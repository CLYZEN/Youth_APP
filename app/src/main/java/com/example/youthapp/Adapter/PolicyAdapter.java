package com.example.youthapp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthapp.Dialog.PolicyDialog;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.R;

import java.util.ArrayList;

public class PolicyAdapter extends RecyclerView.Adapter<PolicyAdapter.PolicyViewHolder> {

    private ArrayList<Emp> policyList;
    Context context;
    FragmentManager fragmentManager;
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
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PolicyDialog(policyList, position).show(fragmentManager,"");
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

        public PolicyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTitle);

        }

    }
}
