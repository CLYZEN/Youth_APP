package com.example.youthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthapp.Adapter.CultureAdapter;
import com.example.youthapp.CultureModel.ShowList;
import com.example.youthapp.CultureModel.ShowListRoot;
import com.example.youthapp.Service.CultureService;
import com.example.youthapp.Service.RetrofitInstance;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_moon2 extends Fragment {

    ArrayList<ShowList> showList;
    Call<ShowListRoot> call;
    RecyclerView recyclerView;
    CultureAdapter cultureAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_moon2, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.cultureRecyclerView);
        getCultureTitles();


        String[] Data1 = {"유형 전체", "취업", "창업", "주거ㆍ금융", "생활ㆍ복지", "정책참여"};
        String[] Data2 = {"지역 전체", "중앙부처", "서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원",
                "충북", "충남", "전북", "전남", "경북", "경남", "제주", "세종"};




    }
    @Override
    public void onDestroyView () {
        super.onDestroyView();
    }


    public void getCultureTitles(){
        CultureService cultureService = RetrofitInstance.getCultureService();
        call = cultureService.getCultureTitle();
        call.enqueue(new Callback<ShowListRoot>() {
            @Override
            public void onResponse(Call<ShowListRoot> call, Response<ShowListRoot> response) {
                ShowListRoot showListRoot = response.body();
                showList = (ArrayList<ShowList>) showListRoot.getShowList();
                CreateRecyclerView();



            }

            @Override
            public void onFailure(Call<ShowListRoot> call, Throwable t) {

            }
        });
    }

    private void CreateRecyclerView() {
        cultureAdapter = new CultureAdapter(showList,getActivity(), Objects.requireNonNull(getActivity()).getSupportFragmentManager());
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cultureAdapter);
    }
}
