package com.example.youthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.youthapp.Adapter.PolicyAdapter;
import com.example.youthapp.Adapter.ScreenSlidePagerAdapter;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.PolicyModel.EmpsInfo;
import com.example.youthapp.Service.PolicyService;
import com.example.youthapp.Service.RetrofitInstance;
import com.example.youthapp.Service.ServiceWorker;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_cung1_1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    PolicyService policyService;
    ArrayList<Emp> emps;
    Call<EmpsInfo> call;
    RecyclerView recyclerView;
    PolicyAdapter policyAdapter;
    SwipeRefreshLayout swipeRefreshLayout;



    private String policyType;
    private String policyLocal="";
    private String isCenter;
    private int pageIndex = 1;
    public fragment_cung1_1(String policyType) {
        this.policyType = policyType;
        Log.d("필터미적용프래그먼트",policyType);

    }

    public fragment_cung1_1(String policyType, String policyLocal){
        this.policyType = policyType;
        this.policyLocal = policyLocal;
        Log.d("필터적용프래그먼트",policyLocal+" and "+policyType);
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
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPolicyTitles(policyType, policyLocal, pageIndex);

        // 다음 페이지 로딩
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ( newState == recyclerView.SCROLL_STATE_SETTLING) {
                    if (!recyclerView.canScrollVertically(1)) {
                        pageIndex += 1;
                        int oldCnt = policyAdapter.getItemCount();
                        call = policyService.getPolicyTitle(policyType, policyLocal, "", pageIndex,"100");
                        call.enqueue(new Callback<EmpsInfo>() {
                            @Override
                            public void onResponse(Call<EmpsInfo> call, Response<EmpsInfo> response) {
                                EmpsInfo empsInfo = response.body();
                                emps = (ArrayList<Emp>) empsInfo.getEmp();
                                int newCnt = emps.size();
                                policyAdapter.addPolicyList(emps);
                                policyAdapter.notifyItemRangeInserted(oldCnt, newCnt);
                                Log.d("fragC1_1", "PAGEINDEX : " + pageIndex);
                            }

                            @Override
                            public void onFailure(Call<EmpsInfo> call, Throwable t) {

                            }
                        });
                    }
                }
            }
        });// listner

        //스와이프 하여 정책 새로고침
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPolicyTitles(policyType, policyLocal, 1);
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "새로고침 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // open api 요청
    public void getPolicyTitles(String policyType, String policyLocal, int pageIndex) {


        policyService = RetrofitInstance.getPolicyService();
        call = policyService.getPolicyTitle(policyType, policyLocal, "", pageIndex, "100");
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


    }//getPolicyTitles

    //create RecyclerView
    private void CreateRecyclerView() {


        policyAdapter = new PolicyAdapter(emps,getActivity(),getActivity().getSupportFragmentManager());
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());



        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(policyAdapter);

    }


    @Override
    public void onRefresh() {

    }
}