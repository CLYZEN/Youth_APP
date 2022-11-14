package com.example.youthapp.Service;


//import static androidx.core.content.ContextCompat.getSystemService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.example.youthapp.DataBase.Policy;
import com.example.youthapp.DataBase.PolicyDao;
import com.example.youthapp.DataBase.PolicyDataBase;
import com.example.youthapp.FragmentAdd.Fragment_add4_alarmList;
import com.example.youthapp.LoginActivity;
import com.example.youthapp.MainActivity;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.PolicyModel.EmpsInfo;
import com.example.youthapp.R;
import com.example.youthapp.SplashActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceWorker extends Worker {

    public ServiceWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    // 백그라운드 스레드에서 비동기적으로 실행
    public Result doWork() {

        Call<EmpsInfo> call;

        PolicyService policyService = RetrofitInstance.getPolicyService();

        // 알림 지역 설정 데이터 불러오기
        SharedPreferences sharedSettingPref = getApplicationContext().getSharedPreferences("alarm_setting_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor settingEditor = sharedSettingPref.edit();

        call = policyService.getPolicyTitle("",sharedSettingPref.getString("alarmLocal",""),"", 1,"10");
        call.enqueue(new Callback<EmpsInfo>() {
            @Override
            public void onResponse(Call<EmpsInfo> call, Response<EmpsInfo> response) {
                EmpsInfo empsInfo = response.body();
                ArrayList<Emp> emps = (ArrayList<Emp>) empsInfo.getEmp();

                // 정책 개수 데이터 불러오기
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("prefCnt", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                int newTotalCnt = empsInfo.getTotalCnt();                   //신규 정책 수
                int oldTotalCnt =  sharedPref.getInt("cnt",0); //기존 정책 수

                Log.d("WorkTag","newTotalCnt : " + newTotalCnt);
                Log.d("WorkTag","oldTotalCnt : " + oldTotalCnt);

                // 앱 최초 실행 시 TotalCnt 할당
                if (oldTotalCnt == 0){
                    editor.putInt("cnt", newTotalCnt);
                    editor.apply();
                    oldTotalCnt = sharedPref.getInt("cnt",0);
//                    Toast.makeText(getApplicationContext(), "앱 최초 실행 totalCnt : " + newTotalCnt + " saved ", Toast.LENGTH_SHORT).show();
                    Log.d("WorkTag","oldTotalCnt(앱 최초 실행) : " + oldTotalCnt);
                }




                // 알람 채널 생성
                createNotificationChannel();

                if ( newTotalCnt  > oldTotalCnt){

                    // 신규 정책 수
                    int newPolicy = newTotalCnt  - oldTotalCnt;
                    Log.d("WorkTag","newPolicy : " + newPolicy);

                    editor.putInt("cnt", newTotalCnt);
                    editor.apply();

                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < newPolicy; i++){
                        stringBuilder.append(emps.get(i).getPolyBizSjnm());
                        stringBuilder.append("\n");
                    }

                    //신규 알림 알림목록에 저장
                    PolicyDataBase db = Room.databaseBuilder(getApplicationContext(), PolicyDataBase.class, "PolicyDB").build();
                    PolicyDao policyDao = db.policyDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i< newPolicy; i++){
                                Policy policy = new Policy();
                                Emp policyContent = emps.get(i);
                                policy.policyTitle = policyContent.getPolyBizSjnm();
                                policy.policyId = policyContent.getBizId();
                                policy.policyLink = policyContent.getRqutUrla();
                                policy.policyContent = "기관 및 지자체 구분 : " + policyContent.getPolyBizTy() + "\n"
                                        +"정책소개 : "+policyContent.getPolyItcnCn() + "\n"
                                        +"정책유형 : "+policyContent.getPlcyTpNm() + "\n"
                                        +"지원규모 : "+policyContent.getSporScvl() + "\n"
                                        +"지원내용 : "+policyContent.getSporCn() + "\n"
                                        +"참여요건 - 연령 : "+policyContent.getAgeInfo() + "\n"
                                        +"참여요건 - 취업상태 : "+policyContent.getEmpmSttsCn() + "\n"
                                        +"참여요건 - 학력 : "+policyContent.getAccrRqisCn() + "\n"
                                        +"참여요건 - 전공 : "+policyContent.getMajrRqisCn() + "\n"
                                        +"참여요건 - 특화분야 : "+policyContent.getSplzRlmRqisCn() + "\n"
                                        +"신청기관명 : "+policyContent.getCnsgNmor() + "\n"
                                        +"신청기간 : "+policyContent.getRqutPrdCn() + "\n"
                                        +"신청절차 : "+policyContent.getRqutProcCn() + "\n"
                                        +"심사발표 : "+policyContent.getJdgnPresCn() + "\n"
                                        +"사이트 링크 주소 : "+policyContent.getRqutUrla();

                                policyDao.insertAll(policy);
                            }
                        }
                    }).start();




                    // 상단 알림
                    Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "POLICY")
                            .setSmallIcon(R.drawable.applogo)
                            .setContentTitle("YouthApp")
                            .setContentText("신규 정책이 " +newPolicy+ "개 있습니다.\n" + stringBuilder.toString())
                            .setStyle(new NotificationCompat.BigTextStyle())
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                    notificationManagerCompat.notify(100,builder.build());
                }

                else{
                    Log.d("WorkTag","신규정책없음 newTotalCnt :" + newTotalCnt + "  oldTotalCnt : " + oldTotalCnt );

                    // TEST NOTIFICATION
                    /*
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "POLICY")
                            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                            .setContentTitle("YouthApp")
                            .setContentText("신규정책 없음 newTotalCnt : "+ newTotalCnt + "\n" + "oldTotalCnt : " + oldTotalCnt)
                            .setStyle(new NotificationCompat.BigTextStyle())
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                    notificationManagerCompat.notify(10 ,builder.build());
                    */
                }


            }

            @Override
            public void onFailure(Call<EmpsInfo> call, Throwable t) {

            }
        }); //call



        // 작업의 성공여부 반환
        return Result.success();
    }   //doWork

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("POLICY", "policy", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("신규 정책 알림");

            NotificationManager notificationManager =  getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}
