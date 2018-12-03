package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Kagaribi;

public class AccountSetting extends AppCompatActivity
{
    private ConstraintLayout mainLayout;

    private TextView accountName;
    private TextView id;
    private TextView email;

    private TextView mode;

    private Kagaribi kagaribi;
    private Handler kagaribi_h;

    @SuppressLint("ClickableViewAccessibility")
    private void Initialize()
    {
        accountName = findViewById(R.id.setting_AccoutName);
        id = findViewById(R.id.setting_ID);
        email = findViewById(R.id.setting_Email);

        accountName.setText("UserName : " + MainActivity.user.getName());
        id.setText("id : " + MainActivity.user.getID() + "");
        email.setText("Email : " + MainActivity.user.getEmail());

        mainLayout = findViewById(R.id.setting_mainLayout);

        Button b = findViewById(R.id.Logout);
        b.setBackgroundColor(Color.RED);

        TextView back = findViewById(R.id.setting_Back);
        back.setBackgroundColor(Color.GREEN);
        back.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(AccountSetting.this, CalendarList.class);
                startActivity(intent);

                return false;
            }
        });

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(AccountSetting.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mode = findViewById(R.id.setting_modechange);

        if(MainActivity.night)
        {
            mode.setBackgroundColor(Color.BLACK);
            mode.setTextColor(Color.WHITE);
            mainLayout.setBackgroundColor(Color.BLACK);

            accountName.setTextColor(Color.WHITE);
            id.setTextColor(Color.WHITE);
            email.setTextColor(Color.WHITE);
        }
        else
        {
            mode.setBackgroundColor(Color.WHITE);
            mode.setTextColor(Color.BLACK);
            mainLayout.setBackgroundColor(Color.WHITE);

            accountName.setTextColor(Color.BLACK);
            id.setTextColor(Color.BLACK);
            email.setTextColor(Color.BLACK);
        }

        mode.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                MainActivity.night = ! MainActivity.night;

                if( ! MainActivity.night)
                {
                    mode.setBackgroundColor(Color.WHITE);
                    mode.setTextColor(Color.BLACK);
                    mainLayout.setBackgroundColor(Color.WHITE);

                    accountName.setTextColor(Color.BLACK);
                    id.setTextColor(Color.BLACK);
                    email.setTextColor(Color.BLACK);

                    kagaribi.close();
                }
                else
                {
                    mode.setBackgroundColor(Color.BLACK);
                    mode.setTextColor(Color.WHITE);
                    mainLayout.setBackgroundColor(Color.BLACK);

                    accountName.setTextColor(Color.WHITE);
                    id.setTextColor(Color.WHITE);
                    email.setTextColor(Color.WHITE);

                    Message message = new Message();
                    message.what = 18;
                    kagaribi_h.sendMessage(message);
                }

                return false;
            }
        });
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        Initialize();

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
                        kagaribi = new Kagaribi(AccountSetting.this, mainLayout, MainActivity.篝火);

                        if(MainActivity.night)
                        {
                            mainLayout.setBackgroundColor(Color.BLACK);
                            accountName.setTextColor(Color.WHITE);
                            id.setTextColor(Color.WHITE);
                            email.setTextColor(Color.WHITE);
                            kagaribi.open();
                        }
                }
            }
        };

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
}
