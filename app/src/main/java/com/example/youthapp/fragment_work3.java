package com.example.youthapp;

import android.app.Dialog;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthapp.Adapter.WorkAdapter;
import com.example.youthapp.Service.RetrofitInstance;
import com.example.youthapp.WorkModel.ShowWork;
import com.example.youthapp.WorkModel.ShowWorkRoot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_work3 extends Fragment {

    Call<ShowWorkRoot> call;
    List<ShowWork> showWork;

    WorkAdapter workAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work3, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.workRecyclerView);
        getWorkTitle();



        String[] Data1 = {"유형 전체", "취업", "창업", "주거ㆍ금융", "생활ㆍ복지", "정책참여"};
        String[] Data2 = {"지역 전체", "중앙부처", "서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원",
                "충북", "충남", "전북", "전남", "경북", "경남", "제주", "세종"};

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void getWorkTitle(){
        call = RetrofitInstance.getWorkService().getWorkTitle();
        call.enqueue(new Callback<ShowWorkRoot>() {
            @Override
            public void onResponse(Call<ShowWorkRoot> call, Response<ShowWorkRoot> response) {
                showWork = response.body().getDhsOpenEmpInfo();
                Log.i("showrk",showWork.get(1).getEmpBusiNm());
                CreateRecyclerView();
            }

            @Override
            public void onFailure(Call<ShowWorkRoot> call, Throwable t) {

            }
        });
    }

    public void CreateRecyclerView(){
        workAdapter = new WorkAdapter(showWork,getActivity(),getActivity().getSupportFragmentManager());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(workAdapter);
    }

}