package com.example.youthapp;

import android.app.Dialog;
import android.content.Context;
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
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;


public class fragment_cung1 extends Fragment {

    TextView policy_fillter;
    Spinner spinnerPolicy, spinnerLocal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cung1, container, false);

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

        String[] Data1 = {"?????? ??????","??????", "??????", "???????????????", "???????????????", "????????????"};
        String[] Data2 = {"?????? ??????","????????????","??????","??????","??????","??????","??????","??????","??????","??????","??????",
                "??????","??????","??????","??????","??????","??????","??????","??????"};


        //?????? ?????? ??????
        ArrayAdapter<String> policyAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,Data1);
        policyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerPolicy.setAdapter(policyAdapter);


        // ?????? ??????
        ArrayAdapter<String> localAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,Data2);
        localAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLocal.setAdapter(localAdapter);

        //????????? ???????????????
        Transition localTransition = new Fade();
        localTransition.addTarget(spinnerLocal);

        Transition policyTransition = new Fade();
        policyTransition.addTarget(spinnerPolicy);


        ArrayList<String> policyData = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,policyData);

        ListView policyListView = view.findViewById(R.id.policyListView);
        policyListView.setAdapter(arrayAdapter);

        //?????? ????????? ??????
        for (int i=1; i<=20; i++){
            arrayAdapter.add("??????"+i);
        }

        arrayAdapter.notifyDataSetChanged();

        //item ????????? ??????
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