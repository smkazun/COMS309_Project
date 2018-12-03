package com.example.admin.callardar;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.admin.callardar.Classes.Event;
import com.example.admin.callardar.Classes.EventAdapter;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Event> eventItem;

    CardView card_view;

    FloatingActionButton fab,fab1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        recyclerView = findViewById(R.id.rv);
        card_view = (CardView) findViewById(R.id.card_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        eventItem = new ArrayList<Event>();

        for(int i = 0 ; i < MainActivity.user.getCalender()[CalendarList.iem].eventViewer().length ; i += 1)
        {

            eventItem.add(MainActivity.user.getCalender()[CalendarList.iem].eventViewer()[i]);
        }


//        //イベント制作テスト
//        String[] names = {"Event_Title", "Event_Title", "Event_Title", "Event_Title","Event_Title", "Event_Title", "Event_Title", "Event_Title"};
//        String[] places = {"Description", "Description", "Description", "Description","Description", "Description", "Description", "Description"};
//        String[] prices = {"11/5", "11/10", "11/23", "11/25","12/5", "12/10", "12/23", "12/25"};
//        //int[] images = {R.drawable.//, R.drawable.//, R.drawable.//, R.drawable.//};
//
//        eventItem = new ArrayList<>();
//
//        for (int i = 0; i < names.length; i++) {
//
//            eventItem.add(new Event(names[i], places[i], prices[i]));
//
//        }




        EventAdapter adapter = new EventAdapter(this, eventItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = linearLayoutManager;

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                Toast.makeText(getApplicationContext(),"Don't Touch me", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(EventActivity.this, MainActivity_Calendar.class);
                startActivity(intent);
            }
        });

        fab1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(EventActivity.this, EventAdd.class);
                startActivity(intent);
            }
        });

        CoordinatorLayout mainLayout = findViewById(R.id.event_Mainlayout);
    }

}