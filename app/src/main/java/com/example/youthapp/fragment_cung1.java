package com.example.youthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthapp.Adapter.PolicyAdapter;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.PolicyModel.EmpsInfo;
import com.example.youthapp.Service.PolicyService;
import com.example.youthapp.Service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_cung1 extends Fragment {

    ArrayList<Emp> emps;
    Call<EmpsInfo> call;
    RecyclerView recyclerView;
    PolicyAdapter policyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cung1, container, false);
        return view;

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.policyRecyclerView);

        getPolicyTitles();


        String[] Data1 = {"유형 전체","취업", "창업", "주거ㆍ금융", "생활ㆍ복지", "정책참여"};
        String[] Data2 = {"지역 전체","중앙부처","서울","부산","대구","인천","광주","대전","울산","경기","강원",
                "충북","충남","전북","전남","경북","경남","제주","세종"};



    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public void getPolicyTitles() {

        PolicyService policyService = RetrofitInstance.getPolicyService();
        call = policyService.getPolicyTitle();
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

        policyAdapter = new PolicyAdapter(emps,getActivity(),getActivity().getSupportFragmentManager());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(policyAdapter);
    }



}