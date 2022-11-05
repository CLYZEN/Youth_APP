package com.example.youthapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.youthapp.FragmentAdd.Fragment_add4_myinfo;
import com.example.youthapp.FragmentAdd.fragment_add4;
import com.example.youthapp.Service.ServiceWorker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.concurrent.TimeUnit;


public class MainActivity extends FragmentActivity {//AppCompatActivity

    fragment_cung1 fragmentCung1;
    fragment_moon2 fragmentMoon2;
    fragment_work3 fragmentWork3;
    fragment_add4 fragmentAdd4;

    FragmentManager fragmentManager;
    public static WorkRequest workRequest;


    /*
    ArrayList<Fragment> fragmentArrayList;
    ViewPager2 viewPager2;
    FragmentStateAdapter pagerAdapter;
    */


    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;
    @Override
    public void onBackPressed() {

        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }


        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LoginActivity로부터 지역정보 받아오기
        String userLiveBig = getIntent().getStringExtra("userLiveBig");
        String userLiveSmall = getIntent().getStringExtra("userLiveSmall");


        //처음화면 구성
        fragmentManager = getSupportFragmentManager();
        fragmentCung1 = new fragment_cung1();
        fragmentManager.beginTransaction().replace(R.id.containers, fragmentCung1).commit();

        // fragmentCung1로 지역정보 전달
        Bundle bundle = new Bundle();
        bundle.putString("userLiveBig", userLiveBig);
        fragmentCung1.setArguments(bundle);


        //바텀 네비게이션 뷰 클릭시 이동
        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_cung:
//                        viewPager2.setCurrentItem(0);
                        if (fragmentCung1 == null){
                            fragmentCung1 = new fragment_cung1();
                            fragmentManager.beginTransaction().add(R.id.containers, fragmentCung1).commit();
                        }

                        // 프래그먼트 화면 전환 시 데이터 유지
                        if (fragmentCung1 != null)
                            fragmentManager.beginTransaction().show(fragmentCung1).commit();
                        if (fragmentMoon2 != null)
                            fragmentManager.beginTransaction().hide(fragmentMoon2).commit();
                        if (fragmentWork3 != null)
                            fragmentManager.beginTransaction().hide(fragmentWork3).commit();
                        if (fragmentAdd4 != null)
                            fragmentManager.beginTransaction().hide(fragmentAdd4).commit();
                        return true;

                    case R.id.navigation_moon:
//                        viewPager2.setCurrentItem(1);
                        if (fragmentMoon2 == null){
                            fragmentMoon2 = new fragment_moon2();
                            fragmentManager.beginTransaction().add(R.id.containers, fragmentMoon2).commit();

                        }
                        if (fragmentCung1 != null)
                            fragmentManager.beginTransaction().hide(fragmentCung1).commit();
                        if (fragmentMoon2 != null)
                            fragmentManager.beginTransaction().show(fragmentMoon2).commit();
                        if (fragmentWork3 != null)
                            fragmentManager.beginTransaction().hide(fragmentWork3).commit();
                        if (fragmentAdd4 != null)
                            fragmentManager.beginTransaction().hide(fragmentAdd4).commit();
                        return true;

                    case R.id.navigation_work:
//                        viewPager2.setCurrentItem(2);
                        if ( fragmentWork3 == null){
                            fragmentWork3 = new fragment_work3();
                            fragmentManager.beginTransaction().add(R.id.containers, fragmentWork3).commit();
                        }
                        if (fragmentCung1 != null)
                            fragmentManager.beginTransaction().hide(fragmentCung1).commit();
                        if (fragmentMoon2 != null)
                            fragmentManager.beginTransaction().hide(fragmentMoon2).commit();
                        if (fragmentWork3 != null)
                            fragmentManager.beginTransaction().show(fragmentWork3).commit();
                        if (fragmentAdd4 != null)
                            fragmentManager.beginTransaction().hide(fragmentAdd4).commit();
                        return true;

                    case R.id.navigation_add:
//                        viewPager2.setCurrentItem(3);
                        if ( fragmentAdd4 == null){
                            fragmentAdd4 = new fragment_add4();
                            fragmentManager.beginTransaction().add(R.id.containers, fragmentAdd4).commit();
                        }
                        if (fragmentCung1 != null)
                            fragmentManager.beginTransaction().hide(fragmentCung1).commit();
                        if (fragmentMoon2 != null)
                            fragmentManager.beginTransaction().hide(fragmentMoon2).commit();
                        if (fragmentWork3 != null)
                            fragmentManager.beginTransaction().hide(fragmentWork3).commit();
                        if (fragmentAdd4 != null)
                            fragmentManager.beginTransaction().show(fragmentAdd4).commit();
                        return true;
                }//switch
                return true;
            }//onNavigationItemSelected
        });//setOnItemSelectedListener


        // 시스템에 백그라운드 처리 등록
        Boolean alarmCheckState = NotificationManagerCompat.from(getApplicationContext()).areNotificationsEnabled();
        if (alarmCheckState == true){
            WorkRequest serviceWorkRequest = new PeriodicWorkRequest.Builder(ServiceWorker.class, 1, TimeUnit.HOURS)
                    .build();
            WorkManager.getInstance(getApplicationContext()).enqueue(serviceWorkRequest);
            Log.d("MainActivity","워크매니저 실행");
        }




        //FCM 푸시알림
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()){
                    Log.w("MAINACTIVITY", "토큰 불러오기 실패", task.getException());
                    return;
                }
                String newToken = task.getResult();
                Log.d("MAINACTIVITY", newToken);
            }
        });


    }//main

    //뒤로가기 버튼, 이전 프래그먼트로 이동
    /*
    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0){
            super.onBackPressed();

        }
        else{
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }
    */

}
