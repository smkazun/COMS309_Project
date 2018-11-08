package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Event;
import com.example.admin.callardar.Classes.EventAdapter;
import com.example.admin.callardar.Connection.AppController;
import com.example.admin.callardar.Connection.JsonRequestActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventDetailActivity extends AppCompatActivity {
    private TextView title, date, time;
    private int i;

    ArrayList<Event> eventView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        title = (TextView) findViewById(R.id.Event_Title);
        date = (TextView) findViewById(R.id.Date_detail);
        time = (TextView) findViewById(R.id.Time_detail);

        TextView t = findViewById(R.id.event_Deleto);
        t.setBackgroundColor(Color.BLUE);

        t.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    String URL = "http://proj309-VC-03.misc.iastate.edu:8080/event/delete/" + "TODO";
                    ArrayList<String> s = new ArrayList<String>();
                    JsonRequestActivity a = new JsonRequestActivity(EventDetailActivity.this);
                    AppController C = new AppController(EventDetailActivity.this);

                    JSONObject msg = new JSONObject();

                    try
                    {
                        msg.put("name", title.getText().toString());
                    }
                    catch (JSONException e)
                    {

                    }

                    a.makeJsonObjReq(URL, msg, C);

                   MainActivity.user.getCalender()[CalendarList.iem].deleteEvent(EventAdapter.position);
                   startActivity(new Intent(EventDetailActivity.this, EventActivity.class));
                }

                return true;
            }
        });

//        Intent intent = getIntent();
//        i = intent.getIntExtra("e",0);
//
//        eventView.add(MainActivity.user.getCalender()[CalendarList.iem].eventViewer()[i]);
//
    }
}
