package com.example.youthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {
    Spinner selectLocal1, selectLocal2;
    Button buttonSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        selectLocal1 = findViewById(R.id.selectLocal1);
        selectLocal2 = findViewById(R.id.selectLocal2);
        String[] localData = {"지역","서울","부산","대구","인천","광주","대전","울산","경기","강원",
                "충북","충남","전북","전남","경북","경남","제주","세종"};

        ArrayAdapter<String> localAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item,localData);
        localAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        selectLocal1.setAdapter(localAdapter);

        buttonSignUp = findViewById(R.id.buttonSignUp);

        //회원가입 후 로그인 화면으로 이동
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}