package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Kagaribi;
import com.example.admin.callardar.Connection.AppController;
import com.example.admin.callardar.Connection.JsonRequestActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class EventAdd extends AppCompatActivity implements OnMapReadyCallback {
    private Button select;
    private EditText title, date, time;

    private Kagaribi kagaribi;
    private Handler kagaribi_h;

    private GoogleMap mMap;
    private LatLng location;

    @SuppressLint({"ClickableViewAccessibility", "HandlerLeak"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        select = findViewById(R.id.SelectAdd);
        title = findViewById(R.id.EventAdd);
        date = findViewById(R.id.DateAdd);
        time = findViewById((R.id.TimeAdd));


        select.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                MainActivity.user.getCalender()[CalendarList.iem].Event(title.getText().toString(), date.getText().toString(), time.getText().toString(), location.latitude, location.longitude);

                String URL = "http://proj309-VC-03.misc.iastate.edu:8080/event/new/" + MainActivity.user.getCalender()[CalendarList.iem].getId();
                ArrayList<String> s = new ArrayList<String>();
                JsonRequestActivity a = new JsonRequestActivity(EventAdd.this);
                AppController C = new AppController(EventAdd.this);

                JSONObject msg = new JSONObject();

                try
                {
                    msg.put("name", title.getText().toString());
                    msg.put("x", location.latitude);
                    msg.put("y", location.longitude);
                }
                catch (JSONException e)
                {

                }

                a.makeJsonObjReq(URL, msg, C);

                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(EventAdd.this, EventActivity.class);
                startActivity(intent);

                Message message = new Message();
                message.what = 17;
                kagaribi_h.sendMessage(message);
            }
        });

        TextView back = findViewById(R.id.event_add_Back);
        back.setBackgroundColor(Color.GREEN);
        back.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(EventAdd.this, EventActivity.class);
                startActivity(intent);

                Message message = new Message();
                message.what = 17;
                kagaribi_h.sendMessage(message);

                return false;
            }
        });

        final ConstraintLayout mainLayout = findViewById(R.id.event_add_Mainlayout);

        TextView til = findViewById(R.id.event_add_Title);
        TextView tim = findViewById(R.id.event_add_Time);
        TextView dat = findViewById(R.id.event_add_Date);
        TextView poi = findViewById(R.id.event_add_Position);

        if(MainActivity.night)
        {
            mainLayout.setBackgroundColor(Color.BLACK);
            select.setBackgroundColor(Color.GREEN);
            title.setBackgroundColor(Color.GREEN);
            date.setBackgroundColor(Color.GREEN);
            time.setBackgroundColor(Color.GREEN);
            til.setTextColor(Color.WHITE);
            tim.setTextColor(Color.WHITE);
            dat.setTextColor(Color.WHITE);
            poi.setTextColor(Color.WHITE);
        }
        else
        {
            mainLayout.setBackgroundColor(Color.WHITE);
            select.setBackgroundColor(Color.WHITE);
            title.setBackgroundColor(Color.WHITE);
            date.setBackgroundColor(Color.WHITE);
            time.setBackgroundColor(Color.WHITE);
            til.setTextColor(Color.BLACK);
            tim.setTextColor(Color.BLACK);
            dat.setTextColor(Color.BLACK);
            poi.setTextColor(Color.BLACK);
        }

        kagaribi_h = new Handler()
        {
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);

                switch (msg.what)
                {
                    case 17:
                        kagaribi.close();
                        break;
                    case 18:
                        kagaribi = new Kagaribi(EventAdd.this, mainLayout, MainActivity.篝火);

                        if(MainActivity.night)
                        {
                            kagaribi.open();
                        }
                }
            }
        };

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.event_add_Map);
        mapFragment.getMapAsync(this);;

        Thread Loading = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(100000000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                Message message = new Message();
                message.what = 18;
                kagaribi_h.sendMessage(message);
            }
        });

        Loading.start();
        Loading.interrupt();
    }

    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ISU = new LatLng(42.025797, -93.646472);
        mMap.addMarker(new MarkerOptions().position(ISU).title("ISU"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ISU));
        CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(42.025797, -93.646472), 16);
        mMap.moveCamera(cUpdate);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng tapLocation)
            {
                mMap.clear();

                // tapされた位置の緯度経度
                location = new LatLng(tapLocation.latitude, tapLocation.longitude);
                String str = String.format(Locale.US, "%f, %f", tapLocation.latitude, tapLocation.longitude);
                mMap.addMarker(new MarkerOptions().position(location).title(str));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
            }
        });
    }
}

