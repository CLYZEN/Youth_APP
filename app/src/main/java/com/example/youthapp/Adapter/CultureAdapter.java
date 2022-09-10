package com.example.youthapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youthapp.Dialog.CultureDialog;
import com.example.youthapp.CultureModel.ShowInfoRoot;
import com.example.youthapp.CultureModel.ShowList;
import com.example.youthapp.CultureModel.ShowInfo;
import com.example.youthapp.R;
import com.example.youthapp.Service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CultureAdapter extends RecyclerView.Adapter<CultureAdapter.CultureViewHolder> {

    ArrayList<ShowList> cultureList;
    Context context;
    FragmentManager fragmentManager;

    Call<ShowInfoRoot> call;
    ShowInfo showInfo;

    //생성자
    public CultureAdapter(ArrayList<ShowList> cultureList, Context context, FragmentManager fragmentManager) {
        this.cultureList = cultureList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }



    @NonNull
    @Override
    public CultureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.culture_item_list,parent,false);


        return new CultureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CultureViewHolder holder, int position) {



        //공연 제목
        holder.textView.setText(cultureList.get(position).getPrfnm().toString());
        //공연 포스터
        Glide.with(context)
                .load(cultureList.get(position).getPoster())
                .into(holder.imageView);



    }//onBindViewHolder

    @Override
    public int getItemCount() {
        return cultureList.size();
    }


    public class CultureViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;

        public CultureViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTitle2);
            imageView = itemView.findViewById(R.id.imageViewPoster);
            cardView = itemView.findViewById(R.id.cardViewCulture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    call = RetrofitInstance.getCultureSearchService().getService(cultureList.get(getAdapterPosition()).getMt20id());//cultureList.get(position).getMt20id()
                    if(call != null){
                        call.enqueue(new Callback<ShowInfoRoot>() {
                            @Override
                            public void onResponse(Call<ShowInfoRoot> call, Response<ShowInfoRoot> response) {

                                showInfo = response.body().getShowInfo();
                                Log.i("TAG","TEST"+showInfo.getPrfage());
                                new CultureDialog(showInfo).show(fragmentManager,"");

                            }


                            @Override
                            public void onFailure(Call<ShowInfoRoot> call, Throwable t) {
                                Log.e("error","onFailure"+t.getMessage());
                            }
                        });
                    }
                    else{
                        Log.i("call",""+call.toString());
                    }
                }
            });
        }
    }
}
