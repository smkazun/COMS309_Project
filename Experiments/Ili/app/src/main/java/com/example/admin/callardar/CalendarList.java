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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Algorithm;
import com.example.admin.callardar.Classes.Point;
import com.example.admin.callardar.Classes.User;
import com.example.admin.callardar.Classes.callenDar;
import com.example.admin.callardar.Classes.シルヴァホルン;
import com.example.admin.callardar.Connection.AppController;
import com.example.admin.callardar.Connection.JsonRequestActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeoutException;
import java.util.jar.JarEntry;

public class CalendarList extends AppCompatActivity {

    private ConstraintLayout screen_CalenderCreator;
    private ConstraintLayout mainLayout;
    private ConstraintLayout friendList;
    private static ImageView trans;

    // pre View
    private ConstraintLayout calendar_PreView;
    private static ConstraintLayout people_PreView;
    private ImageView people_show_pre_leftArrow;
    private ImageView people_show_pre_rightArrow;
    private ImageView people_show_pre_SortingSystem;
    private シルヴァホルン she3;

    //setting
    private ImageView goToSetting;

    //settinging
    private ConstraintLayout settingSolution;
    private ImageView pic_of_the_User;
    private ImageView goToaccountSetting;
    private ImageView checkfriends;
    private シルヴァホルン she2;

    private EditText tf;

    private ArrayList<User> people;
    private ArrayList<User> admins;

    private シルヴァホルン she1;
    private Handler handler;
    private Thread t;

    private ArrayList<シルヴァホルン> she_ca;
    private ArrayList<シルヴァホルン> she_ca_going;

    @SuppressLint("ClickableViewAccessibility")
    private void Initialize()
    {
        int length = 172;

        callenDar[] arr = MainActivity.user.getCalender();
        she_ca = new ArrayList<シルヴァホルン>();
        she_ca_going = new ArrayList<シルヴァホルン>();
        TextView tf = null;
        ImageView Jpanel0 = null;

        for(int i = 0 ; i < arr.length ; i += 1)
        {
            tf = Algorithm.createTextField(CalendarList.this, arr[i].toString(),0, length,new RelativeLayout.LayoutParams(300,100), Color.YELLOW,(float)0.9);
            mainLayout.addView(tf);

            Jpanel0 = Algorithm.createJPanel(CalendarList.this, 310, length, new RelativeLayout.LayoutParams(100, 100), Color.GREEN, (float)0.5);
            mainLayout.addView(Jpanel0);
            she_ca.add(new シルヴァホルン(new Point[]{new Point(310, length), new Point(410, length), new Point(410, length + 100), new Point(310, length + 100)}));
            she_ca_going.add(new シルヴァホルン(new Point[]{new Point(0,length), new Point(300,length),new Point(300,length + 100),new Point(0,length + 100)}));

            Jpanel0.setOnTouchListener(new CalendarListener());
            tf.setOnTouchListener(new gotoCalendarListener());

            length += 100;
        }

//        if(Jpanel0 != null)
//        {
//        }

        final TextView Jpanel = Algorithm.createTextField(CalendarList.this,"Create new calendar", 0, length , new RelativeLayout.LayoutParams(300, 100), Color.rgb(150,100,15), (float)0.9);

        she1 = new シルヴァホルン(new Point[]{new Point(0,length), new Point(300,length),new Point(300,length + 100),new Point(0,length + 100)});

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
    }

    private void callendarShow_preView()
    {
        calendar_PreView = findViewById(R.id.日历预览);calendar_PreView.setBackgroundColor(Color.BLUE);
        people_PreView = findViewById(R.id.成员预览);people_PreView.setBackgroundColor(Color.rgb(170,50,90));
        people_show_pre_leftArrow = findViewById(R.id.成员预览_左箭头);Color.rgb(10,100,200);
        people_show_pre_rightArrow = findViewById(R.id.成员预览_右箭头);Color.rgb(10,100,200);
        people_show_pre_SortingSystem = findViewById(R.id.SortingSystem_PeopleShow_Pre);Color.rgb(30,10,150);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void settingSolution()
    {
        handler = new inLeakHandle();

        //setting
        goToSetting = findViewById(R.id.去设置);
        goToSetting.setX(0);
        goToSetting.setY(0);

        //settinging
        settingSolution = findViewById(R.id.SettingSolution);settingSolution.setBackgroundColor(Color.RED);
        pic_of_the_User = findViewById(R.id.头像);pic_of_the_User.setBackgroundColor(Color.RED);
        goToaccountSetting = findViewById(R.id.账号设定);goToaccountSetting.setBackgroundColor(Color.BLACK);
        checkfriends = findViewById(R.id.AllFriends);checkfriends.setBackgroundColor(Color.GREEN);

        settingSolution.setX(-settingSolution.getWidth());
        settingSolution.setY(goToSetting.getY());

        final double X_position = settingSolution.getX();
        final double Y_position = settingSolution.getY();

        goToSetting.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(settingSolution.getX() + settingSolution.getWidth() >= 1 || (she3 != null && she3.if_Usable) || trans.getVisibility() == View.VISIBLE)
                {
                    return false;
                }

                Algorithm.view_MOVE(new View[]{settingSolution}, 0, (int)settingSolution.getY());
                she2 = new シルヴァホルン(new Point[]{new Point(0, (int)settingSolution.getY()), new Point((int)settingSolution.getWidth(), (int)settingSolution.getY()), new Point((int)settingSolution.getWidth(), mainLayout.getHeight()), new Point(0, mainLayout.getHeight())});

                she1.if_Usable = false;

                for(int i = 0 ; i < she_ca.size() ; i += 1)
                {
                    she_ca.get(i).if_Usable = false;
                }

                mainLayout.removeView(trans);
                mainLayout.addView(trans);
                mainLayout.removeView(settingSolution);
                mainLayout.addView(settingSolution);
                mainLayout.removeView(goToSetting);
                mainLayout.addView(goToSetting);

                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(81);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        Message message = new Message();
                        message.what = 10;
                        handler.sendMessage(message);
                    }
                }).start();

                return false;
            }
        });

        pic_of_the_User.setBackgroundColor(Color.rgb(150,100,50));

        goToaccountSetting.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                startActivity(new Intent(CalendarList.this, AccountSetting.class));

                return false;
            }
        });

        checkfriends.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                startActivity(new Intent(CalendarList.this, FriendList.class));

                return false;
            }
        });
    }

    private class inLeakHandle extends Handler
    {
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            switch (msg.what)
            {
                case 10:
                    trans.setVisibility(View.VISIBLE);
                    break;
                case 11:
                    trans.setVisibility(View.INVISIBLE);
                    people_PreView.removeAllViews();
                    break;
                default:
                    int i = msg.what % 1000;

                    she3 = new シルヴァホルン(new Point[]{new Point(mainLayout.getWidth() - calendar_PreView.getWidth(), 0), new Point(mainLayout.getWidth(), 0), new Point(mainLayout.getWidth(), mainLayout.getHeight()), new Point(mainLayout.getWidth() - people_PreView.getWidth(), mainLayout.getHeight())});

                    she1.if_Usable = false;

                    for(int j = 0 ; j < she_ca.size() ; j += 1)
                    {
                        she_ca.get(j).if_Usable = false;
                    }

                    int[] zone = new int[]{0, people_PreView.getWidth(), 0, people_PreView.getHeight()};
                    ArrayList<ImageView> Pic = new ArrayList<ImageView>();
                    ArrayList<TextView> Tex = new ArrayList<TextView>();

                    mainLayout.removeView(trans);
                    mainLayout.addView(trans);
                    mainLayout.removeView(calendar_PreView);
                    mainLayout.addView(calendar_PreView);

                    if(MainActivity.user.getName().equals("test"))
                    {
                        Algorithm.create_ImageAndTexts(CalendarList.this, people_PreView, zone, 2, 5, null, MainActivity.user.getCalender()[i].getCurrentUser(), Pic, Tex, 0);
                    }
                    else if(MainActivity.user.getCalender()[i].getCurrentUser() != null)
                    {
                        Algorithm.create_ImageAndTexts(CalendarList.this, people_PreView, zone, 2, 5, null, MainActivity.user.getCalender()[i].getCurrentUser(), Pic, Tex, 0);
                    }

                    Algorithm.view_MOVE(new View[]{calendar_PreView}, mainLayout.getWidth() - people_PreView.getWidth(),0);

                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                Thread.sleep(101);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }

                            Message message = new Message();
                            message.what = 10;
                            handler.sendMessage(message);
                        }
                    }).start();
            }
        }
    }

    private class WindowMovement implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            int x = (int)v.getX() + (int)event.getX();
            int y = (int)v.getY() + (int)event.getY();

            if(she2 != null && settingSolution.getX() == 0 && she2.if_Usable && ! she2.if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
            {
                Algorithm.view_MOVE(new View[]{settingSolution}, -settingSolution.getWidth(), settingSolution.getY());

                she2.if_Usable = false;
                she1.if_Usable = true;

                for(int i = 0 ; i < she_ca.size() ; i += 1)
                {
                    she_ca.get(i).if_Usable = true;
                }

                trans.setVisibility(View.INVISIBLE);

                System.out.println("2 =="+ x + "y = " + y);
                return false;
            }

            if(she3 != null && calendar_PreView.getY() == 0 && she3.if_Usable && ! she3.if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
            {
                Algorithm.view_MOVE(new View[]{calendar_PreView}, calendar_PreView.getX(), -calendar_PreView.getHeight());

                she3.if_Usable = false;
                she1.if_Usable = true;
                for(int i = 0 ; i < she_ca.size() ; i += 1)
                {
                    she_ca.get(i).if_Usable = true;
                }

                MainActivity.TIME_CONTROL = new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(81);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        Message message = new Message();
                        message.what = 11;
                        handler.sendMessage(message);
                    }
                };

                MainActivity.TIME_CONTROL.start();

                System.out.println("3 =="+ x + "y = " + y);
                return false;
            }
            System.out.println("Error ==" + x + "y = " + y);
            return false;
        }
    }

    private class CalendarListener implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if((t != null && t.isAlive()) || calendar_PreView.getY() + calendar_PreView.getHeight() >= 1 || trans.getVisibility() == View.VISIBLE)
            {
                return false;
            }

            for(int i = 0 ; i < she_ca.size() ; i += 1)
            {
                if(she_ca.get(i).if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                {
                    final int index = i;

                    t = new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(MainActivity.user.getCalender()[index].getCurrentUser() == null)
                            {
                                getMessage_WithCalendar(index, t);
                            }
                            else
                            {
                                return;
                            }

                            try
                            {
                                MainActivity.TIME_CONTROL.join();
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }

                            Message message = new Message();
                            message.what = 1000 + index;
                            handler.sendMessage(message);
                        }
                    });

                    if(MainActivity.user.getCalender()[index].getCurrentUser() == null)
                    {
                        return false;
                    }
                    else
                    {
                        Message message = new Message();
                        message.what = 1000 + index;
                        handler.sendMessage(message);
                    }


                }
            }

            return false;
        }
    }
    
    private class gotoCalendarListener implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            for(int i = 0 ; i < she_ca_going.size() ; i += 1)
            {
                if(she_ca_going.get(i).if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                {
                    final int index = i;

                    t = new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(MainActivity.user.getCalender()[index].getCurrentUser() == null)
                            {
                                getMessage_WithCalendar(index, t);

                                try
                                {
                                    Thread.sleep(5000);
                                }
                                catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                            //toDO goTo the callendar, initilize event sitting
                        }
                    });

                    t.start();

                    return false;
                }
            }

            return false;
        }
    }

    private void getMessage_WithCalendar(int index, final Thread thread)
    {
        final int calendarID = MainActivity.user.getCalender()[index].getId();

        //toDO
        final String URL = "";
        final ArrayList<String> s = new ArrayList<String>();
        final JsonRequestActivity a = new JsonRequestActivity(CalendarList.this);
        final AppController C = new AppController(CalendarList.this);

        MainActivity.TIME_CONTROL = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                a.makeJsonArryReq_TIME(URL, C, s, MainActivity.TIME_CONTROL);

                try
                {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    s.get(0);
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println("TimeOut when getting data of Calendar --> " + calendarID);
                }

                //toDO
                //MainActivity.user.getCalender()[index] = null;

                thread.interrupt();
            }
        });

        MainActivity.TIME_CONTROL.start();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void SetComponent()
    {
        screen_CalenderCreator = findViewById(R.id.CalenderCreator);
        mainLayout = findViewById(R.id.JFrame_activity_calendar_list);

        mainLayout.setOnTouchListener(new WindowMovement());

        settingSolution();
        callendarShow_preView();

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

        final Button b = findViewById(R.id.closeShow);
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
                if(admins != null && admins.size() == 0)
                {
                    return false;
                }

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

                if(people == null)
                {
                    //mainLayout.setBackgroundColor(Color.BLACK);
                    people = new ArrayList<User>();

                    mainLayout.removeView(friendList);
                    mainLayout.addView(friendList);

                    sheruns1.clear();
                    sheruns2.clear();
                    pic.clear();
                    text.clear();
                    return_Pic.clear();
                    return_Text.clear();

                    friendList.removeAllViews();
                    friendList.addView(b);

                    int zone1[] = new int[]{0, friendList.getWidth(), 0, friendList.getHeight() / 2 - 50};
                    int zone2[] = new int[]{0, friendList.getWidth(), friendList.getHeight() / 2 + 50, friendList.getHeight()};

                    she1.if_Usable = false;

                    if(MainActivity.user.getFriends().length >= 1)
                    {
                        Algorithm.create_ImageAndTexts(CalendarList.this, friendList, zone1, L, H, null, MainActivity.user.getFriends(), pic, text, 0);
                        Algorithm.memberAddingProcess(CalendarList.this, friendList, zone1, zone2, L, H, MainActivity.user.getFriends(), people, return_Pic, return_Text, pic, text, sheruns1, sheruns2, 0, 0, true);
                    }
                }

                return false;
            }
        });

        ImageView showFriendList2 = findViewById(R.id.show2);
        showFriendList2.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(people == null || people.size() == 0)
                {
                    return false;
                }

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

                if(admins == null)
                {
                    //mainLayout.setBackgroundColor(Color.BLACK);
                    admins = new ArrayList<User>();

                    mainLayout.removeView(friendList);
                    mainLayout.addView(friendList);

                    sheruns1.clear();
                    sheruns2.clear();
                    pic.clear();
                    text.clear();
                    return_Pic.clear();
                    return_Text.clear();

                    friendList.removeAllViews();
                    friendList.addView(b);

                    int zone1[] = new int[]{0, friendList.getWidth(), 0, friendList.getHeight() / 2 - 50};
                    int zone2[] = new int[]{0, friendList.getWidth(), friendList.getHeight() / 2 + 50, friendList.getHeight()};

                    she1.if_Usable = false;

                    if(MainActivity.user.getFriends().length >= 1)
                    {
                        User[] peoples = new User[people.size()];
                        peoples = people.toArray(peoples);

                        Algorithm.create_ImageAndTexts(CalendarList.this, friendList, zone1, L, H, null, peoples, pic, text, 0);
                        Algorithm.memberAddingProcess(CalendarList.this, friendList, zone1, zone2, L, H, peoples, admins, return_Pic, return_Text, pic, text, sheruns1, sheruns2, 0, 0, true);
                    }
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
        //open a new screen to create the calendar
        //add people in
        //when it is done
        String URL = "http://proj309-vc-03.misc.iastate.edu:8080/calendar/new";
        ArrayList<String> s = new ArrayList<String>();
        JsonRequestActivity a = new JsonRequestActivity(CalendarList.this);
        AppController C = new AppController(CalendarList.this);

        JSONObject message = new JSONObject();

        try
        {
            message.put("id",0);
            message.put("calendarName",tf.getText().toString());
//            message.put("users",current.getCurrentUser().toString());
        }
        catch (JSONException e)
        {

        }

        a.makeJsonObjReq(URL, message, C);
    }

    private callenDar createCalendarNow(String name, User[] admins, User[] toAdd)
    {
        callenDar calenda = new callenDar(name, admins, toAdd);

        //goto callendar
        //toDO
        //this is a 仮
        MainActivity.user.addCalender(calenda);
        people = null;
        this.admins = null;
        Initialize();
        startActivity(new Intent(CalendarList.this, MainActivity_Calendar.class));
        mainLayout.setOnTouchListener(new WindowMovement());

        return calenda;
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(people == null || admins == null)
            {
                return;
            }

            //todo add user and people
            tf = findViewById(R.id.name_Calender);

            String name = tf.getText().toString();

            User[] user_ToAdd = new User[people.size()];
            user_ToAdd = people.toArray(user_ToAdd);

            admins.add(MainActivity.user);
            User[] user_Admin = new User[admins.size()];
            user_Admin = admins.toArray(user_Admin);

            screen_CalenderCreator.setVisibility(View.INVISIBLE);
            trans.setVisibility(View.INVISIBLE);

            callenDar calender = createCalendarNow(name, user_Admin, user_ToAdd);
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
