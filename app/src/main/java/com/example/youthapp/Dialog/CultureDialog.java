package com.example.youthapp.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youthapp.Adapter.CultureDialogAdapter;
import com.example.youthapp.CultureModel.ShowInfo;
import com.example.youthapp.CultureModel.ShowInfoRoot;
import com.example.youthapp.R;
import com.example.youthapp.Service.CultureSearchService;
import com.example.youthapp.Service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CultureDialog extends DialogFragment {

    ShowInfo showInfo;
    RecyclerView recyclerView;
    CultureDialogAdapter adapter;
    //생성자
    public CultureDialog(ShowInfo showInfo) {
        this.showInfo = showInfo;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction


//        Log.i("TAG","ddd"+showInfo.getPrfage());
//        recyclerView = getActivity().findViewById(R.id.recyclerviewCultureDialog);
//        adapter = new CultureDialogAdapter(showInfo.getStyurls().getUrls(), getActivity());
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
//        .setView(layoutInflater.inflate(R.layout.custom_culture_dialog,null))
        builder
                .setTitle(showInfo.getPrfnm())//
                .setMessage("공연 기간 : "+showInfo.getPrfpdfrom()+" ~ "+showInfo.getPrfpdto()+"\n\n"
                                +"공연장 : " + showInfo.getFcltynm()+"\n\n"
                                +"공연 런타임 : " + showInfo.getPrfruntime()+"\n\n"
                                +"공연시간 : " + showInfo.getDtguidance()+"\n\n"
                                +"티켓가격 : " + showInfo.getPcseguidance()+"\n\n"
                                 +"관람 연령 : " + showInfo.getPrfage()+"\n\n"
                                +"출연진 : " + showInfo.getPrfcast()+"\n\n"
                                +"제작진 : " + showInfo.getPrfcrew()+"\n\n"
                                +"제작사 : " + showInfo.getEntrpsnm()+"\n"
                       )
//
                .setPositiveButton("이동하기", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}