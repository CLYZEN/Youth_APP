package com.example.youthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


public class fragment_add4 extends Fragment {

    static final String[] list = {"내 정보", "내 리스트", "공지사항", "문의하기", "로그아웃", "회원탈퇴"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add4, container, false);

        ListView listView = getView().findViewById(R.id.add4_listview);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);
                switch (i){
                    case 0: Intent intent_myinfo = new Intent(getActivity(), fragment_add4_myinfo.class);
                        startActivity(intent_myinfo);
                        break;
                    case 1: Intent intent_mylist = new Intent(getActivity(), fragment_add4_mylist.class);
                        startActivity(intent_mylist);
                        break;
                    case 2: Intent intent_notice = new Intent(getActivity(), fragment_add4_notice.class);
                        startActivity(intent_notice);
                        break;
                    case 3: Intent intent_qna = new Intent(Intent.ACTION_SEND);
                        intent_qna.setType("plain/text");
                        String[] email = {"youthapp@gmail.com"};
                        intent_qna.putExtra(Intent.EXTRA_EMAIL, email);
                        intent_qna.putExtra(Intent.EXTRA_SUBJECT, "문의 제목: ");
                        intent_qna.putExtra(Intent.EXTRA_TEXT, "문의 내용: ");
                        startActivity(intent_qna);
                        break;
                    case 4: signout();
                        break;
                    case 5: Intent intent_remove = new Intent(getActivity(), fragment_add4_remove.class);
                        startActivity(intent_remove);
                        break;
                }
            }
            void signout(){
                AlertDialog builder = new AlertDialog.Builder(getActivity())
                        .setTitle("Sign out")
                        .setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent out = new Intent(getActivity(), LoginActivity.class);
                                out.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(out);
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        })
                        .show();
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add4, container, false);
    }


}