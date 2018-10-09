package com.example.admin.downanimetion;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Scanner;

public class DownAnimetion extends AppCompatActivity {

    protected ArrayList<ImageView> arrList;
    private ArrayList<ImageView> toShift;
    protected double[] arr;

    private RelativeLayout r;
    private thread thread;
    private Handler b;
    public static long runningTime;

    //startingPosition -- color -- visibility
    public static final String MAP =
            "3 " + Color.BLACK + " 1 " + " 4 " + Color.BLACK + " 1 " +  " 2 " + Color.BLACK + " 1 " + " 4 " + Color.BLACK + " 1 " + " 2 " + Color.BLACK + " 1 "
            + "4 " + Color.BLACK + " 1 " + " 1 " + Color.BLACK + " 1 " +  " 2 " + Color.BLACK + " 1 " + " 3 " + Color.BLACK + " 1 " + " 4 " + Color.BLACK + " 1 "
            + "1 " + Color.BLACK + " 1 " + " 3 " + Color.BLACK + " 1 " +  " 2 " + Color.BLACK + " 1 " + " 1 " + Color.BLACK + " 1 " + " 1 " + Color.BLACK + " 1 "
            + "4 " + Color.BLACK + " 1 " + " 1 " + Color.BLACK + " 1 ";

    public static final int COUNT = 17;
    public static final String TIME =
                    "6.69 8.2 8.6 10.1 10.9 " +
                    "11.4 13 14.52 16.69 " +
                    "17.26 17.90 19.95 20.8 21.36 " +
                    "23.09 25.73";

    public static final int size = 80;
    public static final int firstEntry = 100;
    public static final int secondEntry = 400;
    public static final int thridEntry = 700;
    public static final int fourthEntry = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_animetion);

        Initialize();
    }

    private void Initialize()
    {
        r = findViewById(R.id.JFrame);
        thread = new thread();

        arrList = new ArrayList<ImageView>();
        arr = new double[COUNT];

        InitializeFourPoint();
        createFromString(MAP);
        ryuzyozikan(TIME);

//        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.artonelico);

        runningTime = System.currentTimeMillis();
        thread.setTimeCallBack(1);
        thread.run();
//        mediaPlayer.start();
    }

    private void InitializeFourPoint()
    {
        ImageView fir = createJPanel(size,size,firstEntry,1500, Color.rgb(150,100,5), 1);
        ImageView sec = createJPanel(size,size,secondEntry,1500, Color.rgb(150,100,5), 1);
        ImageView thi = createJPanel(size,size,thridEntry,1500, Color.rgb(150,100,5), 1);
        ImageView fou = createJPanel(size,size,fourthEntry,1500, Color.rgb(150,100,5), 1);

        OnTouch ActionListener = new OnTouch();

        fir.setOnTouchListener(ActionListener);
        sec.setOnTouchListener(ActionListener);
        thi.setOnTouchListener(ActionListener);
        fou.setOnTouchListener(ActionListener);

        r.addView(fir);
        r.addView(sec);
        r.addView(thi);
        r.addView(fou);
    }

    private class OnTouch implements View.OnTouchListener
    {
        private int count;

        public boolean onTouch(View v, MotionEvent event)
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                for(int i = count ; i < toShift.size() ; i++)
                {
                    if (toShift.get(i).getY() >= 1500 - size)
                    {
                        toShift.get(i).setAlpha((float) 0);
                        count += 1;
                    }
                }
            }

            return false;
        }
    }

    private ImageView createJPanel(int x, int y, int startingX, int startingY, int color, float visibility)
    {
        ImageView j = new ImageView(DownAnimetion.this);
        j.setLayoutParams(new RelativeLayout.LayoutParams(x,y));
        j.setBackgroundColor(color);
        j.setAlpha(visibility);

        j.setX(startingX);
        j.setY(startingY);

        return j;
    }

    /**
     *
     *  s1 = StartingPosition(1 - 4)
     *  s2 = color.rgb
     *  s3 = visibility
     *
     * @param s
     */

    private void createFromString(String s)
    {
        Scanner scanner = new Scanner(s);

        while(scanner.hasNextInt())
        {
            switch (scanner.nextInt())
            {
                case 1:
                    arrList.add(createJPanel(size,size,firstEntry,0,scanner.nextInt(),scanner.nextFloat()));
                    break;
                case 2:
                    arrList.add(createJPanel(size,size,secondEntry,0,scanner.nextInt(),scanner.nextFloat()));
                    break;
                case 3:
                    arrList.add(createJPanel(size,size,thridEntry,0,scanner.nextInt(),scanner.nextFloat()));
                    break;
                case 4:
                    arrList.add(createJPanel(size,size,fourthEntry,0,scanner.nextInt(),scanner.nextFloat()));
                    break;
            }
        }
    }

    private void ryuzyozikan(String s)
    {
        Scanner scanner = new Scanner(s);

        for(int i = 0; scanner.hasNextDouble(); i += 1)
        {
            arr[i] = scanner.nextDouble() - 3.5;
        }
    }

    private class thread implements Runnable
    {
        private int index;
        private int time;
        private Handler b;

        public thread()
        {
            b = new Handler();
            toShift = new ArrayList<ImageView>();
        }

        public void run()
        {
            if(time == 0)
            {
                return;
            }

            b.postDelayed(thread, time);

           long currentTime = System.currentTimeMillis() - runningTime;
            double inSecond = (double) currentTime / 1000.0;
            System.out.println("inScond = " + inSecond);

            for(int i = 0 ; i < toShift.size() ; i += 1)
            {
                ImageView JPanel = toShift.get(i);

                JPanel.setX(JPanel.getX());
                JPanel.setY(JPanel.getY() + 10);
            }

            if(index < COUNT)
            {
                while (inSecond > arr[index])
                {
                    r.addView(arrList.get(index));
                    toShift.add(arrList.get(index));
                    index += 1;

                    if(index == COUNT)
                    {
                        break;
                    }
                }
            }
        }

        public void setTimeCallBack(int timeCallBack)
        {
            time = timeCallBack;
        }

        public void removeTimeCallBack()
        {
            b.removeCallbacks(thread);
        }

        public void sleep(int sleepingTime)
        {
            try
            {
                Thread.sleep(sleepingTime);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
