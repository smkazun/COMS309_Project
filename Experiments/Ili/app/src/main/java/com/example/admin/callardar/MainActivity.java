package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Algorithm;
import com.example.admin.callardar.Classes.User;
import com.example.admin.callardar.Classes.callenDar;
import com.example.admin.callardar.Connection.AppController;
import com.example.admin.callardar.Connection.JsonRequestActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    protected static int X;
    protected static int Y;

    private ConstraintLayout mainLayout;

    private static Handler handler;

    private EditText account;
    private static EditText password;
    private EditText CREATE_account;
    private EditText CREATE_password;

    private ConstraintLayout CREATE_layout;
    private ConstraintLayout login;
    private ImageView transparent_CREATE_user;

    private static TextView wrongMessage;

    protected static User user;

    private Button LOGIN;
    protected static Thread TIME_CONTROL;

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
            user.addCalender(new callenDar(10,"TestCalldar", arr, arr));

            return true;
        }

        String URL = "http://proj309-VC-03.misc.iastate.edu:8080/users/all";
        ArrayList<String> s = new ArrayList<String>();
        JsonRequestActivity a = new JsonRequestActivity(MainActivity.this);
        AppController C = new AppController(MainActivity.this);
        a.makeJsonArryReq_TIME(URL, C, s, TIME_CONTROL);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {

        }

        try
        {
            ArrayList<String> ids = Algorithm.ifExistWithAdd_SpecialMode_INT("userid", s.get(0));
            ArrayList<String> names = Algorithm.ifExistWithAdd("name", s.get(0));
            ArrayList<String> email = Algorithm.ifExistWithAdd("email", s.get(0));
            ArrayList<String> passs = Algorithm.ifExistWithAdd("passWord", s.get(0));

            for(int i = 0 ; i < ids.size() ; i += 1)
            {
                if( ! names.get(i).equals(account))// || ! passs.get(i).equals(passWord))
                {
                    continue;
                }

                user = new User(Integer.parseInt(ids.get(i)), names.get(i), email.get(i));
                break;
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Time Out in log in");
            return false;
        }

        //toDO friend list
        URL = "http://proj309-VC-03.misc.iastate.edu:8080/users/all";
        s = new ArrayList<String>();
        a = new JsonRequestActivity(MainActivity.this);
        C = new AppController(MainActivity.this);
        a.makeJsonArryReq_TIME(URL, C, s, TIME_CONTROL);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {

        }

        String s0 = "";

        try
        {
            s0 = s.get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Time Out in adding friend list");
            return false;
        }

        s = Algorithm.ifExistWithAdd("name", s0);
        String[] arr = new String[s.size()];
        User[] toAdd = new User[s.size()];

        arr = s.toArray(arr);

        for(int i = 0 ; i < arr.length ; i += 1)
        {
            toAdd[i] = new User(i, arr[i], "Default");
        }

        user.addFriends(toAdd);

        //toDO calladar list
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

        s0 = "";

        try
        {
              System.out.println(jArr.get(0).toString());
//            System.out.println(jArr.get(0).get(0).toString());
//            System.out.println(jArr.get(0).getJSONObject(0).getJSONArray("users").toString());
//            System.out.println(jArr.get(0).getJSONObject(0).getJSONArray("users").getJSONObject(0).getInt("userid"));

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

                user.addCalender(new callenDar(id, "Default"));
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
            mainLayout.removeView(login);
            mainLayout.addView(CREATE_layout);
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

    private static class inLeakHandle extends Handler
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
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new inLeakHandle();
//        {
//            public void handleMessage(Message msg)
//            {
//                super.handleMessage(msg);
//
//                if(msg .what == 10)
//                {
//                    wrongMessage.setVisibility(View.VISIBLE);
//                    password.setText(new char[]{}, 0, 0);
//                    handler.removeCallbacksAndMessages(null);
//                }
//            }
//        };

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

        CREATE_layout = findViewById(R.id.createNewUser);
        CREATE_layout.setBackgroundColor(Color.rgb(200,150,50));

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
                    message.put("email","1111");
                    message.put("userType","1111");
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

        mainLayout.removeView(CREATE_layout);
    }
}
