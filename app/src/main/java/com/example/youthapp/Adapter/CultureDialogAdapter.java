package com.example.youthapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youthapp.CultureModel.Styurl;
import com.example.youthapp.R;

import java.util.List;

public class CultureDialogAdapter extends RecyclerView.Adapter<CultureDialogAdapter.ViewHolder> {

    List<Styurl> styurl;
    Context context;

    public CultureDialogAdapter(List<Styurl> styurl, Context context) {
        this.styurl = styurl;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.culture_dialog_item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("url",styurl.toString());

//        Glide.with(context)
//                .load(styurl.get(position).getUrl())
//                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return styurl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView = itemView.findViewById(R.id.imageViewShowInfo);
        }
    }
}
