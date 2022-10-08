package com.example.youthapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.youthapp.Adapter.PolicyAdapter;
import com.example.youthapp.Adapter.ScreenSlidePagerAdapter;
import com.example.youthapp.Dialog.FilterDialog;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.PolicyModel.EmpsInfo;
import com.example.youthapp.Service.PolicyService;
import com.example.youthapp.Service.RetrofitInstance;
import com.example.youthapp.Service.ServiceWorker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_cung1 extends Fragment {

    SearchView searchView;
    ImageView searchFilter;
    TabLayout tabLayout;
    ViewPager2 pager;
    ScreenSlidePagerAdapter pagerAdapter;
    ArrayList<Fragment> fragmentArrayList;

    String LocalDataCode;
    String LocalDataString;


    Fragment fragment0, fragment1, fragment2, fragment3;

    public fragment_cung1() {


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cung1, container, false);



        searchView = view.findViewById(R.id.searchView);
        searchFilter = view.findViewById(R.id.searchFilter);
        tabLayout = view.findViewById(R.id.tabLayout);

        // LoginActivity로 부터 지역 String 받아오기
        Bundle bundle = getArguments();
        LocalDataString = bundle.getString("userLiveBig");

        String[] arrayLocalDataString = {"지역 전체", "서울","부산","대구","인천","광주","대전","울산","경기","강원",
                "충북","충남","전북","전남","경북","경남","제주","세종"};

        String[] arrayLocalDataCode = {"","003002001","003002002","003002003","003002004","003002005","003002006",
                "003002007","003002008","003002009","003002010","003002011","003002012","003002013","003002014",
                "003002015","003002016","003002017"};

        // 지역 String과 지역 Code 맵핑
        for (int i=0; i<arrayLocalDataString.length; i++){
            if (arrayLocalDataString[i].contains(LocalDataString)){
                LocalDataCode = arrayLocalDataCode[i];
            }
        }

        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new fragment_cung1_1("004003", LocalDataCode)); // 주거·금융
        fragmentArrayList.add(new fragment_cung1_1("004004", LocalDataCode)); // 생활·복지
        fragmentArrayList.add(new fragment_cung1_1("004001", LocalDataCode)); // 취업지원
        fragmentArrayList.add(new fragment_cung1_1("004002,004005,004006", LocalDataCode)); // 창업지원, 정책참여, 코로나19

        //viewPager 생성
        pager = view.findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getActivity(),fragmentArrayList);
        pager.setAdapter(pagerAdapter);

        return view;

    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //검색 기능
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                intent.putExtra("query", query);
                intent.putExtra("LocalDataCode", LocalDataCode);
                intent.putExtra("LocalDataString", LocalDataString);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        //검색필터
        searchFilter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FilterDialog filterDialog =  new FilterDialog();
                //
                filterDialog.setFilterListener(new FilterDialog.FilterDialogListener() {
                            @Override
                            public void onLoadLocalData(ArrayList<Fragment> fragmentArrayList, String LocalDataCode, String LocalDataString) {
                                //새로고침
                                pagerAdapter = new ScreenSlidePagerAdapter(getActivity(),fragmentArrayList);
                                pager.setAdapter(pagerAdapter);
                                setLocalDataCode(LocalDataCode);    //지역코드 저장
                                setLocalDataString(LocalDataString);    //지역이름 저장

                                /* 뷰페이저 어댑터 notifiy (오류)
                                pagerAdapter.setFragmentArrayList(fragmentArrayList);
                                pagerAdapter.notifyItemRangeChanged(0,pagerAdapter.getItemCount());
                                */

                            }
                        });
               filterDialog.show(getChildFragmentManager(),"");

            }
        });




        //탭 레이아웃
        String tabName[] = new String[]{"주거·금융", "생활·복지", "취업지원", "기타"};
        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabName[position]);
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        pager.setCurrentItem(0);    // 주거·금융
                        break;
                    case 1:
                        pager.setCurrentItem(1);    // 생활·복지
                        break;
                    case 2:
                        pager.setCurrentItem(2);    // 취업지원
                        break;
                    case 3:
                        pager.setCurrentItem(3);    // 기타
                        break;
                    default:
                        return;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //뷰페이지 전환에 따른 메뉴 아이템 체크
        /*pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });*/

        String[] Data1 = {"유형 전체","취업", "창업", "주거ㆍ금융", "생활ㆍ복지", "정책참여"};



    }





    public void setLocalDataCode(String localDataCode) {
        LocalDataCode = localDataCode;
    }

    public void setLocalDataString(String localDataString) {
        LocalDataString = localDataString;
    }


}