package com.example.youthapp.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthapp.Dialog.PolicyDialog;
import com.example.youthapp.MainActivity;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.R;

import java.util.ArrayList;

public class PolicyAdapter extends RecyclerView.Adapter<PolicyAdapter.PolicyViewHolder> {



    private ArrayList<Emp> policyList;
    Context context;
    FragmentManager fragmentManager;
    boolean starFlag = false;



    //constructor
    public PolicyAdapter(ArrayList<Emp> policyList, Context context,FragmentManager fragmentManager) {
        this.policyList = policyList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }



    @NonNull
    @Override
    public PolicyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.policy_item_list,parent,false);

        return new PolicyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PolicyViewHolder holder, int position) {


        holder.textView.setText(policyList.get(position).getPolyBizSjnm());
        holder.imageView.setImageResource(R.drawable.star_off);



        // 즐겨찾기 데이터 로드
        SharedPreferences sharedPref = context.getSharedPreferences("myListPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String policyId = policyList.get(position).getBizId();

        // 즐겨찾기 데이터 체크
        if (sharedPref.contains(policyId)){
            holder.imageView.setImageResource(R.drawable.star_on);
            starFlag = true;
        }

        // 정책 다이얼로그 창
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PolicyDialog(policyList, position).show(fragmentManager,"");
            }
        });

        // 즐겨찾기 on/off
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (starFlag == false){
                    holder.imageView.setImageResource(R.drawable.star_on);
                    starFlag = true;
                    editor.putInt(policyId, 1);
                    editor.apply();
                }
                else{
                    holder.imageView.setImageResource(R.drawable.star_off);
                    starFlag = false;
                    editor.remove(policyId);
                    editor.apply();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return policyList.size();
    }


    //ViewHolder
    class PolicyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public PolicyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageViewStar);

            /*imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (starFlag == false){
                        imageView.setImageResource(R.drawable.star_on);
                        starFlag = true;
                    }
                    else{
                        imageView.setImageResource(R.drawable.star_off);
                        starFlag = false;
                    }
                }
            });*/
        }



    }

    public void addPolicyList(ArrayList<Emp> policyList) {
        this.policyList.addAll(policyList);
    }
}
