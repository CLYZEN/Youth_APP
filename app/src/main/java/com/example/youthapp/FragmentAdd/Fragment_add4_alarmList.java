package com.example.youthapp.FragmentAdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.youthapp.Adapter.AlarmListAdapter;
import com.example.youthapp.DataBase.Policy;
import com.example.youthapp.DataBase.PolicyDao;
import com.example.youthapp.DataBase.PolicyDataBase;
import com.example.youthapp.R;

import java.util.List;

public class Fragment_add4_alarmList extends AppCompatActivity {

    RecyclerView recyclerView;
    AlarmListAdapter alarmListAdapter;
    List<Policy> policyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add4_alarm_list);
        ImageView imageViewDelete = findViewById(R.id.imageViewDelete);

        // Room db
        PolicyDataBase db = Room.databaseBuilder(getApplicationContext(), PolicyDataBase.class, "PolicyDB").build();
        PolicyDao policyDao = db.policyDao();

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 알림 리사이클러뷰 clear
                try {
                    alarmListAdapter.deleteList();
                    alarmListAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(Fragment_add4_alarmList.this, "삭제할 데이터가 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                
                // 알림 목록 삭제
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        policyDao.deleteAll();

                    }
                }).start();
                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });



        // 데이터베이스에서 알림목록 불러오기
        new Thread(new Runnable() {
            @Override
            public void run() {
                policyList = policyDao.getAll();

                if (policyList.size() != 0){
                    recyclerView = findViewById(R.id.recyclerViewAlarmList);
                    alarmListAdapter = new AlarmListAdapter(policyList,getSupportFragmentManager());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setAdapter(alarmListAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
            }
        }).start();






    }
}