package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Algorithm;
import com.example.admin.callardar.Classes.Point;
import com.example.admin.callardar.Classes.User;
import com.example.admin.callardar.Classes.callenDar;
import com.example.admin.callardar.Classes.シルヴァホルン;

import java.util.ArrayList;

public class CalendarList extends AppCompatActivity {

    private ConstraintLayout screen_CalenderCreator;
    private ConstraintLayout mainLayout;
    private ConstraintLayout friendList;
    private Button accountScreen;
    private ImageView trans;

    private ArrayList<TextView> calT;

    private ArrayList<User> people;
    private ArrayList<User> admins;

    private シルヴァホルン she1;

    @SuppressLint("ClickableViewAccessibility")
    private void Initialize()
    {
        int length = 100;
        calT = new ArrayList<TextView>();
        callenDar[] arr = MainActivity.user.getCalender();

        for(int i = 0 ; i < arr.length ; i += 1)
        {
            TextView tf = Algorithm.createTextField(CalendarList.this, arr[i].toString(),0, length - 100,new RelativeLayout.LayoutParams(600,100), Color.YELLOW,(float)0.9);
            mainLayout.addView(tf);
            calT.add(tf);

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
                                              mainLayout.removeView(trans);
                                              mainLayout.addView(trans);
                                              mainLayout.removeView(screen_CalenderCreator);
                                              mainLayout.addView(screen_CalenderCreator);
                                              screen_CalenderCreator.setVisibility(View.VISIBLE);
                                              trans.setVisibility(View.VISIBLE);
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
        trans = findViewById(R.id.Transparent);
        trans.setBackgroundColor(Color.BLACK);
        trans.setAlpha((float)0.8);
        trans.setVisibility(View.INVISIBLE);

        screen_CalenderCreator.setVisibility(View.INVISIBLE);
        screen_CalenderCreator.setBackgroundColor(Color.rgb(200,100,50));
        friendList.setVisibility(View.INVISIBLE);
        friendList.setBackgroundColor(Color.rgb(200,100,20));

        final ArrayList<TextView> text = new ArrayList<TextView>();
        final ArrayList<ImageView> pic = new ArrayList<ImageView>();
        final ArrayList<TextView> return_Text = new ArrayList<TextView>();
        final ArrayList<ImageView> return_Pic = new ArrayList<ImageView>();
        final ArrayList<シルヴァホルン> sheruns1 = new ArrayList<シルヴァホルン>();
        final ArrayList<シルヴァホルン> sheruns2 = new ArrayList<シルヴァホルン>();

        final ArrayList<Integer> she1_f = new ArrayList<Integer>();
        final ArrayList<Integer> she2_f = new ArrayList<Integer>();

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

                she1_f.clear();
                she2_f.clear();

                for(int i = 0 ; i < sheruns1.size() ; i += 1)
                {
                    if(sheruns1.get(i).if_Usable)
                    {
                        sheruns1.get(i).if_Usable = false;
                        she1_f.add(10);
                    }
                    else
                    {
                        she1_f.add(0);
                    }
                }

                for(int i = 0 ; i < sheruns2.size() ; i += 1)
                {
                    if(sheruns2.get(i).if_Usable)
                    {
                        sheruns2.get(i).if_Usable = false;
                        she2_f.add(10);
                    }
                    else
                    {
                        she2_f.add(0);
                    }
                }
                for(int i = 0 ; i < pic.size() ; i += 1)
                {
                    text.get(i).setVisibility(View.INVISIBLE);
                    pic.get(i).setVisibility(View.INVISIBLE);
                }
            }
        });

        //create the friend panel
        final int L = 4;
        final int H = 3;

        ImageView showFriendList = findViewById(R.id.show);
        showFriendList.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                she1.if_Usable = false;
                friendList.setVisibility(View.VISIBLE);
                screen_CalenderCreator.setVisibility(View.INVISIBLE);

                for(int i = 0 ; i < sheruns1.size() ; i += 1)
                {
                    if(she1_f.get(i) == 10)
                    {
                        sheruns1.get(i).if_Usable = true;
                    }
                }
                for(int i = 0 ; i < sheruns2.size() ; i += 1)
                {
                    if(she2_f.get(i) == 10)
                    {
                        sheruns2.get(i).if_Usable = true;
                    }
                }

                for(int i = 0 ; i < pic.size() ; i++)
                {
                    text.get(i).setVisibility(View.VISIBLE);
                    pic.get(i).setVisibility(View.VISIBLE);
                }

                if(people == null)
                {
                    //mainLayout.setBackgroundColor(Color.BLACK);

                    ImageView j1 = findViewById(R.id.arrow_Left_up);
                    ImageView j2 = findViewById(R.id.arrow_Right_Up);
                    changeTOnext cto1 = new changeTOnext(false, L * H);
                    changeTOnext cto2 = new changeTOnext(true, L * H);

                    j1.setOnTouchListener(cto1);
                    j2.setOnTouchListener(cto2);

                    people = new ArrayList<User>();
                    admins = new ArrayList<User>();

                    mainLayout.removeView(friendList);
                    mainLayout.addView(friendList);


                    for(int i = 0 ; i < sheruns1.size() ;)
                    {
                        sheruns1.remove(0);
                    }
                    for(int i = 0 ; i < sheruns2.size() ;)
                    {
                        sheruns2.remove(0);
                    }

                    for(int i = 0 ; i < pic.size() ;)
                    {
                        //toDO
                        text.get(0).setVisibility(View.INVISIBLE);
                        text.remove(0);
                      //  mainLayout.removeView(text.remove(0));
                        mainLayout.removeView(pic.remove(0));
                    }
                    for(int i = 0 ; i < return_Pic.size() ;)
                    {
                        return_Text.get(0).setVisibility(View.INVISIBLE);
                        return_Text.remove(0);
                    //    mainLayout.removeView(return_Text.remove(0));
                        mainLayout.removeView(return_Pic.remove(0));
                    }

                    int x0 = (int) friendList.getX();
                    int x1 = (int) friendList.getX() + friendList.getWidth();
                    int y0 = (int) friendList.getY();
                    int y1 = (int) friendList.getY() + friendList.getHeight() / 2;

                    int x8 = (int) friendList.getX();
                    int x9 = (int) friendList.getX() + friendList.getWidth();
                    int y8 = (int) friendList.getY() + friendList.getHeight() / 2  + 100;
                    int y9 = (int) friendList.getY() + friendList.getHeight();

                    she1.if_Usable = false;

                    Algorithm.create_ImageAndTexts(CalendarList.this, mainLayout, new int[]{x0, x1, y0, y1}, L, H, null, MainActivity.user.getFriends(), pic, text, cto1.index);
                    Algorithm.memberAddingProcess(CalendarList.this, mainLayout, new int[]{x0, x1, y0, y1}, new int[]{x8, x9, y8, y9}, L, H, MainActivity.user.getFriends(),people, return_Pic, return_Text, pic, text, sheruns1, sheruns2, cto1.index, cto2.index);
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
        admins = null;
        Initialize();

        return calenda;
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //todo add user and people
            EditText tf = findViewById(R.id.name_Calender);

            String name = tf.getText().toString();

            User[] user_ToAdd = new User[people.size()];
            user_ToAdd = people.toArray(user_ToAdd);

            admins.add(MainActivity.user);
            User[] user_Admin = new User[admins.size()];
            user_Admin = admins.toArray(user_Admin);


            //toDO
            //open a new screen to create the calendar
            //add people in
            //when it is done
            String URL = null;
            ArrayList<String> s = new ArrayList<String>();

//            Method_Connection.makeStringReq_POST(URL, null, s);

            screen_CalenderCreator.setVisibility(View.INVISIBLE);
            trans.setVisibility(View.INVISIBLE);
            callenDar calender = createCalendarNow(name, user_Admin, user_ToAdd);
            MainActivity.user.addCalender(calender);

            writeCalendar(calender);
        }
    }

    private class changeTOnext implements View.OnTouchListener
    {
        private int index;
        private boolean way;
        private boolean isRunning;

        private final int count;

        private changeTOnext(boolean way, int ONE)
        {
            this.way = way;
            count = ONE;
            isRunning = true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if( ! isRunning)
            {
                return false;
            }

            if(way)
            {
                index += count;
            }
            else
            {
                index = Math.max(0, index - count);
            }

            return false;
        }

        public void setRunning(boolean run)
        {
            isRunning = run;
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
