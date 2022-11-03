package com.example.youthapp.FragmentAdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.youthapp.LoginActivity;
import com.example.youthapp.LoginRequest;
import com.example.youthapp.MainActivity;
import com.example.youthapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Fragment_add4_myinfo extends AppCompatActivity {

    TextView my_id;
    EditText my_pass;
    EditText my_name;
    EditText my_age;
    Spinner my_live_big;
    Spinner my_live_small;
    Button btn_changeInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add4_myinfo);
        my_id = findViewById(R.id.my_id);
        my_pass = findViewById(R.id.my_pass);
        my_name = findViewById(R.id.my_name);
        my_age = findViewById(R.id.my_age);
        my_live_big = findViewById(R.id.my_live_big);
        my_live_small = findViewById(R.id.my_live_small);
        btn_changeInfo = findViewById(R.id.btn_changeInfo);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        my_id.setText(sharedPreferences.getString("userID","null"));
        my_pass.setText(sharedPreferences.getString("userPass",""));
        my_age.setText(Integer.toString(sharedPreferences.getInt("userAge",0)));
        my_name.setText(sharedPreferences.getString("userName","null"));

        btn_changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = my_id.getText().toString();
                String userPass = my_pass.getText().toString();
                String userName = my_name.getText().toString();
                String userLiveBig = my_live_big.getSelectedItem().toString();
                String userLiveSmall;
                if (my_live_big.getSelectedItemPosition() != 0){
                    userLiveSmall = my_live_small.getSelectedItem().toString();
                }
                else {
                    userLiveSmall = "선택없음";
                }
                int userAge = Integer.parseInt(my_age.getText().toString());

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

                                // 변경된 회원정보 저장
                                editor.putString("userPass",userPass);
                                editor.putString("userName",userName);
                                editor.putInt("userAge",userAge);
                                editor.putString("userLiveBig",userLiveBig);
                                editor.putString("userLiveSmall",userLiveSmall);
                                editor.apply();



                                Toast.makeText(getApplicationContext(), "회원정보 수정 완료!", Toast.LENGTH_SHORT).show();
                                finish();


                            } else { //로그인에 실패
                                Toast.makeText(getApplicationContext(), "회원정보 수정 실패!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                InfoRequest infoRequest = new InfoRequest(userID, userPass,userName,userAge, userLiveBig, userLiveSmall, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Fragment_add4_myinfo.this);
                queue.add(infoRequest);


            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_region, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        my_live_big.setAdapter(adapter);
        my_live_big.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                    my_live_small.setVisibility(View.INVISIBLE);
                else
                    my_live_small.setVisibility(View.VISIBLE);
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
    }


    public class InfoRequest extends StringRequest {

        //서버 URL 설정(PHP파일 연동)
        final static  private String URL="http://jimuny01.dothome.co.kr/edit_myinfo.php";
        private Map<String,String> map;

        public InfoRequest(String userID, String userPassword, String userName, int userAge, String userLiveBig, String userLiveSmall, Response.Listener<String> listener){
            super(Method.POST,URL,listener,null);//위 URL에 POST방식으로 값을 전송

            map = new HashMap<>();
            map.put("userID",userID);
            map.put("userPassword",userPassword);
            map.put("userName",userName);
            map.put("userAge",userAge+"");
            map.put("userLiveBig",userLiveBig);
            map.put("userLiveSmall",userLiveSmall);

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }

    public void setSmallRegionSpinner(int small_region_array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                small_region_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        my_live_small.setAdapter(adapter);
    }
}