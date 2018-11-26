package com.example.admin.callardar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    private String currentDate;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        date = findViewById(R.id.Date);
        Intent intent = getIntent();
        currentDate = intent.getStringExtra("d");
        date.setText(currentDate);


    }
}

