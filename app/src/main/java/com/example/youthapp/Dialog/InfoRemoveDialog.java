package com.example.youthapp.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.room.Room;
import androidx.work.WorkManager;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.youthapp.DataBase.PolicyDao;
import com.example.youthapp.DataBase.PolicyDataBase;
import com.example.youthapp.FragmentAdd.Fragment_add4_myinfo;
import com.example.youthapp.LoginActivity;
import com.example.youthapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InfoRemoveDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("정말 탈퇴 하시겠습니까?" + "\n" + "회원정보가 모두 삭제됩니다.")
                .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Log.d("response",""+response);
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean success = jsonObject.getBoolean("success");
                                    if (success) { // 회원탈퇴 성공


                                    } else {

                                        return;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        InfoRemoveRequest infoRemoveRequest = new InfoRemoveRequest(sharedPreferences.getString("userID",""), responseListener);

                        RequestQueue queue = Volley.newRequestQueue(getActivity());
                        queue.add(infoRemoveRequest);

                        // AutoLogin 데이터 삭제
                        SharedPreferences sharedPref_AutoLogin= getActivity().getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
                        sharedPref_AutoLogin.edit().clear().apply();


                        //즐겨찾기 데이터 삭제
                        SharedPreferences sharedPref_MyList = getActivity().getSharedPreferences("myListPref", Context.MODE_PRIVATE);
                        sharedPref_MyList.edit().clear().apply();

                        //정책 데이터 삭제
                        SharedPreferences sharedPref_Cnt = getActivity().getSharedPreferences("prefCnt", Context.MODE_PRIVATE);
                        sharedPref_Cnt.edit().clear().apply();

                        //workmanager 해제
                        WorkManager.getInstance(getActivity()).cancelAllWork();

                        //알림 목록 데이터 삭제
                        PolicyDataBase db = Room.databaseBuilder(getActivity(), PolicyDataBase.class, "PolicyDB").build();
                        PolicyDao policyDao = db.policyDao();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                policyDao.deleteAll();

                            }
                        }).start();

                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "탈퇴되었습니다.", Toast.LENGTH_SHORT).show();
                        getActivity().finish();


                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }



    public class InfoRemoveRequest extends StringRequest {

        //서버 URL 설정(PHP파일 연동)
        final static  private String URL="http://jimuny01.dothome.co.kr/remove_info.php";
        private Map<String,String> map;

        public InfoRemoveRequest(String userID, Response.Listener<String> listener){
            super(Method.POST,URL,listener,null);//위 URL에 POST방식으로 값을 전송

            map = new HashMap<>();
            map.put("userID",userID);

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }
    }
}