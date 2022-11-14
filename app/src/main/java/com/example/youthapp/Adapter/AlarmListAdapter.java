package com.example.youthapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthapp.DataBase.Policy;
import com.example.youthapp.Dialog.AlarmListDialog;
import com.example.youthapp.Dialog.PolicyDialog;
import com.example.youthapp.PolicyModel.Emp;
import com.example.youthapp.R;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {

    List<Policy> policyList;
    FragmentManager fragmentManager;

    public AlarmListAdapter(List<Policy> policyList, FragmentManager fragmentManager) {
        this.policyList = policyList;
        this.fragmentManager = fragmentManager;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.textViewTitle);
            imageView = view.findViewById(R.id.imageViewStar);


        }


    }

    @NonNull
    @Override
    public AlarmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.policy_item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmListAdapter.ViewHolder holder, int position) {
        holder.textView.setText(policyList.get(position).policyTitle);
        holder.imageView.setVisibility(View.INVISIBLE);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlarmListDialog(policyList, position).show(fragmentManager,"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return policyList.size();
    }

    public void deleteList(){
        policyList.clear();
    }

}
