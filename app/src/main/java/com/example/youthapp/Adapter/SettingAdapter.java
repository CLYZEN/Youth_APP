package com.example.youthapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youthapp.Dialog.InfoRemoveDialog;
import com.example.youthapp.FragmentAdd.Fragment_add4_alarm;
import com.example.youthapp.FragmentAdd.Fragment_add4_alarmList;
import com.example.youthapp.FragmentAdd.Fragment_add4_myinfo;
import com.example.youthapp.Fragment_add4_notice;
import com.example.youthapp.LoginActivity;
import com.example.youthapp.R;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    private String[] settingTitle;
    private FragmentActivity fragmentActivity;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textViewTitle);
        }

        public TextView getTextView() {
            return textView;
        }
    }


    public SettingAdapter(String[] settingTitle, FragmentActivity fragmentActivity) {
        this.settingTitle = settingTitle;
        this.fragmentActivity = fragmentActivity;
    }




    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.policy_item_list, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.setText(settingTitle[position]);

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {


                if (viewHolder.textView.getText().toString() == "회원정보 수정"){
                    intent = new Intent(fragmentActivity, Fragment_add4_myinfo.class);
                    fragmentActivity.startActivity(intent);
                }



                if (viewHolder.textView.getText().toString() == "알림 설정"){
                    intent = new Intent(fragmentActivity, Fragment_add4_alarm.class);
                    fragmentActivity.startActivity(intent);

                }

                if (viewHolder.textView.getText().toString().contains("알림 목록")){
                    intent = new Intent(fragmentActivity, Fragment_add4_alarmList.class);
                    fragmentActivity.startActivity(intent);

                }

                if (viewHolder.textView.getText().toString() == "로그아웃"){
                    SharedPreferences sharedPreferences = fragmentActivity.getSharedPreferences("AutoLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    intent = new Intent(fragmentActivity, LoginActivity.class);
                    fragmentActivity.startActivity(intent);
                    fragmentActivity.finish();
                }

                if (viewHolder.textView.getText().toString() == "회원탈퇴"){
                    InfoRemoveDialog infoRemoveDialog = new InfoRemoveDialog();
                    infoRemoveDialog.show(fragmentActivity.getSupportFragmentManager(), "remove");
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return settingTitle.length;
    }
}