package com.example.admin.callardar.Classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

//篝(かがり)火
public class 篝火
{
    private boolean isRunning = true;

    private Handler h;
    private int[][] pixels;
    private Context context;

    private ArrayList<ImageView> views;
    private ArrayList<Integer> speeds;
    private ArrayList<Integer> speedCopy;

    private Thread Moving;

    @SuppressLint("HandlerLeak")
    public 篝火(Context classDOTthis, final ConstraintLayout layout, final int[][] pixels)
    {
        h = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case 1:
                        Package p = (Package) msg.obj;
                        int x = p.a;
                        int y = p.b;
                        int length = p.c;
                        int height = p.d;
                        int speed = p.e;
                        float visibility = p.f;
                        Bitmap bitmap = p.bitmap;

                        ImageView view = Algorithm.createJPanel(context, x, y, new RelativeLayout.LayoutParams(length, height), 8753, visibility);
                        layout.addView(view);
                        view.setImageBitmap(bitmap);

                        Random r = new Random();
                        float position = (float)(r.nextInt(30) + 70) / (float) 100;

                        view.setZ(position);

                        views.add(view);
                        speeds.add(speed);
                        speedCopy.add(speed);

                        break;
                    case -1:
                        while(views.size() != 0)
                        {
                            layout.removeView(views.get(0));
                            views.remove(0);
                            speeds.remove(0);
                        }

                        break;
                    case 7:
                        view = (ImageView)msg.obj;
                        view.setY(view.getY() - 3);

                        r = new Random();
                        int next = r.nextInt(5);

                        if(next == 1)
                        {
                            view.setX(view.getX() - (float)1.5);
                        }
                        else if(next == 2)
                        {
                            view.setX(view.getX() + (float)1.5);
                        }


                       ((ImageView)msg.obj).setAlpha(((ImageView)msg.obj).getAlpha() - (float)0.005);

                        break;
                    case 8:
                        p = (Package) msg.obj;

                        p.imageView.setImageBitmap(p.bitmap);
                }

                if(msg.what >= 1000)
                {
                    try
                    {
                        layout.removeView(views.get(msg.what % 1000));
                    }
                    catch (IndexOutOfBoundsException e)
                    {

                    }

                    ((Thread)msg.obj).interrupt();
                }

                msg = null;
            }
        };

        context = classDOTthis;

        views = new ArrayList<ImageView>();
        speeds = new ArrayList<Integer>();
        speedCopy = new ArrayList<Integer>();
        this.pixels = pixels;
    }

    public void open()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(isRunning)
                {
                    Bitmap bitmap = Bitmap.createBitmap(pixels[0].length, pixels.length, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();

                    for(int i = 0 ; i < pixels.length ; i += 1)
                    {
                        for(int j = 0 ; j < pixels[0].length ; j += 1)
                        {
                            int A = Color.alpha(pixels[i][j]);
                            int R = Color.red(pixels[i][j]);
                            int G = Color.green(pixels[i][j]);
                            int B = Color.blue(pixels[i][j]);

                            if(pixels[i][j] == -1)
                            {
                                A = 0;
                            }

                            paint.setARGB(A, R, G, B);
                            paint.setStyle(Paint.Style.FILL);
                            canvas.drawPoint(i, j, paint);
                        }
                    }

                    Random r = new Random();

                    int x = r.nextInt(1280);
                    int y = r.nextInt(1000) + 1000;
                    int length = pixels[0].length;
                    int height = pixels.length;
                    float visibility = ((float)r.nextInt(3753) + (float)5000) / (float)10000;
                    int speed = r.nextInt(10) + 5;

                    Package p = new Package(x, y, bitmap, length, height, speed, visibility);

                    Message message = new Message();
                    message.obj = p;
                    message.what = 1;
                    h.sendMessage(message);

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Moving = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                long sysTime = System.currentTimeMillis();

                while(isRunning)
                {
                    for(int i = 0 ; i < views.size() ; i += 1)
                    {
                        try
                        {
                            for(int j = 0 ; j < views.size() ; j += 1)
                            {
                                long curTime = System.currentTimeMillis();

                                speeds.set(j, speeds.get(j) - (int)(curTime - sysTime));
                            }

                            sysTime = System.currentTimeMillis();

                            if(speeds.get(i) > 0)
                            {
                                continue;
                            }

                            final ImageView view = views.get(i);
                            speeds.set(i, speedCopy.get(i));

                            Message message = new Message();
                            message.what = 7;
                            message.obj = view;
                            h.sendMessage(message);

                            if(view.getY() + view.getHeight() <= 0 || view.getAlpha() <= 0)
                            {
                                message = new Message();
                                message.obj = Moving;
                                message.what = 1000 + i;
                                h.sendMessage(message);

                                try
                                {
                                    Thread.sleep(1000000);
                                }
                                catch (InterruptedException e)
                                {

                                }

                                views.remove(i);
                                speeds.remove(i);
                                speedCopy.remove(i);

                                i -= 1;
                            }
                        }
                        catch (IndexOutOfBoundsException e)
                        {

                        }
                    }
                }
            }
        });

        Moving.start();
    }

    public void close()
    {
        isRunning = false;
        Message message = new Message();
        message.what = -1;
        h.sendMessage(message);
    }

    private class Package
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        float f;
        Bitmap bitmap;
        ImageView imageView;

        public Package(int a, int b, Bitmap bitmap, int c, int d, int e, float f)
        {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
            this.bitmap = bitmap;
        }
    }
}