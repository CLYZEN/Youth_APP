package com.example.youthapp.FragmentAdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.PolicyModel.EmpsInfo;
import com.example.youthapp.R;
import com.example.youthapp.Service.PolicyService;
import com.example.youthapp.Service.RetrofitInstance;
import com.example.youthapp.Service.ServiceWorker;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_add4_alarm extends AppCompatActivity {

    Switch alarmSwitch;
    TextView textViewLocal;
    Spinner spinner;

    /*CheckBox checkBox_house_finance;
    CheckBox checkBox_life_welfare;
    CheckBox checkBox_work;
    CheckBox checkBox_etc;*/

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add4_alarm);

        alarmSwitch = findViewById(R.id.switchAlarm);
        textViewLocal = findViewById(R.id.textViewLocal);
        spinner = findViewById(R.id.spinnerAlarmLocal);


        //알림 on/off

        Boolean alarmCheckState = NotificationManagerCompat.from(getApplicationContext()).areNotificationsEnabled();    // 알림 허용 상태를 불러옴
        if (alarmCheckState == true) {
            alarmSwitch.setChecked(alarmCheckState);
            textViewLocal.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
        }
        else {
            alarmSwitch.setChecked(alarmCheckState);
            textViewLocal.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);
        }

        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
                startActivity(intent);
            }

        });

        // 알림 설정 값 데이터
        sharedPref = getApplicationContext().getSharedPreferences("alarm_setting_pref", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        // 알림 지역 선택

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_region, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(sharedPref.getInt("default_Alarm_Local", 0));   //지역 기본값
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        editor.putString("alarmLocal", "");  //전체지역
                        editor.putInt("default_Alarm_Local", 0);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "전체지역 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        editor.putString("alarmLocal", "003002001");  //서울특별시
                        editor.putInt("default_Alarm_Local", 1);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "서울특별시 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        editor.putString("alarmLocal", "003002002");  //부산광역시
                        editor.putInt("default_Alarm_Local", 2);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "부산광역시 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        editor.putString("alarmLocal", "003002003");  //대구광역시
                        editor.putInt("default_Alarm_Local", 3);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "대구광역시 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        editor.putString("alarmLocal", "003002004");  //인천광역시
                        editor.putInt("default_Alarm_Local", 4);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "인천광역시 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        editor.putString("alarmLocal", "003002005");  //광주광역시
                        editor.putInt("default_Alarm_Local", 5);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "광주광역시 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        editor.putString("alarmLocal", "003002006");  //대전광역시
                        editor.putInt("default_Alarm_Local", 6);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "대전광역시", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        editor.putString("alarmLocal", "003002007");  //울산광역시
                        editor.putInt("default_Alarm_Local", 7);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "울산광역시 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        editor.putString("alarmLocal", "003002017");  //세종특별자치시
                        editor.putInt("default_Alarm_Local", 8);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "세종특별자치시 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        editor.putString("alarmLocal", "003002008");  //경기도
                        editor.putInt("default_Alarm_Local", 9);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "경기도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 10:
                        editor.putString("alarmLocal", "003002009");  //강원도
                        editor.putInt("default_Alarm_Local", 10);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "강원도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 11:
                        editor.putString("alarmLocal", "003002010");  //충청북도
                        editor.putInt("default_Alarm_Local", 11);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "충청북도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 12:
                        editor.putString("alarmLocal", "003002011");  //충청남도
                        editor.putInt("default_Alarm_Local", 12);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "충청남도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 13:
                        editor.putString("alarmLocal", "003002012");  //전라북도
                        editor.putInt("default_Alarm_Local", 13);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "전라북도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 14:
                        editor.putString("alarmLocal", "003002013");  //전라남도
                        editor.putInt("default_Alarm_Local", 14);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "전라남도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 15:
                        editor.putString("alarmLocal", "003002014");  //경상북도
                        editor.putInt("default_Alarm_Local", 15);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "경상북도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 16:
                        editor.putString("alarmLocal", "003002015");  //경상남도
                        editor.putInt("default_Alarm_Local", 16);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "경상남도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case 17:
                        editor.putString("alarmLocal", "003002016");  //제주특별자치도
                        editor.putInt("default_Alarm_Local", 17);
                        editor.apply();
                        Toast.makeText(Fragment_add4_alarm.this, "제주특별자치도 알림을 받습니다", Toast.LENGTH_SHORT).show();
                        break;
                }
                Call<EmpsInfo> call;
                PolicyService policyService = RetrofitInstance.getPolicyService();
                call = policyService.getPolicyTitle("",sharedPref.getString("alarmLocal",""),"", 1,"10");
                call.enqueue(new Callback<EmpsInfo>() {
                    @Override
                    public void onResponse(Call<EmpsInfo> call, Response<EmpsInfo> response) {
                        EmpsInfo empsInfo = response.body();
                        ArrayList<Emp> emps = (ArrayList<Emp>) empsInfo.getEmp();
                        // 정책 개수 데이터 불러오기
                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("prefCnt", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("cnt",empsInfo.getTotalCnt());
                        editor.apply();
                        Log.d("알람필터설정", sharedPref.getString("alarmLocal","")+"지역 : "+sharedPref.getInt("cnt",0));
                    }

                    @Override
                    public void onFailure(Call<EmpsInfo> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });


        /*checkBox_house_finance = findViewById(R.id.checkBox_house_finance);
        checkBox_life_welfare = findViewById(R.id.checkBox_life_welfare);
        checkBox_work = findViewById(R.id.checkBox_work);
        checkBox_etc = findViewById(R.id.checkBox_etc);*/

    }

    /*public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_house_finance:
                if (checked){
                    editor.putBoolean("house_finance",true);
                    editor.apply();
                }
                else {
                    editor.putBoolean("house_finance",false);
                    editor.apply();
                }
                break;

            case R.id.checkBox_life_welfare:
                if (checked){
                    editor.putBoolean("life_welfare",true);
                    editor.apply();
                }
                else {
                    editor.putBoolean("life_welfare",false);
                    editor.apply();

                }
                break;

            case R.id.checkBox_work:
                if (checked){
                    editor.putBoolean("work",true);
                    editor.apply();
                }
                else {
                    editor.putBoolean("work",false);
                    editor.apply();
                }
                break;

            case R.id.checkBox_etc:
                if (checked){
                    editor.putBoolean("etc",true);
                    editor.apply();
                }
                else {
                    editor.putBoolean("etc",false);
                    editor.apply();
                }
                break;



        }
    }*/


    @Override
    protected void onRestart() {
        super.onRestart();
        Boolean alarmCheckState = NotificationManagerCompat.from(getApplicationContext()).areNotificationsEnabled();    // 알림 허용 상태를 불러옴
        if (alarmCheckState == true) {
            alarmSwitch.setChecked(alarmCheckState);
            textViewLocal.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            WorkRequest serviceWorkRequest = new PeriodicWorkRequest.Builder(ServiceWorker.class, 1, TimeUnit.HOURS)
                    .build();
            WorkManager.getInstance(getApplicationContext()).enqueue(serviceWorkRequest);
            Log.d("setting","워크매니저 실행");
        }
        else {
            alarmSwitch.setChecked(alarmCheckState);
            textViewLocal.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);
            //workmanager 해제
            WorkManager.getInstance(getApplicationContext()).cancelAllWork();
            Log.d("setting","워크매니저 해제");
        }
    }
}