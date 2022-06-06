package com.example.youthapp;

import android.app.Dialog;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
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

import java.util.ArrayList;

public class fragment_work3 extends Fragment {

    TextView policy_fillter;
    Spinner spinnerPolicy, spinnerLocal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work3, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerPolicy = view.findViewById(R.id.spinnerPolicy);
        spinnerLocal = view.findViewById(R.id.spinnerLocal);
        policy_fillter = view.findViewById(R.id.fillter);


        policy_fillter.setOnClickListener(new View.OnClickListener() {
            boolean show = false;
            @Override
            public void onClick(View view) {

                if (show == true)
                {
                    spinnerLocal.setVisibility(view.GONE);
                    spinnerPolicy.setVisibility(view.GONE);
                    show = false;
                }
                else
                {
                    spinnerLocal.setVisibility(view.VISIBLE);
                    spinnerPolicy.setVisibility(view.VISIBLE);
                    show = true;
                }
            }
        });

        String[] Data1 = {"유형 전체","취업", "창업", "주거ㆍ금융", "생활ㆍ복지", "정책참여"};
        String[] Data2 = {"지역 전체","중앙부처","서울","부산","대구","인천","광주","대전","울산","경기","강원",
                "충북","충남","전북","전남","경북","경남","제주","세종"};


        //정책 유형 선택
        ArrayAdapter<String> policyAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,Data1);
        policyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerPolicy.setAdapter(policyAdapter);


        // 지역 선택
        ArrayAdapter<String> localAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,Data2);
        localAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLocal.setAdapter(localAdapter);

        //스피너 애니메이션
        Transition localTransition = new Fade();
        localTransition.addTarget(spinnerLocal);

        Transition policyTransition = new Fade();
        policyTransition.addTarget(spinnerPolicy);


        ArrayList<String> policyData = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,policyData);

        ListView policyListView = view.findViewById(R.id.policyListView);
        policyListView.setAdapter(arrayAdapter);

        //임시 데이터 생성
        for (int i=1; i<=20; i++){
            arrayAdapter.add("취업"+i);
        }

        arrayAdapter.notifyDataSetChanged();

        //item 이벤트 처리
        policyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = arrayAdapter.getItem(i);
                dialog();
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    public void dialog(){


        Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activiy_dialog);
        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        Window window = dialog.getWindow();
        window.setAttributes(lp);
        Button cancel = dialog.findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}