package com.example.admin.callardar;

import android.content.Context;
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
