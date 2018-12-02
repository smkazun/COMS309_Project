package com.example.admin.callardar;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.callardar.Classes.CalendarAdapter;
import com.example.admin.callardar.Classes.UserType;

public class MainActivity_Calendar extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView titleText;
    private Button prevButton, nextButton, Event_B,Chat;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;

    private Button calendar_back;
    private Button calendar_add;

    private Handler h;

    @SuppressLint("HandlerLeak")
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
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MainActivity_Calendar.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        Event_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(MainActivity_Calendar.this, EventActivity.class);
                startActivity(intent);
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

        LinearLayout mainLayout = findViewById(R.id.calendar_Mainlayout);
        TextView mon = findViewById(R.id.calendar_Mon);
        TextView tue = findViewById(R.id.calendar_Tue);
        TextView wed = findViewById(R.id.calendar_Wed);
        TextView thu = findViewById(R.id.calendar_Thu);
        TextView fri = findViewById(R.id.calendar_Fri);


//        if(MainActivity.night)
//        {
//            calendar_back.setTextColor(Color.BLACK);
//            mainLayout.setBackgroundColor(Color.BLACK);
//            titleText.setBackgroundColor(Color.BLACK);
//            titleText.setTextColor(Color.WHITE);
//            mon.setTextColor(Color.WHITE);
//            thu.setTextColor(Color.WHITE);
//            tue.setTextColor(Color.WHITE);
//            fri.setTextColor(Color.WHITE);
//            wed.setTextColor(Color.WHITE);
//        }
//        else
//        {
//            calendar_back.setTextColor(Color.BLACK);
//            mainLayout.setBackgroundColor(Color.WHITE);
//            titleText.setBackgroundColor(Color.WHITE);
//            titleText.setTextColor(Color.BLACK);
//            mon.setTextColor(Color.BLACK);
//            thu.setTextColor(Color.BLACK);
//            tue.setTextColor(Color.BLACK);
//            fri.setTextColor(Color.BLACK);
//            wed.setTextColor(Color.BLACK);
//        }

        final Button delete = findViewById(R.id.calendar_Delete);
        delete.setBackgroundColor(Color.BLUE);

        if(MainActivity.user.getType(MainActivity.user.getCalender()[CalendarList.iem]) == UserType.Normal)
        {
            calendar_add.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
        }

        h = new Handler()
        {
            public void handleMessage(Message msg)
            {
                delete.setBackgroundColor(Color.BLUE);
            }
        };

        delete.setOnClickListener(new View.OnClickListener()
        {
            private long sysTime = System.currentTimeMillis();
            private int count = -1;

            @Override
            public void onClick(View v)
            {

                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        while(true)
                        {
                            if(System.currentTimeMillis() - sysTime >= 200)
                            {
                                h.sendMessage(new Message());
                                count = -1;

                                break;
                            }
                        }
                    }
                }).start();

                if(System.currentTimeMillis() - sysTime < 200)
                {
                    count += 1;
                    sysTime = System.currentTimeMillis();

                    switch (count)
                    {
                        case 0:
                            delete.setBackgroundColor(Color.GREEN);
                            break;
                        case 1:
                            delete.setBackgroundColor(Color.YELLOW);
                            break;
                        case 2:
                            delete.setBackgroundColor(Color.RED);
                            break;
                        case 3:
                            MainActivity.user.deleteCalendar(CalendarList.iem);

                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(MainActivity_Calendar.this, CalendarList.class);
                            startActivity(intent);

                            break;
                    }

                    return;
                }

                count = 0;
                sysTime = System.currentTimeMillis();

                delete.setBackgroundColor(Color.GREEN);
            }
        });

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


