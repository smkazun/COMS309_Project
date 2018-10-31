package com.example.bansho.testingcard;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Event> eventItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        recyclerView = findViewById(R.id.rv);

        //イベント制作テスト
        String[] names = {"Event_Title", "Event_Title", "Event_Title", "Event_Title"};
        String[] places = {"Description", "Description", "Description", "Description"};
        String[] prices = {"11/5", "11/10", "11/23", "11/25"};
        //int[] images = {R.drawable.//, R.drawable.//, R.drawable.//, R.drawable.//};

        eventItem = new ArrayList<>();


        for (int i = 0; i < names.length; i++) {

            eventItem.add(new Event(names[i], places[i], prices[i]));

        }



        EventAdapter adapter = new EventAdapter(this, eventItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = linearLayoutManager;

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



    }

}
