package com.example.youthapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_id, et_pass, et_name, et_age;
    private Spinner et_live_big, et_live_small;
    private Button btn_register;
    private AlertDialog dialog;
    private boolean validate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 시작 시 처음으로 실행
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //아이디 값 찾아주기
        et_id = findViewById(R.id.my_id);
        et_pass = findViewById(R.id.my_pass);
        et_name = findViewById(R.id.my_name);
        et_age = findViewById(R.id.my_age);
        et_live_big = findViewById(R.id.my_live_big);
        et_live_small = findViewById(R.id.my_live_small);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_region, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_live_big.setAdapter(adapter);

        et_live_big.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                    et_live_small.setVisibility(View.INVISIBLE);
                else
                    et_live_small.setVisibility(View.VISIBLE);
                if (position == 1)
                    setSmallRegionSpinner(R.array.spinner_region_seoul);
                if (position == 2)
                    setSmallRegionSpinner(R.array.spinner_region_busan);
                if (position == 3)
                    setSmallRegionSpinner(R.array.spinner_region_daegu);
                if (position == 4)
                    setSmallRegionSpinner(R.array.spinner_region_incheon);
                if (position == 5)
                    setSmallRegionSpinner(R.array.spinner_region_gwangju);
                if (position == 6)
                    setSmallRegionSpinner(R.array.spinner_region_daejeon);
                if (position == 7)
                    setSmallRegionSpinner(R.array.spinner_region_ulsan);
                if (position == 8)
                    setSmallRegionSpinner(R.array.spinner_region_sejong);
                if(position == 9)
                    setSmallRegionSpinner(R.array.spinner_region_gyeonggi);
                if (position == 10)
                    setSmallRegionSpinner(R.array.spinner_region_gangwon);
                if (position == 11)
                    setSmallRegionSpinner(R.array.spinner_region_chung_buk);
                if (position == 12)
                    setSmallRegionSpinner(R.array.spinner_region_chung_nam);
                if (position == 13)
                    setSmallRegionSpinner(R.array.spinner_region_jeon_buk);
                if (position == 14)
                    setSmallRegionSpinner(R.array.spinner_region_jeon_nam);
                if (position == 15)
                    setSmallRegionSpinner(R.array.spinner_region_gyeong_buk);
                if (position == 16)
                    setSmallRegionSpinner(R.array.spinner_region_gyeong_nam);
                if (position == 17)
                    setSmallRegionSpinner(R.array.spinner_region_jeju);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_register = findViewById(R.id.btn_changeInfo);
        //회원가입 버튼 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값 GET해옴
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                String userLiveBig = et_live_big.getSelectedItem().toString();
                String userLiveSmall;
                if (et_live_big.getSelectedItemPosition() != 0){
                    userLiveSmall = et_live_small.getSelectedItem().toString();
                }
                else {
                    userLiveSmall = "선택없음";
                }

                int userAge;


                if(userID.length() < 8) {
                    Toast.makeText(getApplicationContext(), "\"ID는 8자 이상이어야 합니다.\"", Toast.LENGTH_SHORT).show();
                    et_id.requestFocus();
                    return;
                }

                if(userPass.length() < 8){
                    Toast.makeText(getApplicationContext(), "\"비밀번호는 8자 이상이어야 합니다.\"", Toast.LENGTH_SHORT).show();
                    et_pass.requestFocus();
                    return;
                }

                if(userName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "\"이름을 입력해주세요.\"", Toast.LENGTH_SHORT).show();
                    et_name.requestFocus();
                    return;
                }

                if(et_age.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "\"나이를 입력해주세요.\"", Toast.LENGTH_SHORT).show();
                    et_age.requestFocus();
                    return;
                }
                else {
                    userAge = Integer.parseInt(et_age.getText().toString());
                }







                Response.Listener<String> responseListener = response -> {
                    try {

                        Log.d("response",""+response);
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) { //회원등록에 성공
                            Toast.makeText(getApplicationContext(), "회원 등록 성공!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else { //회원등록에 실패
                            Toast.makeText(getApplicationContext(), "회원 등록 실패!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(userID, userPass, userName, userLiveBig, userLiveSmall, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });


    }

    public void setSmallRegionSpinner(int small_region_array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                small_region_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_live_small.setAdapter(adapter);
    }
}