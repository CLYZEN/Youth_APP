package com.example.youthapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youthapp.Adapter.PolicyAdapter;
import com.example.youthapp.Adapter.ScreenSlidePagerAdapter;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.PolicyModel.EmpsInfo;
import com.example.youthapp.Service.PolicyService;
import com.example.youthapp.Service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_cung1_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_cung1_1 extends Fragment {


    ArrayList<Emp> emps;
    Call<EmpsInfo> call;
    RecyclerView recyclerView;
    PolicyAdapter policyAdapter;




    private String policyType;


    public fragment_cung1_1(String policyType) {
        this.policyType = policyType;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cung1_1, container, false);
        recyclerView = view.findViewById(R.id.policyRecyclerView);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPolicyTitles(policyType);
    }

    public void getPolicyTitles(String policyType) {

        PolicyService policyService = RetrofitInstance.getPolicyService();
        call = policyService.getPolicyTitle(policyType,"");
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