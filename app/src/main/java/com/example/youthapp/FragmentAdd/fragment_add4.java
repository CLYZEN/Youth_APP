package com.example.youthapp.FragmentAdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthapp.Adapter.SettingAdapter;
import com.example.youthapp.R;


public class fragment_add4 extends Fragment {

    private String[] settingTitle = {"알림 설정", "알림 목록", "회원정보 수정", "로그아웃", "회원탈퇴"};
    RecyclerView recyclerView;
    SettingAdapter settingAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add4, container, false);





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.settingRecyclerView);
        settingAdapter = new SettingAdapter(settingTitle, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(settingAdapter);
    }
}