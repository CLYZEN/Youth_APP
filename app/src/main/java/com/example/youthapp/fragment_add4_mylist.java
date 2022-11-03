package com.example.youthapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class fragment_add4_mylist extends AppCompatActivity {

    TextView textView;
    static final String[] mlist = {"청년정책", "문화활동", "일자리"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add4_mylist);

        Spinner spinner = findViewById(R.id.mylist_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, mlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(mlist[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText(mlist[0]);
            }
        });

    }


}
