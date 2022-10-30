package com.example.youthapp;

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
                    case 0: Intent intent_0 = new Intent(getActivity(), fragment_add4_myinfo.class);
                        startActivity(intent_0);
                        break;
                    case 1: Intent intent_1 = new Intent(getActivity(), fragment_add4_mylist.class);
                        startActivity(intent_1);
                        break;
                    case 2: Intent intent_2 = new Intent(getActivity(), fragment_add4_notice.class);
                        startActivity(intent_2);
                        break;
                    case 3: Intent intent_3 = new Intent(getActivity(), fragment_add4_qna.class);
                        startActivity(intent_3);
                        break;
                    case 4: Intent intent_4 = new Intent(getActivity(), fragment_add4_signout.class);
                        startActivity(intent_4);
                        break;
                    case 5: Intent intent_5 = new Intent(getActivity(), fragment_add4_remove.class);
                        startActivity(intent_5);
                        break;
                }
            }
        });





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add4, container, false);
    }


}