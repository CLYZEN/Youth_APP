package com.example.youthapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //로딩화면 시작.
        Loadingstart();
    }

    private void Loadingstart(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
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
                                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                    intent.putExtra("userID", userID); // MainActivity로 넘겨줄 정보
                                    intent.putExtra("userPass", userPass); // MainActivity로 넘겨줄 정보
                                    intent.putExtra("userLiveBig",userLiveBig);
                                    intent.putExtra("userLiveSmall",userLiveSmall);
                                    startActivity(intent);
                                    finish();
                                } else { //로그인에 실패
                                    Toast.makeText(getApplicationContext(), "자동 로그인 실패!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    LoginRequest loginRequest = new LoginRequest(sharedPreferences.getString("userID","null"), sharedPreferences.getString("userPass","null"), responseListener);
                    RequestQueue queue = Volley.newRequestQueue(SplashActivity.this);
                    queue.add(loginRequest);
                }
                else {

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },2000);
    }

}
