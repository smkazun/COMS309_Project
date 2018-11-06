package com.example.admin.callardar;

import android.content.Intent;
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
import com.example.admin.callardar.Classes.EventAdd;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Event> eventItem;

    CardView card_view;

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        recyclerView = findViewById(R.id.rv);
        card_view = (CardView) findViewById(R.id.card_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);




        //イベント制作テスト
        String[] names = {"Event_Title", "Event_Title", "Event_Title", "Event_Title","Event_Title", "Event_Title", "Event_Title", "Event_Title"};
        String[] places = {"Description", "Description", "Description", "Description","Description", "Description", "Description", "Description"};
        String[] prices = {"11/5", "11/10", "11/23", "11/25","12/5", "12/10", "12/23", "12/25"};
        //int[] images = {R.drawable.//, R.drawable.//, R.drawable.//, R.drawable.//};

        eventItem = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {

            eventItem.add(new Event(names[i], places[i], prices[i]));

        }

        //



        EventAdapter adapter = new EventAdapter(this, eventItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = linearLayoutManager;

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Don't Touch me", Toast.LENGTH_LONG).show();
                Intent i = new Intent(EventActivity.this, EventAdd.class);
                startActivity(i);
            }
        });

//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Don't Touch me", Toast.LENGTH_LONG).show();
//            }
//        });

//                Toast.makeText(getApplicationContext(),"Don't Touch me", Toast.LENGTH_LONG).show();


    }

}