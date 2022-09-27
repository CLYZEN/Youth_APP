package com.example.youthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.youthapp.Adapter.PolicyAdapter;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.PolicyModel.EmpsInfo;
import com.example.youthapp.Service.PolicyService;
import com.example.youthapp.Service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    ArrayList<Emp> emps;
    Call<EmpsInfo> call;
    RecyclerView recyclerView;
    PolicyAdapter policyAdapter;
    String query;
    TextView searchResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.searchRecyClerView);
        searchResult = findViewById(R.id.searchResultTextView);
        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        searchResult.setText("'"+query+"'"+" 검색결과 ");
        getPolicyTitles(query);

    }

    public void getPolicyTitles(String query) {

        PolicyService policyService = RetrofitInstance.getPolicyService();
        call = policyService.getPolicyTitle("",query);
        call.enqueue(new Callback<EmpsInfo>() {
            @Override
            public void onResponse(Call<EmpsInfo> call, Response<EmpsInfo> response) {
                EmpsInfo empsInfo = response.body();
                emps = (ArrayList<Emp>) empsInfo.getEmp();

                CreateRecyclerView();

            }

            @Override
            public void onFailure(Call<EmpsInfo> call, Throwable t) {

            }
        });

    }

    //create RecyclerView
    private void CreateRecyclerView() {

        policyAdapter = new PolicyAdapter(emps,getApplicationContext(),getSupportFragmentManager());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(policyAdapter);
    }
}