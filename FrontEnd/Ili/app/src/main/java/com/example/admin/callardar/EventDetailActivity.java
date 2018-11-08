package com.example.admin.callardar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Event;

import java.util.ArrayList;

public class EventDetailActivity extends AppCompatActivity {
    private TextView title, date, time;
    private int i;

    ArrayList<Event> eventView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetail);
        title = (TextView) findViewById(R.id.Event_Title);
        date = (TextView) findViewById(R.id.Date_detail);
        time = (TextView) findViewById(R.id.Time_detail);

//        Intent intent = getIntent();
//        i = intent.getIntExtra("e",0);
//
//        eventView.add(MainActivity.user.getCalender()[CalendarList.iem].eventViewer()[i]);
//
    }
}
