package com.example.youthapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass, et_live_big, et_live_small;
    private Button btn_login,btn_register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        et_id=findViewById(R.id.my_id);
        et_pass=findViewById(R.id.my_pass);

        btn_login=findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_changeInfo);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //회원가입 버튼 클릭 시
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("response",""+response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //로그인에 성공
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
                                String userName = jsonObject.getString("userName");
                                int userAge = jsonObject.getInt("userAge");
                                String userLiveBig = jsonObject.getString("userLiveBig");
                                String userLiveSmall = jsonObject.getString("userLiveSmall");

                                //자동 로그인을 위한 id,pass 저장
                                editor.putString("userID",userID);
                                editor.putString("userPass",userPass);
                                editor.putString("userName", userName);
                                editor.putInt("userAge",userAge);
                                editor.putString("userLiveBig",userLiveBig);
                                editor.putString("userLiveSmall",userLiveSmall);
                                editor.apply();

                                Toast.makeText(getApplicationContext(), "로그인 성공!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID); // MainActivity로 넘겨줄 정보
                                intent.putExtra("userPass", userPass); // MainActivity로 넘겨줄 정보
                                intent.putExtra("userLiveBig",userLiveBig);
                                intent.putExtra("userLiveSmall",userLiveSmall);
//                                intent.putExtra("userLiveBig", userLiveBig); // MainActivity로 넘겨줄 정보
//                                intent.putExtra("userLiveSmall", userLiveSmall); // MainActivity로 넘겨줄 정보

                                startActivity(intent);
                                finish();
                            } else { //로그인에 실패
                                Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });


        // 자동 로그인
        if (sharedPreferences.getString("userID","null") != "null"  &&  sharedPreferences.getString("userPass","null") != "null"){
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("response",""+response);
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) { //로그인에 성공
                            String userID = jsonObject.getString("userID");
                            String userPass = jsonObject.getString("userPassword");
                            String userName = jsonObject.getString("userName");
                            int userAge = jsonObject.getInt("userAge");
                            String userLiveBig = jsonObject.getString("userLiveBig");
                            String userLiveSmall = jsonObject.getString("userLiveSmall");


                            Toast.makeText(getApplicationContext(), "자동 로그인 성공!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("userID", userID); // MainActivity로 넘겨줄 정보
                            intent.putExtra("userPass", userPass); // MainActivity로 넘겨줄 정보
                            intent.putExtra("userLiveBig",userLiveBig);
                            intent.putExtra("userLiveSmall",userLiveSmall);
                            startActivity(intent);
                            finish();
                        } else { //로그인에 실패
                            Toast.makeText(getApplicationContext(), "자동 로그인 실패!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            LoginRequest loginRequest = new LoginRequest(sharedPreferences.getString("userID","null"), sharedPreferences.getString("userPass","null"), responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
        }

    }
}

