package com.example.youthapp.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


import com.example.youthapp.PolicyModel.Emp;

import java.util.ArrayList;

public class PolicyDialog extends DialogFragment {

        ArrayList<Emp> policyList;
        int position;

    public PolicyDialog(ArrayList<Emp> policyList, int position) {
        this.policyList = policyList;
        this.position = position;
    }

    @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            Emp policyContent = policyList.get(position);
            builder.setTitle(policyContent.getPolyBizSjnm())
                    .setMessage("기관 및 지자체 구분 : " + policyContent.getPolyBizTy() + "\n"
                               +"정책소개 : "+policyContent.getPolyItcnCn() + "\n"
                            +"정책유형 : "+policyContent.getPlcyTpNm() + "\n"
                            +"지원규모 : "+policyContent.getSporScvl() + "\n"
                            +"지원내용 : "+policyContent.getSporCn() + "\n"
                            +"참여요건 - 연령 : "+policyContent.getAgeInfo() + "\n"
                            +"참여요건 - 취업상태 : "+policyContent.getEmpmSttsCn() + "\n"
                            +"참여요건 - 학력 : "+policyContent.getAccrRqisCn() + "\n"
                            +"참여요건 - 전공 : "+policyContent.getMajrRqisCn() + "\n"
                            +"참여요건 - 특화분야 : "+policyContent.getSplzRlmRqisCn() + "\n"
                            +"신청기관명 : "+policyContent.getCnsgNmor() + "\n"
                            +"신청기간 : "+policyContent.getRqutPrdCn() + "\n"
                            +"신청절차 : "+policyContent.getRqutProcCn() + "\n"
                            +"심사발표 : "+policyContent.getJdgnPresCn() + "\n"
                            +"사이트 링크 주소 : "+policyContent.getRqutUrla()
                    )



                    .setPositiveButton("이동하기", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // START THE GAME!
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }

}
