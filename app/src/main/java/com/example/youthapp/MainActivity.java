package com.example.youthapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    //프래그먼트 관련 변수 정의
    fragment_cung1 fragmentCung1;
    fragment_moon2 fragmentMoon2;
    fragment_work3 fragmentWork3;
    fragment_add4 fragmentAdd4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miancontent);


        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentCung1).commit();
        //바텀 네비게이션 뷰 클릭시 이동
        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_cung:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentCung1).commit();
                        return true;
                    case R.id.navigation_moon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentMoon2).commit();
                        return true;
                    case R.id.navigation_work:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentWork3).commit();
                        return true;
                    case R.id.navigation_add:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragmentAdd4).commit();
                        return true;


                }
                return true;
            }
        });
    }
}

