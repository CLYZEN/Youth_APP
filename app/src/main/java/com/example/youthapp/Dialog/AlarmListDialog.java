package com.example.youthapp.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.youthapp.DataBase.Policy;
import com.example.youthapp.R;
import com.example.youthapp.WebViewActivity;

import java.util.List;


public class AlarmListDialog extends DialogFragment {
    List<Policy> policyList;
    int position;
    public AlarmListDialog(List<Policy> policyList, int position) {
        this.policyList = policyList;
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(policyList.get(position).policyTitle)
                .setMessage(policyList.get(position).policyContent)
                .setPositiveButton("이동하기", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (policyList.get(position).policyLink.contains("http")){
                            Intent intent = new Intent(getContext(), WebViewActivity.class);
                            intent.putExtra("url",policyList.get(position).policyLink);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(), "이동할 사이트가 없습니다.", Toast.LENGTH_SHORT).show();
                        }
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