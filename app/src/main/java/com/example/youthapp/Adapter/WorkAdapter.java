package com.example.youthapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youthapp.R;
import com.example.youthapp.WebViewActivity;
import com.example.youthapp.WorkModel.ShowWork;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {

    private List<ShowWork> showWorks;
    Context context;
    FragmentManager fragmentManager;

    //생성자
    public WorkAdapter(List<ShowWork> showWorks, Context context, FragmentManager fragmentManager) {
        this.showWorks = showWorks;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public WorkAdapter.WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.work_item_list, parent, false);

        return new WorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.WorkViewHolder holder, int position) {
        Glide.with(context)
                .load(showWorks.get(position).getRegLogImgNm())
                .into(holder.imageViewLogo);

        holder.textViewCompayName.setText(showWorks.get(position).getEmpBusiNm());
        holder.textViewWantedTitle.setText(showWorks.get(position).getEmpWantedTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url",showWorks.get(position).getEmpWantedHomepgDetail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return showWorks.size();
    }

    //ViewHolder
    public class WorkViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewLogo;
        TextView textViewCompayName;
        TextView textViewWantedTitle;
        public WorkViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewLogo = itemView.findViewById(R.id.imageViewCompanyLogo);
            textViewCompayName = itemView.findViewById(R.id.textViewCompanyName);
            textViewWantedTitle = itemView.findViewById(R.id.textViewWantedTitle);

        }
    }
}
