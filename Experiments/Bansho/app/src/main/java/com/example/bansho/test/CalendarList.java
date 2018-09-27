package com.example.bansho.test;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CalendarList extends AppCompatActivity {

    ConstraintLayout screen_CalenderCreator;
    ConstraintLayout mainLayout;
    Button accountScreen;

    private void Initialize()
    {
        int length = 100;
        callenDar[] arr = MainActivity.user.getCalender();
        screen_CalenderCreator = findViewById(R.id.CalenderCreator);
        mainLayout = findViewById(R.id.JFrame_activity_calendar_list);

        screen_CalenderCreator.setVisibility(View.INVISIBLE);
        screen_CalenderCreator.setBackgroundColor(Color.rgb(200,100,50));

        for(int i = 0 ; i < arr.length ; i += 1)
        {
            TextView tf = Algorithm.createTextField(CalendarList.this, arr[i].toString(),0, length - 100,new RelativeLayout.LayoutParams(600,100), Color.YELLOW,(float)0.9);
            mainLayout.addView(tf);

            length += 100;
        }

        TextView Jpanel = Algorithm.createTextField(CalendarList.this,"Create new calendar", 0, length - 100 , new RelativeLayout.LayoutParams(600, 100), Color.rgb(150,100,15), (float)0.9);
        Jpanel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainLayout.removeView(screen_CalenderCreator);
                mainLayout.addView(screen_CalenderCreator);
                screen_CalenderCreator.setVisibility(View.VISIBLE);
            }
        });

        mainLayout.addView(Jpanel);

        Button create = findViewById(R.id.createCalender);
        create.setOnClickListener(new OnClick());

//        accountScreen = findViewById(0);
//        accountScreen.setOnClickListener(new View.OnClickListener()
//        {
//            /**
//             * go to account setting page
//             * @param v
//             */
//            @Override
//            public void onClick(View v)
//            {
//                //toDO
//            }
//        });
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

    private callenDar createCalendarNow(String name, User[] admins, User[] toAdd)
    {
        callenDar calenda = new callenDar(name, admins, toAdd);

        //goto callendar
        //toDO
        //this is a 仮
        MainActivity.user.addCalender(calenda);
        Initialize();

        return calenda;
    }

    public class OnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //todo add user and people
            EditText tf = findViewById(R.id.name_Calender);

            String name = tf.getText().toString();
            User[] user_Admin = null;
            User[] user_ToAdd = null;

            ArrayList<User> admins = new ArrayList<User>();
            ArrayList<User> people = new ArrayList<User>();

            admins.add(MainActivity.user);

            //toDO
            //open a new screen to create the calendar
            //add people in
            //when it is done
            user_Admin = new User[admins.size()];
            user_ToAdd = new User[people.size()];
            user_Admin = admins.toArray(user_Admin);
            user_ToAdd = admins.toArray(user_ToAdd);

            screen_CalenderCreator.setVisibility(View.INVISIBLE);
            callenDar calender = createCalendarNow(name, user_Admin, user_ToAdd);
            //todo this should be delete when finished'//'
            //MainActivity.user.addCalender(calender);

            writeCalendar(calender);
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
