package com.example.admin.callardar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CalendarList extends AppCompatActivity {

    Button create;
    Button accountScreen;

    private void Initialize()
    {
        int length = 100;
        String arr = getCallingPackage();

        for(int i = 0 ; i < arr.length() ; i += 1)
        {
            Algorithm.createTextField(CalendarList.this,length - 100, 0,new RelativeLayout.LayoutParams(600,length), Color.BLACK,(float)0.9);
            length += length;
        }

        create = findViewById(0);
        create.setOnClickListener(new OnClick());

        accountScreen = findViewById(0);
        accountScreen.setOnClickListener(new View.OnClickListener()
        {
            /**
             * go to account setting page
             * @param v
             */
            @Override
            public void onClick(View v)
            {
                //toDO
            }
        });
    }

    /**
     *  get user's current event list from database
     * @return
     *  a string array, each element will contain one
     *  event
     *
     *  example: { Event1 : date , Event2 : date }
     *
     */
    private String[] getCurrentEvent()
    {
        //toDo

        return null;
    }

    /**
     *
     * write the current Calendar in to database.
     *
     */
    private void writeCalendar(callenDar current)
    {
        //toDO
    }

    private callenDar createCalendarNow(User[] admins, User[] toAdd)
    {
        callenDar calenda = new callenDar(admins, toAdd);

        //goto callendar
        //toDO

        return calenda;
    }

    public class OnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            User[] user_Admin = null;
            User[] user_ToAdd = null;

            //toDO
            //open a new screen to create the calendar
            //add people in
            //when it is done

            callenDar createdCallendar = createCalendarNow(user_Admin, user_ToAdd);
            writeCalendar(createdCallendar);
        }
    }

    public class OnTouch implements View.OnTouchListener
    {
        /**
         * using for drag screen to view bottom event
         * @param v
         * @param event
         * @return
         */

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            //toDO
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_list);

        Initialize();
    }
}
