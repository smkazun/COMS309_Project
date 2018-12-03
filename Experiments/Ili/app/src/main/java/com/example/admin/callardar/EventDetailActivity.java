package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Locale;

import com.example.admin.callardar.Classes.Event;
import com.example.admin.callardar.Classes.EventAdapter;
import com.example.admin.callardar.Connection.AppController;
import com.example.admin.callardar.Connection.JsonRequestActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class EventDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView title, date, time;
    private int i;

    private GoogleMap mMap;
    private LatLng location;
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
        t.setBackgroundColor(Color.GRAY);

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

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);;
    }

    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        Event e = MainActivity.user.getCalender()[CalendarList.iem].eventViewer()[EventAdapter.position];

        // Add a marker in Sydney and move the camera
        LatLng ISU = new LatLng(e.x, e.y);
        mMap.addMarker(new MarkerOptions().position(ISU).title(e.getItem_title()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ISU));
        CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(e.x, e.y), 16);
        mMap.moveCamera(cUpdate);
    }
}
