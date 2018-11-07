package com.example.admin.callardar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.callardar.Classes.CalendarAdapter;

import java.util.Date;
import java.util.List;

public class MainActivity_Calendar extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView titleText;
    private Button prevButton, nextButton, Event_B,list;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);

        Event_B =  findViewById(R.id.Event);
        list =  findViewById(R.id.list);
        titleText = findViewById(R.id.titleText);
        prevButton = findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.prevMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.nextMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        calendarGridView = findViewById(R.id.calendarGridView);
        mCalendarAdapter = new CalendarAdapter(this);
        calendarGridView.setAdapter(mCalendarAdapter);
        titleText.setText(mCalendarAdapter.getTitle());

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Don't Touch me", Toast.LENGTH_LONG).show();
            }
        });

        Event_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_Calendar.this, EventActivity.class);
                startActivity(i);
            }
        });

//        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//
//                int i = position;
//
//                Toast.makeText(getApplicationContext(),"its " + i, Toast.LENGTH_LONG).show();
//                Intent k = new Intent(MainActivity_Calendar.this, EventActivity.class);
//                startActivity(k);
//                // DO something
//
//            }
//        });
        calendarGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.d("position", String.valueOf(position));
//        Log.d("date", mCalendarAdapter.getItem(position).toString());
        Intent intent = new Intent(getApplicationContext(), DayActivity.class);
        intent.putExtra("date", mCalendarAdapter.getItem(position).toString());
        startActivity(intent);
    }
}


