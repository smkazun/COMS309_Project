package com.example.admin.callardar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.admin.callardar.Classes.CalendarAdapter;

public class MainActivity_Calendar extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView titleText;
    private Button prevButton, nextButton, Event_B,Chat;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;

    private Button calendar_back;
    private Button calendar_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);

        Event_B =  findViewById(R.id.Event);
        Chat =  findViewById(R.id.list);
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

        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_Calendar.this, ChatActivity.class);
                startActivity(i);
            }
        });

        Event_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_Calendar.this, EventActivity.class);
                startActivity(i);
            }
        });

        calendar_add = findViewById(R.id.calendar_add);
        calendar_back = findViewById(R.id.calendar_back);

        calendar_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MainActivity_Calendar.this, PeopleAddingCalendar.class);
                startActivity(intent);
            }
        });

        calendar_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MainActivity_Calendar.this, CalendarList.class);
                startActivity(intent);
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
        intent.putExtra("d", mCalendarAdapter.getItem(position).toString());
        startActivity(intent);
    }
}


