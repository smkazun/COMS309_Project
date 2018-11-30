package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.callardar.Classes.Algorithm;
import com.example.admin.callardar.Classes.Event;
import com.example.admin.callardar.Classes.User;
import com.example.admin.callardar.Classes.callenDar;
import com.example.admin.callardar.Connection.AppController;
import com.example.admin.callardar.Connection.JsonRequestActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    protected static int X;
    protected static int Y;

    private ConstraintLayout mainLayout;

    private Handler handler;
    private Handler handler_Message;

    private EditText account;
    private EditText password;
    private EditText CREATE_account;
    private EditText CREATE_password;
    private EditText CREATE_email;

    private ConstraintLayout CREATE_layout;
    private ConstraintLayout login;
    private ImageView transparent_CREATE_user;

    private TextView wrongMessage;

    protected static User user;

    private Button LOGIN;
    protected static Thread TIME_CONTROL;
    private Thread ToKoShiE;

//        Algorithm.Stop stop = new Algorithm.Stop(2000);
//        FutureTask<Integer> task = new FutureTask<Integer>(stop);
//        new Thread(task).start();
//        try {
//            task.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    /**
     *  get from database to determine the current account
     *  and passWord if is match, if it is true, add the
     *  calendar to the user by calling user.addCalender()
     *
     * @param account
     * @param passWord
     * @return
     *  return true if match
     */
    private boolean ifExist(final String account, final String passWord)
    {
        X = mainLayout.getWidth();
        Y = (int) (mainLayout.getHeight() * 0.9);

        if(account.equals("") && passWord.equals(""))
        {
            user = new User(100, "test","@");

            User[] arr = new User[100];

            for(int i = 0 ; i < 100 ; i += 1)
            {
                arr[i] = new User(i, "illiand" + i, "!");
            }

            user.addFriends(arr);
            user.addCalender(new callenDar(10, "TestCalldar",user, arr, arr));

            return true;
        }

        String URL = "http://proj309-VC-03.misc.iastate.edu:8080/users/all";
        ArrayList<JSONArray> JArr = new ArrayList<JSONArray>();

        JsonRequestActivity a = new JsonRequestActivity(MainActivity.this);
        AppController C = new AppController(MainActivity.this);
        a.makeJsonArryReq_object_TIME(URL, C, JArr, TIME_CONTROL);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {

        }

        try
        {
            for(int i = 0 ; i < JArr.get(0).length() ; i += 1)
            {
                int id = JArr.get(0).getJSONObject(i).getInt("userid");
                String name = JArr.get(0).getJSONObject(i).getString("name");
                String email = JArr.get(0).getJSONObject(i).getString("email");

                if( ! name.equals(account))// || ! passs.get(i).equals(passWord))
                {
                    if(i + 1 == JArr.get(0).length())
                    {
                        return false;
                    }

                    continue;
                }
System.out.println(id + "+ " + name);
                user = new User(id, name, email);
                break;
            }
        }
        catch (IndexOutOfBoundsException | JSONException e)
        {
            System.out.println("Time Out in log in");
            return false;
        }

        //toDO friend list
        URL = "http://proj309-VC-03.misc.iastate.edu:8080/users/all";
        JArr = new ArrayList<JSONArray>();
        a = new JsonRequestActivity(MainActivity.this);
        C = new AppController(MainActivity.this);
        a.makeJsonArryReq_object_TIME(URL, C, JArr, TIME_CONTROL);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {

        }

        try
        {
            JArr.get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Time Out in adding friend list");
            return false;
        }

        User[] toAdd = new User[JArr.get(0).length()];

        try
        {
            for(int i = 0 ; i < toAdd.length ; i += 1)
            {
                int id = JArr.get(0).getJSONObject(i).getInt("userid");
                String name = JArr.get(0).getJSONObject(i).getString("name");
                String email = JArr.get(0).getJSONObject(i).getString("email");

                toAdd[i] = new User(id, name, email);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        user.addFriends(toAdd);

        URL = "http://proj309-vc-03.misc.iastate.edu:8080/users/calendars/" + user.getID();
        ArrayList<JSONArray> jArr = new ArrayList<JSONArray>();
        a = new JsonRequestActivity(MainActivity.this);
        C = new AppController(MainActivity.this);
        a.makeJsonArryReq_object_TIME(URL, C, jArr, TIME_CONTROL);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {

        }

        try
        {
             jArr.get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Time Out in adding calendar");
            return false;
        }

        try
        {
            for(int i = 0 ; i < jArr.get(0).length() ; i += 1)
            {
                int id = jArr.get(0).getJSONObject(i).getInt("calendarid");

                URL = "http://proj309-vc-03.misc.iastate.edu:8080/calendar/" + id;
                ArrayList<JSONObject> JObj = new ArrayList<JSONObject>();
                a = new JsonRequestActivity(MainActivity.this);
                C = new AppController(MainActivity.this);

                a.makeJsonObjReq_GET_TIME(URL, new JSONObject(), JObj, C, TIME_CONTROL);

                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {

                }

                try
                {
                    String name = JObj.get(0).getString("calendarname");
                    user.addCalender(new callenDar(id, name));
                }
                catch (IndexOutOfBoundsException e)
                {
                    user.addCalender(new callenDar(id, "DEFAULT"));
                    System.out.println("Time Out in adding calendar --> process getting name");
                    continue;
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return true;
    }

    private class CreateNewUser implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            mainLayout.removeView(transparent_CREATE_user);
            mainLayout.addView(transparent_CREATE_user);
            transparent_CREATE_user.setVisibility(View.VISIBLE);

            mainLayout.removeView(CREATE_layout);
            mainLayout.addView(CREATE_layout);
            CREATE_layout.setVisibility(View.VISIBLE);
        }
    }

    private void Initialize()
    {
        Button create = findViewById(R.id.CREATE);
        create.setOnClickListener(new CreateNewUser());
    }

    private class OnClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            TIME_CONTROL = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    if (ifExist(account.getText().toString(), password.getText().toString()))
                    {
                        startActivity(new Intent(MainActivity.this, CalendarList.class));

                        Message message = new Message();
                        message.what = 17;
                        handler.sendMessage(message);
                    }
                    else
                    {
                        System.out.println(account.getText().toString() + " + " + password.getText().toString());
                        Message message = new Message();
                        message.what = 10;
                        handler.sendMessage(message);
                    }
                }
            });

            TIME_CONTROL.start();
        }
    }

    private class inLeakHandle extends Handler
    {
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            if(msg .what == 10)
            {
                wrongMessage.setVisibility(View.VISIBLE);
                password.setText(new char[]{}, 0, 0);
                handler.removeCallbacksAndMessages(null);
            }
            else if(msg.what == 17)
            {
                UnStopped_Message_Thread();
            }
        }
    }

    private void UnStopped_Message_Thread()
    {
        if(user.getName().equals("test"))
        {
            return;
        }

        ToKoShiE = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    ArrayList<callenDar> calendar = new ArrayList<callenDar>();

                    String URL = "http://proj309-VC-03.misc.iastate.edu:8080/users/" + user.getID();
                    ArrayList<JSONObject> JObj = new ArrayList<JSONObject>();

                    JsonRequestActivity a = new JsonRequestActivity(MainActivity.this);
                    AppController C = new AppController(MainActivity.this);
                    a.makeJsonObjReq_GET_TIME(URL,new JSONObject(), JObj, C, ToKoShiE);

                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    try
                    {
                        JObj.get(0);
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        continue;
                    }

                    try
                    {
                        JSONArray arr = JObj.get(0).getJSONArray("calendars");

                        for(int i = 0 ; i < arr.length() ; i += 1)
                        {
                            int cid = arr.getJSONObject(i).getInt("calendarid");
                            String cname = arr.getJSONObject(i).getString("calendarname");
                            JSONArray events = arr.getJSONObject(i).getJSONArray("events");

                            callenDar cal = new callenDar(cid, cname);

                            for(int j = 0 ; j < events.length() ; j += 1)
                            {
                                int eid = events.getJSONObject(j).getInt("id");
                                String ename = events.getJSONObject(j).getString("name");

                                cal.Event(eid, ename,"N/A", "N/A");
                            }

                            calendar.add(cal);
                        }

                        for(int i = 0 ; i < calendar.size() ; i += 1)
                        {
                            for(int j = 0 ; j < user.getCalender().length ; j += 1)
                            {
                                if(calendar.get(i).equals(user.getCalender()[j]) == 0)
                                {
                                    break;
                                }

                                if(calendar.get(i).equals(user.getCalender()[j]) == 1)
                                {
                                    if(j + 1 != user.getCalender().length)
                                    {
                                        continue;
                                    }
                                    else
                                    {
                                        user.addCalender(calendar.get(i));

                                        Message message = new Message();
                                        message.what = 1;
                                        message.obj = calendar.get(i).toString();
                                        handler.sendMessage(message);
                                        break;
                                    }
                                }

                                if(calendar.get(i).equals(user.getCalender()[j]) == 2)
                                {
                                    for(; user.getCalender()[j].eventViewer().length != 0 ;)
                                    {
                                        user.getCalender()[j].deleteEvent(0);
                                    }

                                    for(int k = 0 ; k < calendar.get(i).eventViewer().length ; k += 1)
                                    {
                                        Event toAdd = calendar.get(i).eventViewer()[k];

                                        user.getCalender()[j].Event(toAdd.id, toAdd.getItem_title(), toAdd.getItem_desc(), toAdd.getItem_date());
                                    }

                                    Message message = new Message();
                                    message.what = 2;
                                    message.obj = calendar.get(i).toString();
                                    handler.sendMessage(message);
                                    break;
                                }
                            }
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                    try
                    {
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        ToKoShiE.start();
    }

    @SuppressLint({"ClickableViewAccessibility", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new inLeakHandle();

        mainLayout = findViewById(R.id.JFrame_activity_main);
        login = findViewById(R.id.LOGIN);

        LOGIN = findViewById(R.id.button_Login);
        account = findViewById(R.id.acc);
        password = findViewById(R.id.pass);

        wrongMessage = findViewById(R.id.WrongMessage);
        wrongMessage.setVisibility(View.INVISIBLE);

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        LOGIN.setOnClickListener(new OnClick());

        CREATE_account = findViewById(R.id.CREATE_User_Name);
        CREATE_password = findViewById(R.id.CREATE_User_Password);
        CREATE_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        CREATE_email = findViewById(R.id.CREATE_User_Email);

        CREATE_layout = findViewById(R.id.createNewUser);
        CREATE_layout.setBackgroundColor(Color.rgb(200,150,50));
        CREATE_layout.setVisibility(View.INVISIBLE);

        transparent_CREATE_user = findViewById(R.id.transparent_CREATE_User);
        transparent_CREATE_user.setBackgroundColor(Color.BLACK);
        transparent_CREATE_user.setAlpha((float) 0.8);
        transparent_CREATE_user.setVisibility(View.INVISIBLE);

        Button create = findViewById(R.id.CREATE);
        create.setOnClickListener(new CreateNewUser());
        Button create_DOWN = findViewById(R.id.CREATE_OVER);
        create_DOWN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String URL = "http://proj309-VC-03.misc.iastate.edu:8080/users/new";
                JSONObject message = new JSONObject();
                AppController C = new AppController(MainActivity.this);
                JsonRequestActivity a = new JsonRequestActivity(MainActivity.this);

                try
                {
                    message.put("id",0);
                    message.put("name",CREATE_account.getText().toString());
                    message.put("password",CREATE_password.getText().toString());
                    message.put("email",CREATE_email.getText().toString());
                }
                catch (JSONException e)
                {

                }

                a.makeJsonObjReq(URL, message, C);

                transparent_CREATE_user.setVisibility(View.INVISIBLE);
                mainLayout.removeView(CREATE_layout);
                mainLayout.addView(login);

                user = new User(0, account.toString(),"@");
                startActivity(new Intent(MainActivity.this,CalendarList.class));
            }
        });

        TextView close = findViewById(R.id.CREATE_close);
        close.setBackgroundColor(Color.GREEN);
        close.setOnTouchListener(new View.OnTouchListener()
        {
            private float x;
            private float y;

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                x = event.getX();
                y = event.getY();

                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    if(event.getX() == x && event.getY() == y)
                    {
                        CREATE_account.setText("");
                        CREATE_password.setText("");
                        CREATE_email.setText("");
                        CREATE_layout.setVisibility(View.INVISIBLE);
                        transparent_CREATE_user.setVisibility(View.INVISIBLE);
                    }
                }

                return true;
            }
        });

        handler_Message = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch(msg.what)
                {
                    case 1:
                        Notification.Builder notifybuider = new Notification.Builder(MainActivity.this);
                        notifybuider.setContentTitle("New Calendar has been created")
                                .setSubText((String)msg.obj)
                                .setTicker("New calendar")
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                                .setAutoCancel(true);

                        Notification notify = notifybuider.build();
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(1,notify);

                        break;
                    case 2:
                        notifybuider = new Notification.Builder(MainActivity.this);

                        notifybuider.setContentTitle("New Event has been added")
                                .setSubText("In " + (String) msg.obj)
                                .setTicker("New Event")
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                                .setAutoCancel(true);

                        notify = notifybuider.build();
                        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(1,notify);

                        break;
                }

                msg = null;
            }
        };
    }
}
