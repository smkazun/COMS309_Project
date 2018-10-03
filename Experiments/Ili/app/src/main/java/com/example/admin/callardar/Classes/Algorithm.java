package com.example.admin.callardar.Classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Algorithm
{
    private Algorithm()
    {
        //do nothing
    }

    /**
     *
     *  move a View to the assigned place automatically
     *
     * @param v
     * @param x_TO
     * @param y_TO
     */
    public static void view_MOVE(View[] v, final float x_TO, final float y_TO)
    {
        new view_MOVE(v, v[0].getX(), v[0].getY(), x_TO, y_TO);
    }

    /**
     * Call it when need it
     * @param classDOTthis
     * @param Layout
     * @param Zone1
     * @param Zone2
     * @param X_number
     * @param Y_number
     * @param addForView
     * @param toAdd
     * @param return_Value
     * @param views_Pic
     * @param views_Tex
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void memberAddingProcess(Object classDOTthis, Object Layout, int[] Zone1, int[] Zone2, final int X_number, final int Y_number, final String[] addForView, final User[] toAdd, final ArrayList<User> return_Value, final ArrayList<ImageView> views_Pic, final ArrayList<TextView> views_Tex, final ArrayList<シルヴァホルン> sheruns)
    {
        final int left_X_start = Zone1[0];
        final int right_X_start = Zone1[1];
        final int up_Y_start = Zone1[2];
        final int down_Y_start = Zone1[3];
        final int left_X_end = Zone2[0];
        final int right_X_end = Zone2[1];
        final int up_Y_end = Zone2[2];
        final int down_Y_end = Zone2[3];

        final int x_ONE = (right_X_start - left_X_start) / X_number - 10;
        final int y_ONE = (down_Y_start - up_Y_start) / Y_number - 50;

        final int x_endBy = (right_X_end - left_X_end) / x_ONE;
        final int y_endBy = (down_Y_end - up_Y_end) / y_ONE;

        final ArrayList<String> pictures = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        final ArrayList<User> copy_User= new ArrayList<User>();
        final ArrayList<ImageView> copy_Pic = new ArrayList<ImageView>();
        final ArrayList<TextView> copy_Tex = new ArrayList<>();
        final ArrayList<シルヴァホルン> copy_sheruns = new ArrayList<シルヴァホルン>();
        final ArrayList<ImageView> Time_Control = new ArrayList<ImageView>();

        ConstraintLayout layout = (ConstraintLayout) Layout;

        for(int i = 0; i < toAdd.length ; i += 1)
        {
            if(addForView != null)
            {
                pictures.add(addForView[i]);
            }

            name.add(toAdd[i].getName());

            //create zone,logic
            int scale_X = i % X_number;
            int scale_Y = i / X_number;
            Point p1 = new Point(left_X_start + scale_X * (x_ONE + 10),up_Y_start + scale_Y * (y_ONE + 50));
            Point p2 = new Point(left_X_start + (x_ONE + 10) + scale_X * (x_ONE + 10), up_Y_start + scale_Y * (y_ONE + 50));
            Point p3 = new Point(left_X_start + (x_ONE + 10) + scale_X * (x_ONE + 10), up_Y_start + (y_ONE + 50) + scale_Y * (y_ONE + 50));
            Point p4 = new Point(left_X_start + scale_X * (x_ONE + 10),up_Y_start + (y_ONE + 50) + scale_Y * (y_ONE + 50));

            sheruns.add(new シルヴァホルン(new Point[]{p1, p2, p3, p4}));
            copy_sheruns.add(new シルヴァホルン(new Point[]{p1, p2, p3, p4}));
            //create vision
            Random r = new Random();
            ImageView v = Algorithm.createJPanel((Context)classDOTthis, p1.x, p1.y, new RelativeLayout.LayoutParams(x_ONE, y_ONE), Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255)), 1);
            TextView v_text = Algorithm.createTextField((Context)classDOTthis, name.get(i), p1.x, p1.y + y_ONE, new RelativeLayout.LayoutParams(x_ONE, 40), Color.WHITE, 1);

            try
            {
                //((ImageView)v).setImage);
            }
            catch(IndexOutOfBoundsException e)
            {

            }

            copy_User.add(toAdd[i]);

            views_Pic.add(v);
            views_Tex.add(v_text);
            copy_Pic.add(v);
            copy_Tex.add(v_text);
            layout.addView(v);
            layout.addView(v_text);
        }

        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                for(int i = 0 ; i < copy_sheruns.size() ; i += 1)
                {
                    if(copy_sheruns.get(i).if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                    {
                        int scale_X = return_Value.size() % x_endBy;
                        int scale_Y = return_Value.size() / x_endBy;
                        int moving_X = (int)copy_Pic.get(i).getX();
                        int moving_Y = (int)copy_Pic.get(i).getY();
                        int moving_X_cur = 0;
                        int moving_Y_cur = 0;

                        //ASSGIEND ?
                        int xC = (return_Value.size() - 1) % x_endBy;
                        int yC = (return_Value.size() - 1) / x_endBy;
                        int HxC = left_X_end + xC * (x_ONE + 10);
                        int HyC = up_Y_end + yC * (y_ONE + 50);

                        //broken
                        if(Time_Control.size() >= 1 && (Time_Control.get( Time_Control.size() - 1 ).getX() != HxC || Time_Control.get( Time_Control.size() - 1 ).getY() != HyC))
                        {
                            return false;
                        }

                        for(int j = 0; j < copy_Pic.size() / X_number; j += 1)
                        {
                            HxC = left_X_start + (X_number - 1) * (x_ONE + 10);
                            HyC = up_Y_start + j * (y_ONE + 50);

                            if(Time_Control.size() >= 1 && (copy_Pic.get((j + 1) * X_number - 1)).getX() != HxC || copy_Pic.get((j + 1) * X_number - 1).getY() != HyC)
                            {
                                return false;
                            }
                        }

                        Time_Control.add(copy_Pic.get(i));
                        view_MOVE(new View[]{copy_Pic.get(i), copy_Tex.get(i)}, left_X_end + scale_X * (x_ONE + 10), up_Y_end + scale_Y * (y_ONE + 50));

                        return_Value.add(copy_User.get(i));

                        for(int j = i + 1; j < copy_sheruns.size() ; j += 1)
                        {
                            moving_X_cur = (int)copy_Pic.get(j).getX();
                            moving_Y_cur = (int)copy_Pic.get(j).getY();

                            view_MOVE(new View[]{copy_Pic.get(j), copy_Tex.get(j)}, moving_X, moving_Y);

                            moving_X = moving_X_cur;
                            moving_Y = moving_Y_cur;
                        }

                        copy_sheruns.remove(copy_sheruns.size() - 1);
                        copy_User.remove(i);
                        copy_Pic.remove(i);
                        copy_Tex.remove(i);
                    }
                }

                return false;
            }
        });
    }

    /**
     *  Create a ImageView in certain part
     * @param classDOTthis
     *  CLASSNAME.this
     * @param startingX
     *  the starting point in x
     * @param startingY
     *  the starting point in y
     * @param params
     *  the layout params
     * @param color
     *  Color
     * @param visibility
     *  visibility
     * @return
     *  the ImageView that does not be added
     *  in current component, need to call
     *  currentComponent.add(ImageView)
     */
    public static ImageView createJPanel(Context classDOTthis, int startingX, int startingY, ViewGroup.LayoutParams params, int color, float visibility)
    {
        ImageView j = new ImageView(classDOTthis);
        j.setLayoutParams(params);
        j.setBackgroundColor(color);
        j.setAlpha(visibility);

        j.setX(startingX);
        j.setY(startingY);

        return j;
    }

    public static TextView createTextField(Context classDOTthis, String text, int startingX, int startingY, ViewGroup.LayoutParams params, int color, float visibility)
    {
        TextView tf = new TextView(classDOTthis);
        tf.setLayoutParams(params);
        tf.setBackgroundColor(color);
        tf.setAlpha(visibility);
        tf.setText(text);

        tf.setX(startingX);
        tf.setY(startingY);

        tf.setId(new Random().nextInt());

        return tf;
    }

    private static class view_MOVE
    {
        private Handler handler;
        private Thread t;

        private final float speed = 150;

        private View[] views;
        private Point[] distant;
        private final float x_Proportion;
        private final float y_Proportion;
        private final boolean x_Way;
        private final boolean y_Way;

        private final float x_TO;
        private final float y_TO;

        private view_MOVE(View[] v, float x_FROM, float y_FROM, float x_TO, float y_TO)
        {
            views = v;
            distant = new Point[views.length];
            distant[0] = new Point(0, 0);
            this.x_TO = x_TO;
            this.y_TO = y_TO;

            for(int i = 1 ; i < views.length ; i += 1)
            {
                distant[i] = new Point((int)views[i].getX() - (int)views[0].getX(), (int)views[i].getY() - (int)views[0].getY());
            }

            x_Way = x_FROM - x_TO > 0;
            y_Way = y_FROM - y_TO > 0;

            float x_Distance = Math.abs(x_FROM - x_TO);
            float y_Distance = Math.abs(y_FROM - y_TO);
            x_Proportion = x_Distance / (x_Distance + y_Distance);
            y_Proportion = y_Distance / (x_Distance + y_Distance);

            handler = new Handler();
            t = new Thread();;
            t.run();
        }

        private class Thread implements Runnable
        {
            @Override
            public void run()
            {
                handler.postDelayed(t, 1);

                if(Math.abs(y_TO - views[0].getY()) <= y_Proportion * speed && Math.abs(x_TO - views[0].getX()) <= x_Proportion * speed)
                {
                    for(int i = 0 ; i < views.length ; i += 1)
                    {
                        views[i].setX(x_TO + distant[i].x);
                        views[i].setY(y_TO + distant[i].y);
                    }

                    handler.removeCallbacks(t);
                    return;
                }

                if (x_Way)
                {
                    for(int i = 0 ; i < views.length ; i += 1)
                    {
                        views[i].setX(views[i].getX() - x_Proportion * speed);
                    }
                }
                else
                {
                    for(int i = 0 ; i < views.length ; i += 1)
                    {
                        views[i].setX(views[i].getX() + x_Proportion * speed);
                    }
                }

                if (y_Way)
                {
                    for(int i = 0 ; i < views.length ; i += 1)
                    {
                        views[i].setY(views[i].getY()  - y_Proportion * speed);
                    }
                }
                else
                {
                    for(int i = 0 ; i < views.length ; i += 1)
                    {
                        views[i].setY(views[i].getY() + y_Proportion * speed);
                    }
                }
            }
        }
    }
}