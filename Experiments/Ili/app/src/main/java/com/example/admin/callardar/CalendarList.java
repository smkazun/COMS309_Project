package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.Image;
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

    private ConstraintLayout screen_CalenderCreator;
    private ConstraintLayout mainLayout;
    private ConstraintLayout friendList;
    private Button accountScreen;

    private ArrayList<User> people;

    private シルヴァホルン she1;

    @SuppressLint("ClickableViewAccessibility")
    private void Initialize()
    {
        int length = 100;
        callenDar[] arr = MainActivity.user.getCalender();

        for(int i = 0 ; i < arr.length ; i += 1)
        {
            TextView tf = Algorithm.createTextField(CalendarList.this, arr[i].toString(),0, length - 100,new RelativeLayout.LayoutParams(600,100), Color.YELLOW,(float)0.9);
            mainLayout.addView(tf);

            length += 100;
        }

        final TextView Jpanel = Algorithm.createTextField(CalendarList.this,"Create new calendar", 0, length - 100 , new RelativeLayout.LayoutParams(600, 100), Color.rgb(150,100,15), (float)0.9);

        she1 = new シルヴァホルン(new Point[]{new Point(0,length - 100), new Point(600,length - 100),new Point(600,length),new Point(0,length)});

        Jpanel.setOnTouchListener(new View.OnTouchListener()
                                  {
                                      @Override
                                      public boolean onTouch(View v, MotionEvent event)
                                      {
                                          if(she1.if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                                          {
                                              mainLayout.removeView(screen_CalenderCreator);
                                              mainLayout.addView(screen_CalenderCreator);
                                              screen_CalenderCreator.setVisibility(View.VISIBLE);
                                          }

                                          return false;
                                      }
                                  }
        );

        mainLayout.addView(Jpanel);

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

    @SuppressLint("ClickableViewAccessibility")
    private void SetComponent()
    {
        screen_CalenderCreator = findViewById(R.id.CalenderCreator);
        mainLayout = findViewById(R.id.JFrame_activity_calendar_list);
        friendList = findViewById(R.id.JFrame_FriendList);

        screen_CalenderCreator.setVisibility(View.INVISIBLE);
        screen_CalenderCreator.setBackgroundColor(Color.rgb(200,100,50));
        friendList.setVisibility(View.INVISIBLE);
        friendList.setBackgroundColor(Color.rgb(200,100,20));

        final ArrayList<TextView> text = new ArrayList<TextView>();
        final ArrayList<ImageView> pic = new ArrayList<ImageView>();
        final ArrayList<シルヴァホルン> sheruns = new ArrayList<シルヴァホルン>();
        final ArrayList<Integer> shs = new ArrayList<Integer>();

        Button create = findViewById(R.id.createCalender);
        create.setOnClickListener(new OnClick());

        Button b = findViewById(R.id.closeShow);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                friendList.setVisibility(View.INVISIBLE);
                screen_CalenderCreator.setVisibility(View.VISIBLE);

                for(int i = 0 ; i < text.size() ; i += 1)
                {
                    text.get(i).setVisibility(View.INVISIBLE);
                    pic.get(i).setVisibility(View.INVISIBLE);

                    if(sheruns.get(i).if_Usable)
                    {
                        shs.add(10);
                    }
                    else
                    {
                        shs.add(0);
                    }

                    sheruns.get(i).if_Usable = false;
                }
            }
        });

        //create the friend panel
        ImageView showFriendList = findViewById(R.id.show);
        showFriendList.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                she1.if_Usable = false;
                friendList.setVisibility(View.VISIBLE);
                screen_CalenderCreator.setVisibility(View.INVISIBLE);

                for(int i = 0 ; i < text.size() ; i += 1)
                {
                    text.get(i).setVisibility(View.VISIBLE);
                    pic.get(i).setVisibility(View.VISIBLE);

                    if(shs.get(0) == 10)
                    {
                        shs.remove(0);
                        sheruns.get(i).if_Usable = true;
                    }
                }

                if(people == null)
                {
                    people = new ArrayList<User>();
                    mainLayout.removeView(friendList);
                    mainLayout.addView(friendList);

                    for(int i = 0 ; i < text.size() ;)
                    {
                        mainLayout.removeView(text.remove(0));
                        mainLayout.removeView(pic.remove(0));
                        sheruns.remove(0);
                    }

                    int x0 = (int) friendList.getX();
                    int x1 = (int) friendList.getX() + friendList.getWidth();
                    int y0 = (int) friendList.getY();
                    int y1 = (int) friendList.getY() + friendList.getHeight() / 2;

                    int x8 = (int) friendList.getX();
                    int x9 = (int) friendList.getX() + friendList.getWidth();
                    int y8 = (int) friendList.getY() + y1 + 100;
                    int y9 = y8 + friendList.getHeight() / 2;

                    she1.if_Usable = false;

                    Algorithm.memberAddingProcess(CalendarList.this, mainLayout, new int[]{x0, x1, y0, y1}, new int[]{x8, x9, y8, y9}, 7, 3, null, MainActivity.user.getFriends(),people, pic, text, sheruns);
                }

                return false;
            }
        });
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
        people = null;
        Initialize();

        return calenda;
    }

    private class OnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //todo add user and people
            EditText tf = findViewById(R.id.name_Calender);

            String name = tf.getText().toString();
            User[] user_Admin = null;
            User[] user_ToAdd = new User[people.size()];
            user_ToAdd = people.toArray(user_ToAdd);

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

        SetComponent();
        Initialize();
    }
}
