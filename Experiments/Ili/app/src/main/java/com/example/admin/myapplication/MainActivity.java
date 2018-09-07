package com.example.admin.myapplication;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.Image;
import android.media.JetPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Button up;
    private Button down;
    private Button left;
    private Button right;

    private TextView tf;
    private TextView consulor;

    private thread timer_CountDown;

    private void Initialize()
    {
        up = findViewById(R.id.button_up);
        down = findViewById(R.id.button_down);
        left = findViewById(R.id.button_left);
        right = findViewById(R.id.button_right);

        tf = findViewById(R.id.test);
        consulor = findViewById(R.id.consulor);

        ActionListener al = new ActionListener();

        up.setOnTouchListener(al);
        down.setOnTouchListener(al);
        left.setOnTouchListener(al);
        right.setOnTouchListener(al);

        timer_CountDown = new thread(new Timer());
        ImageView JPanel1 = findViewById(R.id.image1);
        ImageView JPanel2 = findViewById(R.id.image2);
        JPanel1.setColorFilter(Color.rgb(50,100,150));
        JPanel2.setColorFilter(Color.GREEN);
    //    JPanel.setBackgroundColor(Color.rgb(50,100,150));
    //    JPanel.getDrawingRect(new Rect(10,10,30,30));

        MoveListener ml = new MoveListener();

        JPanel1.setOnTouchListener(ml);
        JPanel2.setOnTouchListener(ml);

        JPanel1.setAlpha((float)1);
    }

    private class ActionListener implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch (v.getId())
            {
                case R.id.button_up:
                    if(event.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        timer_CountDown.run();
                        consulor.setText("pressed ");
                    }

                    tf.setText("up " +  timer_CountDown.getRunningTime());

                    if(event.getAction() == MotionEvent.ACTION_UP)
                    {
                        timer_CountDown.interrupt();
                        consulor.setText("interupted");
                    }

                    break;

                case R.id.button_down:
                    tf.setText("down"+  timer_CountDown.getRunningTime());
                    break;

                case R.id.button_left:
                    tf.setText("left"+  timer_CountDown.getRunningTime());
                    break;

                case R.id.button_right:
                    tf.setText("right"+  timer_CountDown.getRunningTime());
                    break;
            }

            return true;
        }
    }

    private class MoveListener implements View.OnTouchListener
    {
        private float oldX;
        private float oldY;
        private float iniX;
        private float iniY;

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                int[] arr = new int[2];
                v.getLocationOnScreen(arr);

                oldX = v.getX() + event.getX();
                oldY = v.getY() + event.getY();
                iniX = v.getX();
                iniY = v.getY();
            }

            float newX = v.getX() + event.getX();
            float newY = v.getY() + event.getY();

            float x = iniX + (newX - oldX);
            float y = iniY + (newY - oldY);

            v.setX(x);
            v.setY(y);

            return true;
        }
    }

    private class Timer implements Runnable
    {
        @Override
        public void run()
        {
            consulor.setText("Start");
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

        public void interrupt()
        {
            super.interrupt();
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
