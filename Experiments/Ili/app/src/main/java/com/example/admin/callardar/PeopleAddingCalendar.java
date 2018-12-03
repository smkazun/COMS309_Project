package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Algorithm;
import com.example.admin.callardar.Classes.Point;
import com.example.admin.callardar.Classes.User;
import com.example.admin.callardar.Classes.UserType;
import com.example.admin.callardar.Classes.シルヴァホルン;
import com.example.admin.callardar.Connection.AppController;
import com.example.admin.callardar.Connection.JsonRequestActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class PeopleAddingCalendar extends AppCompatActivity
{
    private ConstraintLayout mainLayout;
    private ConstraintLayout container;

    private TextView noAvi;

    private Handler handler;
    private int iem;

    private int chyet;

    private boolean add_type;
    private boolean adding;

    private ArrayList<シルヴァホルン> sherun;
    private ArrayList<ImageView> pics;
    private ArrayList<TextView> texts;

    @SuppressLint({"HandlerLeak", "ClickableViewAccessibility"})
    private void Initialize()
    {
        mainLayout = findViewById(R.id.people_adding_calendar_mainLayout);

        TextView hint = findViewById(R.id.people_adding_calendar_Hint);
        TextView choose = findViewById(R.id.people_adding_calendar_choose);
        TextView type = findViewById(R.id.people_adding_calendar_Typechoose);

        if(MainActivity.night)
        {
            mainLayout.setBackgroundColor(Color.BLACK);
            hint.setTextColor(Color.WHITE);
            choose.setTextColor(Color.WHITE);
            type.setTextColor(Color.WHITE);
        }
        else
        {
            mainLayout.setBackgroundColor(Color.WHITE);
            hint.setTextColor(Color.BLACK);
            choose.setTextColor(Color.BLACK);
            type.setTextColor(Color.BLACK);
        }

        container = findViewById(R.id.people_adding_calendar_Container);

        TextView back = findViewById(R.id.people_adding_calendar_Back);
        back.setBackgroundColor(Color.GREEN);
        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(PeopleAddingCalendar.this, MainActivity_Calendar.class);
                startActivity(intent);

                return false;
            }
        });

        final TextView admin = findViewById(R.id.people_adding_calendar_Admin);
        final TextView normal = findViewById(R.id.people_adding_calendar_Normal);
        admin.setBackgroundColor(Color.RED);
        normal.setBackgroundColor(Color.RED);

        noAvi = findViewById(R.id.people_adding_calendar_NotFound);
        noAvi.setVisibility(View.INVISIBLE);

        admin.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                adding = true;

                add_type = true;
                admin.setBackgroundColor(Color.GREEN);
                normal.setBackgroundColor(Color.RED);

                return false;
            }
        });

        normal.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                adding = false;

                add_type = false;
                admin.setBackgroundColor(Color.RED);
                normal.setBackgroundColor(Color.GREEN);

                return false;
            }
        });

        handler = new Handler()
        {
            @SuppressLint("ClickableViewAccessibility")
            public void handleMessage(Message msg)
            {
                final User[][] toAdd = {refresh()};

                mainLayout.setOnTouchListener(new View.OnTouchListener()
                {
                    private float oldX;
                    private float[] iniXs;
                    @Override
                    public boolean onTouch(View v, MotionEvent event)
                    {
                        if(event.getAction() == MotionEvent.ACTION_DOWN)
                        {
                            iniXs = new float[pics.size()];
                            oldX = v.getX() + event.getX();

                            for(int i = 0 ; i < pics.size() ; i += 1)
                            {
                                iniXs[i] = pics.get(i).getX();
                            }
                        }

                        float newX = v.getX() + event.getX();

                        final int xMax = Math.max(4, (int)(toAdd[0].length / (float)(container.getHeight() / 200)) + 1);

                        final int xOne = 160;

                        for(int i = 0 ; newX != oldX && i < pics.size() ; i += 1)
                        {
                            int min = (i % xMax) * xOne - (xMax * xOne - container.getWidth());
                            int max = (i % xMax) * xOne;

                            if(xOne * xMax < container.getWidth())
                            {

                            }
                            else if(iniXs[i] - (newX - oldX) < min)
                            {
                                pics.get(i).setX(min);
                                texts.get(i).setX(min);
                            }
                            else if(iniXs[i] - (newX - oldX) > max)
                            {
                                pics.get(i).setX(max);
                                texts.get(i).setX(max);
                            }
                            else
                            {
                                pics.get(i).setX(iniXs[i] - (newX - oldX));
                                texts.get(i).setX(iniXs[i] - (newX - oldX));
                            }
                        }

                        if(event.getAction() == MotionEvent.ACTION_UP)
                        {
                            if(xOne * xMax < container.getWidth())
                            {

                            }
                            else if(iem > 0)
                            {
                                iem = 0;
                            }
                            else if(iem < -(xMax * xOne - container.getWidth()))
                            {
                                iem = -(xMax * xOne - container.getWidth());
                            }
                            else
                            {
                                iem += (oldX - newX);
                            }
                        }

                        if(event.getAction() == MotionEvent.ACTION_UP && newX == oldX)
                        {
                            if( ! adding)
                            {
                                return false;
                            }

                            for(int i = 0 ; i < sherun.size() ; i += 1)
                            {
                                if(sherun.get(i).if_Exist(new Point((int)v.getX() + (int)event.getX() - (int)iem,(int)v.getY() + (int)event.getY())))
                                {
                                    if(chyet == i)
                                    {
                                        chyet = -1;

                                        String URL = "http://proj309-VC-03.misc.iastate.edu:8080/calendar/" + toAdd[0][i] + "/" + toAdd[0][i].getID();
                                        ArrayList<String> s = new ArrayList<String>();
                                        JsonRequestActivity a = new JsonRequestActivity(PeopleAddingCalendar.this);
                                        AppController C = new AppController(PeopleAddingCalendar.this);
                                        JSONObject msg = new JSONObject();

                                        try
                                        {
                                            msg.put("userid", toAdd[0][i].getID());
                                            msg.put("name", toAdd[0][i].getName());
                                            msg.put("email", toAdd[0][i].getEmail());

                                            if(add_type)
                                            {
                                                msg.put("usertype", "ADMIN");
                                            }
                                            else
                                            {
                                                msg.put("usertype", "NORMAL");
                                            }
                                        }
                                        catch (JSONException e)
                                        {

                                        }

                                        a.makeJsonObjReq__TIME(URL, msg, s, C, MainActivity.TIME_CONTROL);

                                        if(add_type)
                                        {
                                            MainActivity.user.getCalender()[CalendarList.iem].addUser(toAdd[0][i], UserType.Admin);
                                        }
                                        else
                                        {
                                            MainActivity.user.getCalender()[CalendarList.iem].addUser(toAdd[0][i], UserType.Normal);
                                        }

                                        toAdd[0] = refresh();
                                        iem = 0;

                                        return false;
                                    }

                                    chyet = i;
                                }
                            }
                        }

                        return true;
                    }
                });
            }
        };
    }

    private User[] refresh()
    {
        ArrayList<User> users = new ArrayList<User>();
        User[] cur = MainActivity.user.getCalender()[CalendarList.iem].getCurrentUser();

        sherun = new ArrayList<シルヴァホルン>();
        pics = new ArrayList<ImageView>();
        texts = new ArrayList<TextView>();

        container.removeAllViews();

        for(int i = 0 ; i < MainActivity.user.getFriends().length ; i += 1)
        {
            User curFriend = MainActivity.user.getFriends()[i];

            for(int j = 0 ; j < cur.length ; j += 1)
            {
                if(cur[j].equals(curFriend))
                {
                    break;
                }

                if(j + 1 == cur.length)
                {
                    users.add(curFriend);
                }
            }
        }

        int x_Max = Math.max(4, (int)(users.size() / (float)(container.getHeight() / 200)) + 1);
        User[] toAdd = new User[users.size()];
        toAdd = users.toArray(toAdd);

        Algorithm.create_ImageAndTexts_fillMode(PeopleAddingCalendar.this, container, 150, 200, x_Max, null, toAdd, pics, texts, sherun);

        if(toAdd.length == 0)
        {
            noAvi.setVisibility(View.VISIBLE);
        }

        return toAdd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_people_calendar);

        Initialize();

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
                handler.sendMessage(message);
            }
        });

        Loading.start();
        Loading.interrupt();
    }
}
