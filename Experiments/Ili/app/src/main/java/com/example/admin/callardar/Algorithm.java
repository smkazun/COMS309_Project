package com.example.admin.callardar;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Algorithm
{
    private Algorithm()
    {
        //do nothing
    }

    /**
     *
     * a class that can help drag the view, if the
     * view is dragged into correct place, nothing
     * will happened. If not, return to its original
     * with certain speed.
     *
     * NOTE : View does not need to add Listeners,
     * this class will add to it automatically
     *
     */
    public static class BackToOriginal
    {
        Handler handler;
        Thread backToOrigin;

        public BackToOriginal(View v, final float x_TO, final float y_TO)
        {
            v.setOnTouchListener(new View.OnTouchListener()
            {
                private float oldX;
                private float oldY;
                private float iniX;
                private float iniY;

                @Override
                public boolean onTouch(View v, MotionEvent event)
                {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                    {
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

                    if (event.getAction() == MotionEvent.ACTION_UP)
                    {
                        if(Math.abs(x - x_TO) <= 10 && Math.abs(y - y_TO) <= 10)
                        {
                            v.setX(x_TO);
                            v.setY(y_TO);
                        }
                        else
                        {
                            backToOrigin = new Thread(v, x, y, iniX, iniY);
                            backToOrigin.run();
                        }

                        return false;
                    }

                    return true;
                }
            });
        }

        private class Thread implements Runnable
        {
            private final float speed = 50;

            private View view;
            private final float x_Proportion;
            private final float y_Proportion;
            private final boolean x_Way;
            private final boolean y_Way;

            private final float x_TO;

            public Thread(View v, float x_FROM, float y_FROM, float x_TO, float y_TO)
            {
                view = v;
                this.x_TO = x_TO;

                if(x_FROM - x_TO > 0)
                {
                    x_Way = true;
                }
                else
                {
                    x_Way = false;
                }
                if(y_FROM - y_TO > 0)
                {
                    y_Way = true;
                }
                else
                {
                    y_Way = false;
                }

                float x_Distance = Math.abs(x_FROM - x_TO);
                float y_Distance = Math.abs(y_FROM - y_TO);
                x_Proportion = x_Distance / (x_Distance + y_Distance);
                y_Proportion = y_Distance / (x_Distance + y_Distance);
            }

            @Override
            public void run()
            {
                handler.postDelayed(backToOrigin, 10);

                if(x_TO == view.getX())
                {
                    handler.removeCallbacks(backToOrigin);
                }

                if(x_Way)
                {
                    view.setX(view.getX() - x_Proportion * speed);
                }
                else
                {
                    view.setX(view.getX() + x_Proportion * speed);
                }

                if(y_Way == true)
                {
                    view.setY(view.getY() - y_Proportion * speed);
                }
                else
                {
                    view.setY(view.getY() + y_Proportion * speed);
                }
            }
        }
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

        return tf;
    }
}
