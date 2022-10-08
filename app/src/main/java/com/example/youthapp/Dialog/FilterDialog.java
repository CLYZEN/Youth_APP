package com.example.youthapp.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.youthapp.R;
import com.example.youthapp.SearchActivity;
import com.example.youthapp.fragment_cung1;
import com.example.youthapp.fragment_cung1_1;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterDialog extends DialogFragment {


    public interface FilterDialogListener{
        public void onLoadLocalData(ArrayList<Fragment> fragmentArrayList, String LocalDataCode, String LocalDataString);
    }

    FilterDialogListener Filterlistener;

    public void setFilterListener(FilterDialogListener listener) {
        this.Filterlistener = listener;
    }







    String[] LocalDataString = {"지역 전체","서울","부산","대구","인천","광주","대전","울산","경기","강원",
            "충북","충남","전북","전남","경북","경남","제주","세종"};
    String[] LocalDataCode = {"","003002001","003002002","003002003","003002004","003002005","003002006",
            "003002007","003002008","003002009","003002010","003002011","003002012","003002013","003002014",
    "003002015","003002016","003002017"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("지역선택")
                .setItems(LocalDataString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        // 각각의 지역 정보를 담는 새로운 프래그먼트 3개 생성
                        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
                        fragmentArrayList.add(new fragment_cung1_1("004003",LocalDataCode[which]));
                        fragmentArrayList.add(new fragment_cung1_1("004004",LocalDataCode[which]));
                        fragmentArrayList.add(new fragment_cung1_1("004001",LocalDataCode[which]));
                        fragmentArrayList.add(new fragment_cung1_1("004002,004005,004006",LocalDataCode[which]));
                        Filterlistener.onLoadLocalData(fragmentArrayList, LocalDataCode[which], LocalDataString[which]);
                        Toast.makeText(getContext(), LocalDataString[which]+" 선택", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
