package com.example.youthapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends FragmentActivity {//AppCompatActivity

    fragment_cung1 fragmentCung1;
    fragment_moon2 fragmentMoon2;
    fragment_work3 fragmentWork3;
    fragment_add4 fragmentAdd4;

    FragmentManager fragmentManager;



    /*
    ArrayList<Fragment> fragmentArrayList;
    ViewPager2 viewPager2;
    FragmentStateAdapter pagerAdapter;
    */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewpager2 생성
        /*
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(fragmentCung1);
        fragmentArrayList.add(fragmentMoon2);
        fragmentArrayList.add(fragmentWork3);
        fragmentArrayList.add(fragmentAdd4);
        */

        /*
        viewPager2 = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this,fragmentArrayList);
        viewPager2.setAdapter(pagerAdapter);
        */

        fragmentManager = getSupportFragmentManager();

        //처음화면 구성
        fragmentCung1 = new fragment_cung1();
        fragmentManager.beginTransaction().replace(R.id.containers, fragmentCung1).commit();

        //바텀 네비게이션 뷰 클릭시 이동
        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_cung:
//                        viewPager2.setCurrentItem(0);
                        if (fragmentCung1 == null){
                            fragmentCung1 = new fragment_cung1();
                            fragmentManager.beginTransaction().add(R.id.containers, fragmentCung1).commit();
                        }

                        if (fragmentCung1 != null)
                            fragmentManager.beginTransaction().show(fragmentCung1).commit();
                        if (fragmentMoon2 != null)
                            fragmentManager.beginTransaction().hide(fragmentMoon2).commit();
                        if (fragmentWork3 != null)
                            fragmentManager.beginTransaction().hide(fragmentWork3).commit();
                        if (fragmentAdd4 != null)
                            fragmentManager.beginTransaction().hide(fragmentAdd4).commit();



                        return true;

                    case R.id.navigation_moon:
//                        viewPager2.setCurrentItem(1);
                        if (fragmentMoon2 == null){
                            fragmentMoon2 = new fragment_moon2();
                            fragmentManager.beginTransaction().add(R.id.containers, fragmentMoon2).commit();

                        }
                        if (fragmentCung1 != null)
                            fragmentManager.beginTransaction().hide(fragmentCung1).commit();
                        if (fragmentMoon2 != null)
                            fragmentManager.beginTransaction().show(fragmentMoon2).commit();
                        if (fragmentWork3 != null)
                            fragmentManager.beginTransaction().hide(fragmentWork3).commit();
                        if (fragmentAdd4 != null)
                            fragmentManager.beginTransaction().hide(fragmentAdd4).commit();
                        return true;

                    case R.id.navigation_work:
//                        viewPager2.setCurrentItem(2);
                        if ( fragmentWork3 == null){
                            fragmentWork3 = new fragment_work3();
                            fragmentManager.beginTransaction().add(R.id.containers, fragmentWork3).commit();
                        }
                        if (fragmentCung1 != null)
                            fragmentManager.beginTransaction().hide(fragmentCung1).commit();
                        if (fragmentMoon2 != null)
                            fragmentManager.beginTransaction().hide(fragmentMoon2).commit();
                        if (fragmentWork3 != null)
                            fragmentManager.beginTransaction().show(fragmentWork3).commit();
                        if (fragmentAdd4 != null)
                            fragmentManager.beginTransaction().hide(fragmentAdd4).commit();
                        return true;

                    case R.id.navigation_add:
//                        viewPager2.setCurrentItem(3);
                        if ( fragmentAdd4 == null){
                            fragmentAdd4 = new fragment_add4();
                            fragmentManager.beginTransaction().add(R.id.containers, fragmentAdd4).commit();
                        }
                        if (fragmentCung1 != null)
                            fragmentManager.beginTransaction().hide(fragmentCung1).commit();
                        if (fragmentMoon2 != null)
                            fragmentManager.beginTransaction().hide(fragmentMoon2).commit();
                        if (fragmentWork3 != null)
                            fragmentManager.beginTransaction().hide(fragmentWork3).commit();
                        if (fragmentAdd4 != null)
                            fragmentManager.beginTransaction().show(fragmentAdd4).commit();
                        return true;
                }//switch
                return true;
            }//onNavigationItemSelected
        });//setOnItemSelectedListener

        //뷰페이지 전환에 따른 메뉴 아이템 체크
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                navigationBarView.getMenu().getItem(position).setChecked(true);
//            }
//        });




    }//main

    //뒤로가기 버튼, 이전 프래그먼트로 이동
    /*
    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0){
            super.onBackPressed();

        }
        else{
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }
    */

}
