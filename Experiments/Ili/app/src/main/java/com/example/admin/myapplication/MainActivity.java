package com.example.admin.myapplication;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Button up;
    private Button down;
    private Button left;
    private Button right;

    private TextView tf;

    private thread timer_CountDown;

    private void Initialize()
    {
        up = findViewById(R.id.button_up);
        down = findViewById(R.id.button_down);
        left = findViewById(R.id.button_left);
        right = findViewById(R.id.button_right);

        tf = findViewById(R.id.test);

        ActionListener al = new ActionListener();

        up.setOnTouchListener(al);
        down.setOnTouchListener(al);
        left.setOnTouchListener(al);
        right.setOnTouchListener(al);

        timer_CountDown = new thread(new Timer());
        timer_CountDown.run();
    }

    private class ActionListener implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch (v.getId())
            {
                case R.id.button_up:
                    tf.setText("up" +  timer_CountDown.getRunningTime());
                    return true;

                case R.id.button_down:
                    tf.setText("down"+  timer_CountDown.getRunningTime());
                    return true;

                case R.id.button_left:
                    tf.setText("left"+  timer_CountDown.getRunningTime());
                    return true;

                case R.id.button_right:
                    tf.setText("right"+  timer_CountDown.getRunningTime());
                    return true;

                default:
                    return false;
            }
        }
    }

    private class Timer implements Runnable
    {
        @Override
        public void run()
        {

        }
    }

    private class thread extends Thread
    {
        private long runningTime;

        public thread(Runnable Run)
        {
            super(Run);
        }

        public void run()
        {
            super.run();

            runningTime = System.currentTimeMillis();
        }

        public long getRunningTime()
        {
            return System.currentTimeMillis() - runningTime;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize();
    }
}
