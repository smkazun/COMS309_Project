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
import java.util.concurrent.Callable;

public class Algorithm
{
    private Algorithm()
    {
        //do nothing
    }

    /**
     * check the specific text in specific header
     * @param header
     * @param toCheck
     * @param s
     *  string to check
     * @return
     */
    public static boolean ifExist(String header, String toCheck, String s)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        boolean flag = false;
        int length_s = s.length();
        int length_h = header.length();
        int length_t = toCheck.length();

        try
        {
            for(; i < length_s ; i += 1)
            {
                if(s.charAt(i) == header.charAt(0) && s.charAt(i + length_h - 1) == header.charAt(length_h - 1))
                {
                    if(length_h <= 2)
                    {
                        flag = true;
                    }

                    for(j = 1 ; j < length_h - 1; j += 1)
                    {
                        if(s.charAt(i + j) != header.charAt(j))
                        {
                            break;
                        }

                        if(j + 1 == length_h - 1)
                        {
                            flag = true;
                        }
                    }

                    if(flag && s.charAt(i + length_h + 3) == toCheck.charAt(0) && s.charAt(i + length_h + 3 + length_t - 1) == toCheck.charAt(length_t - 1))
                    {
                        if(length_t <= 2 && s.charAt(i + length_h + 3 + length_t) == '"')
                        {
                            return true;
                        }

                        for(k = 1 ; k < length_t - 1 ; k +=1)
                        {
                            if(s.charAt(i  + length_h + 3 + k) != toCheck.charAt(k))
                            {
                                flag = false;
                                break;
                            }

                            if(k + 1 == length_t - 1 && s.charAt(i + length_h + 3 + length_t) == '"')
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        catch (StringIndexOutOfBoundsException e)
        {
            return false;
        }


        return false;
    }

    /**
     * check the specific String to specific header, and
     * add the text into ArrayList
     * @param header
     * @param s
     * @return
     */
    public static ArrayList<String> ifExistWithAdd(String header,String s)
    {
        int i = 0;
        int j = 0;
        int k = 0;

        boolean flag = false;
        int length_s = s.length();
        int length_h = header.length();

        ArrayList<String> returnValue = new ArrayList<String>();

        try
        {
            for(; i < length_s ; i += 1)
            {
                if(s.charAt(i) == header.charAt(0) && s.charAt(i + length_h - 1) == header.charAt(length_h - 1))
                {
                    if(length_h <= 2)
                    {
                        flag = true;
                    }

                    for(j = 1 ; j < length_h - 1; j += 1)
                    {
                        if(s.charAt(i + j) != header.charAt(j))
                        {
                            break;
                        }

                        if(j + 1 == length_h - 1)
                        {
                            flag = true;
                        }
                    }

                    if(flag)
                    {
                        for(k = i + length_h + 3 ; s.charAt(k) != '"' ; k +=1)
                        {
                            if(s.charAt(k + 1) == '"')
                            {
                                returnValue.add(s.substring(i + length_h + 3, k + 1));
                                i = k + 2;
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        catch (StringIndexOutOfBoundsException e)
        {
            return returnValue;
        }

        return returnValue;
    }

    public static ArrayList<String> ifExistWithAdd_SpecialMode_INT(String header,String s)
    {
        int i = 0;
        int j = 0;
        int k = 0;

        boolean flag = false;
        int length_s = s.length();
        int length_h = header.length();

        ArrayList<String> returnValue = new ArrayList<String>();

        try
        {
            for(; i < length_s ; i += 1)
            {
                if(s.charAt(i) == header.charAt(0) && s.charAt(i + length_h - 1) == header.charAt(length_h - 1))
                {
                    if(length_h <= 2)
                    {
                        flag = true;
                    }

                    for(j = 1 ; j < length_h - 1; j += 1)
                    {
                        if(s.charAt(i + j) != header.charAt(j))
                        {
                            break;
                        }

                        if(j + 1 == length_h - 1)
                        {
                            flag = true;
                        }
                    }

                    if(flag)
                    {
                        for(k = i + length_h + 2 ; s.charAt(k) != ',' ; k +=1)
                        {
                            if(s.charAt(k + 1) == ',')
                            {
                                returnValue.add(s.substring(i + length_h + 2, k + 1));
                                i = k + 1;
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        catch (StringIndexOutOfBoundsException e)
        {
            return returnValue;
        }

        return returnValue;
    }

    /**
     * check the specific String to specific header, and
     * add the text into ArrayList
     * @param header
     * @param toCheck
     * @param s
     * @return
     */
    public static ArrayList<String> ifExistAndAdd(String header, String toCheck, String s)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        boolean flag = false;
        int length_s = s.length();
        int length_h = header.length();
        int length_t = toCheck.length();

        ArrayList<String> returnValue = new ArrayList<String>();

        try
        {
        for(; i < length_s ; i += 1)
        {
            if(s.charAt(i) == header.charAt(0) && s.charAt(i + length_h - 1) == header.charAt(length_h - 1))
            {
                if(length_h <= 2)
                {
                    flag = true;
                }

                for(j = 1 ; j < length_h - 1; j += 1)
                {
                    if(s.charAt(i + j) != header.charAt(j))
                    {
                        break;
                    }

                    if(j + 1 == length_h - 1)
                    {
                        flag = true;
                    }
                }

                if(flag && s.charAt(i + length_h + 3) == toCheck.charAt(0) && s.charAt(i + length_h + 3 + length_t - 1) == toCheck.charAt(length_t - 1))
                {
                    if(length_t <= 2 && s.charAt(i + length_h + 3 + length_t) == '"')
                    {
                        returnValue.add(s.substring(i + length_h + 3, i + length_h + 3 + length_t));
                        i = i + length_h + 4 + length_t;
                        flag = false;
                    }

                    for(k = 1 ; k < length_t - 1 ; k +=1)
                    {
                        if(s.charAt(i  + length_h + 3 + k) != toCheck.charAt(k))
                        {
                            flag = false;
                            break;
                        }

                        if(k + 1 == length_t - 1 && s.charAt(i + length_h + 3 + length_t) == '"')
                        {
                            returnValue.add(s.substring(i + length_h + 3, i + length_h + 3 + length_t));
                            i = i + length_h + 4 + length_t;
                            flag = false;
                        }
                    }
                }
            }
        }
        }
        catch (StringIndexOutOfBoundsException e)
        {
            return returnValue;
        }

        return returnValue;
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
     * call it when need it (Ver.2)
     * @param classDOTthis
     * @param Layout
     * @param Zone
     * @param X_number
     * @param Y_number
     * @param addForView
     * @param toAdd
     * @param views_Pic
     * @param views_Tex
     * @param index
     */
    public static void create_ImageAndTexts(Object classDOTthis, Object Layout, int[] Zone, final int X_number, final int Y_number, final String[] addForView, final User[] toAdd, final ArrayList<ImageView> views_Pic, final ArrayList<TextView> views_Tex, int index)
    {
        ConstraintLayout layout = (ConstraintLayout) Layout;

        final int left_X_start = Zone[0];
        final int right_X_start = Zone[1];
        final int up_Y_start = Zone[2];
        final int down_Y_start = Zone[3];

        final int x_ONE = (right_X_start - left_X_start) / X_number - 10;
        final int y_ONE = (down_Y_start - up_Y_start) / Y_number - 50;

        for(int i = index ; i < toAdd.length && i < index + X_number * Y_number ; i += 1)
        {
            int scale_X = (i - index) % X_number;
            int scale_Y = (i - index) / X_number;
            Point p1 = new Point(left_X_start + scale_X * (x_ONE + 10),up_Y_start + scale_Y * (y_ONE + 50));
            Random r = new Random();

            ImageView v = Algorithm.createJPanel((Context) classDOTthis, p1.x, p1.y, new RelativeLayout.LayoutParams(x_ONE, y_ONE), Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)), 1);
            TextView v_text = Algorithm.createTextField((Context) classDOTthis, toAdd[i].getName(), p1.x, p1.y + y_ONE, new RelativeLayout.LayoutParams(x_ONE, 40), Color.WHITE, 1);

            try
            {
                //((ImageView)v).setImage);
            }
            catch(IndexOutOfBoundsException e)
            {

            }

            views_Pic.add(v);
            views_Tex.add(v_text);
            layout.addView(v);
            layout.addView(v_text);
        }
    }

    /**
     * Call it when need it
     * @param classDOTthis
     * @param Layout
     * @param Zone1
     * @param Zone2
     * @param X_number
     * @param Y_number
     * @param toAdd
     * @param return_Value_Picture
     * @param views_Pic
     * @param views_Tex
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void memberAddingProcess(final Object classDOTthis, Object Layout, int[] Zone1, int[] Zone2, final int X_number, final int Y_number,
                                           final User[] toAdd, final ArrayList<User> return_Value_toAdd, final ArrayList<ImageView> return_Value_Picture,
                                           final ArrayList<TextView> return_Value_Text, final ArrayList<ImageView> views_Pic, final ArrayList<TextView> views_Tex,
                                           final ArrayList<シルヴァホルン> sheruns1,final ArrayList<シルヴァホルン> sheruns2, final int index1, final int index2)
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
        final int x_endBy = (right_X_end - left_X_end) / (x_ONE + 10);
        final int y_endBy = (down_Y_end - up_Y_end) / (y_ONE + 50);

        final ArrayList<User> copy_User= new ArrayList<User>();
        final ArrayList<ImageView> copy_Pic = new ArrayList<ImageView>();
        final ArrayList<TextView> copy_Tex = new ArrayList<>();

        final ConstraintLayout layout = (ConstraintLayout) Layout;

        for(int i = 0; i < X_number * Y_number ; i += 1)
        {
            //create zone,logic
            int scale_X = i % X_number;
            int scale_Y = i / X_number;
            Point p1 = new Point(left_X_start + scale_X * (x_ONE + 10), up_Y_start + scale_Y * (y_ONE + 50));
            Point p2 = new Point(left_X_start + (x_ONE + 10) + scale_X * (x_ONE + 10), up_Y_start + scale_Y * (y_ONE + 50));
            Point p3 = new Point(left_X_start + (x_ONE + 10) + scale_X * (x_ONE + 10), up_Y_start + (y_ONE + 50) + scale_Y * (y_ONE + 50));
            Point p4 = new Point(left_X_start + scale_X * (x_ONE + 10), up_Y_start + (y_ONE + 50) + scale_Y * (y_ONE + 50));

            sheruns1.add(new シルヴァホルン(new Point[]{p1, p2, p3, p4}));

            if(i > toAdd.length)
            {
                sheruns1.get(i).if_Usable = false;
            }
        }

        for(int i = index1; i < Math.min(toAdd.length, index1 + X_number * Y_number) ; i += 1)
        {
            copy_Pic.add(views_Pic.get(i));
            copy_Tex.add(views_Tex.get(i));
        }

        for(int i = 0; i < x_endBy * y_endBy ; i += 1)
        {
            int scale_X = i % x_endBy;
            int scale_Y = i / x_endBy;
            Point p1 = new Point(left_X_end + scale_X * (x_ONE + 10),up_Y_end + scale_Y * (y_ONE + 50));
            Point p2 = new Point(left_X_end + (x_ONE + 10) + scale_X * (x_ONE + 10), up_Y_end + scale_Y * (y_ONE + 50));
            Point p3 = new Point(left_X_end + (x_ONE + 10) + scale_X * (x_ONE + 10), up_Y_end + (y_ONE + 50) + scale_Y * (y_ONE + 50));
            Point p4 = new Point(left_X_end + scale_X * (x_ONE + 10),up_Y_end + (y_ONE + 50) + scale_Y * (y_ONE + 50));

            sheruns2.add(new シルヴァホルン(new Point[]{p1, p2, p3, p4}));

            sheruns2.get(i).if_Usable = false;
        }


        for(int i = 0 ; i < toAdd.length ; i += 1)
        {
            copy_User.add(toAdd[i]);
        }

        layout.setOnTouchListener(new View.OnTouchListener()
        {
            private long sysTime = System.currentTimeMillis();

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(System.currentTimeMillis() - sysTime <= 200)return false;

                sysTime = System.currentTimeMillis();

                for(int i = index1 ; i < Math.min(copy_User.size(), index1 + X_number * Y_number) ; i += 1)
                {
                    if(sheruns1.get(i - index1).if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                    {
                        //ASSGIEND ?
                        int xC = (return_Value_Picture.size() - 1) % x_endBy;
                        int yC = (return_Value_Picture.size() - 1) / x_endBy;
                        int HxC = left_X_end + xC * (x_ONE + 10);
                        int HyC = up_Y_end + yC * (y_ONE + 50);

                        //broken
                        if(return_Value_Picture.size() >= 1 && (return_Value_Picture.get(0).getX() != left_X_end || return_Value_Picture.get(0).getY() != up_Y_end))
                        {
                            return false;
                        }


                        for(int j = 0; j < Math.min(copy_User.size(), index1 + X_number * Y_number) / X_number; j += 1)
                        {
                            HxC = left_X_start + (X_number - 1) * (x_ONE + 10);
                            HyC = up_Y_start + j * (y_ONE + 50);

                            if(return_Value_Picture.size() >= 1 && (copy_Pic.get((j + index1 + 1) * X_number - 1)).getX() != HxC || copy_Pic.get((j + index1 + 1) * X_number - 1).getY() != HyC)
                            {
                                return false;
                            }
                        }

                        int scale_X = return_Value_Picture.size() % x_endBy;
                        int scale_Y = return_Value_Picture.size() / x_endBy;
                        int moving_X = (int)copy_Pic.get(i).getX();
                        int moving_Y = (int)copy_Pic.get(i).getY();
                        int moving_X_cur = 0;
                        int moving_Y_cur = 0;

                        view_MOVE(new View[]{copy_Pic.get(i), copy_Tex.get(i)}, left_X_end, up_Y_end);

                        //END-------------
                        int j = 0;

                        for(j = index2 ; j < Math.min(return_Value_Picture.size(), index2 + x_endBy * y_endBy) - 1; j += 1)
                        {
                            scale_X = (j + 1) % x_endBy;
                            scale_Y = (j + 1) / x_endBy;

                            view_MOVE(new View[]{return_Value_Picture.get(j), return_Value_Text.get(j)}, left_X_end + scale_X * (x_ONE + 10), up_Y_end + scale_Y * (y_ONE + 50));
                        }

                        return_Value_Picture.add(0, copy_Pic.get(i));
                        return_Value_Text.add(0, copy_Tex.get(i));
                        return_Value_toAdd.add(0, copy_User.get(i));

                        if(index2 + x_endBy * y_endBy < return_Value_Picture.size())
                        {
                            view_MOVE(new View[]{return_Value_Picture.get(x_endBy * y_endBy), return_Value_Text.get(x_endBy * y_endBy)}, (float)return_Value_Picture.get(x_endBy * y_endBy).getX() + 500, return_Value_Picture.get(x_endBy * y_endBy).getY());
                        }
                        else if(return_Value_Picture.size() >= 2)
                        {
                            scale_X = (j + 1) % x_endBy;
                            scale_Y = (j + 1) / x_endBy;

                            view_MOVE(new View[]{return_Value_Picture.get(j + 1), return_Value_Text.get(j + 1)}, left_X_end + scale_X * (x_ONE + 10), up_Y_end + scale_Y * (y_ONE + 50));
                        }

                        //FROM--------------
                        for(j = i + 1; j < Math.min(copy_User.size(), index1 + X_number * Y_number); j += 1)
                        {
                            moving_X_cur = (int)copy_Pic.get(j).getX();
                            moving_Y_cur = (int)copy_Pic.get(j).getY();

                            view_MOVE(new View[]{copy_Pic.get(j), copy_Tex.get(j)}, moving_X, moving_Y);

                            moving_X = moving_X_cur;
                            moving_Y = moving_Y_cur;
                        }

                        if(j < copy_User.size())
                        {
                            if(copy_Pic.size() == j)
                            {
                                Random r = new Random();
                                ImageView newImage = Algorithm.createJPanel((Context) classDOTthis, right_X_start, down_Y_start, new RelativeLayout.LayoutParams(x_ONE, y_ONE), Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)), 1);
                                TextView newText = Algorithm.createTextField((Context) classDOTthis, copy_User.get(j).getName(), right_X_start, down_Y_start + y_ONE, new RelativeLayout.LayoutParams(x_ONE, 40), Color.WHITE, 1);

                                view_MOVE(new View[]{newImage, newText}, moving_X, moving_Y);

                                views_Pic.add(newImage);
                                views_Tex.add(newText);
                                copy_Pic.add(newImage);
                                copy_Tex.add(newText);
                                layout.addView(newImage);
                                layout.addView(newText);
                            }
                            else
                            {
                                view_MOVE(new View[]{copy_Pic.get(j),copy_Tex.get(j)}, moving_X, moving_Y);
                            }
                        }

                        copy_User.remove(i);
                        copy_Pic.remove(i);
                        copy_Tex.remove(i);

                        if(index1 + X_number * Y_number > copy_User.size())
                        {
                            sheruns1.get(copy_User.size() - index1).if_Usable = false;
                        }
                        if ( ! sheruns2.get(x_endBy * y_endBy - 1).if_Usable && ! sheruns2.get(return_Value_Picture.size() - 1 - index2).if_Usable)
                        {
                            sheruns2.get(return_Value_Picture.size() - 1 - index2).if_Usable = true;
                        }
                    }
                }

                //---------------------------------------------DOWN---------------------------------------------

                for(int i = index2 ; i < Math.min(return_Value_Picture.size(), index2 + x_endBy * y_endBy) ; i += 1)
                {
                    if(sheruns2.get(i - index2).if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                    {
                        //ASSGIEND ?
                        int xC = (copy_Pic.size() - 1) % X_number;
                        int yC = (copy_Pic.size() - 1) / X_number;
                        int HxC = left_X_end + xC * (x_ONE + 10);
                        int HyC = up_Y_end + yC * (y_ONE + 50);

                        //broken
                        if(copy_Pic.size() >= 1 && (copy_Pic.get(0).getX() != left_X_start || copy_Pic.get(0).getY() != up_Y_start))
                        {
                            return false;
                        }

                        for(int j = 0; j < Math.min(return_Value_Picture.size(), index1 + x_endBy * y_endBy) / x_endBy; j += 1)
                        {
                            HxC = left_X_end + (x_endBy - 1) * (x_ONE + 10);
                            HyC = up_Y_end + j * (y_ONE + 50);

                            if(copy_Pic.size() >= 1 && (return_Value_Picture.get((j + index2 + 1) * x_endBy - 1)).getX() != HxC || return_Value_Picture.get((j + index2 + 1) * x_endBy - 1).getY() != HyC)
                            {
                                return false;
                            }
                        }

                        int scale_X = copy_Pic.size() % X_number;
                        int scale_Y = copy_Pic.size() / X_number;
                        int moving_X = (int)return_Value_Picture.get(i).getX();
                        int moving_Y = (int)return_Value_Picture.get(i).getY();
                        int moving_X_cur = 0;
                        int moving_Y_cur = 0;

                        view_MOVE(new View[]{return_Value_Picture.get(i), return_Value_Text.get(i)}, left_X_start, up_Y_start);

                        //END-------------
                        int j = 0;

                        for(j = index1 ; j < Math.min(copy_Pic.size(), index1 + X_number * Y_number) - 1 ; j += 1)
                        {
                            scale_X = (j + 1) % X_number;
                            scale_Y = (j + 1) / X_number;

                            view_MOVE(new View[]{copy_Pic.get(j), copy_Tex.get(j)}, left_X_start + scale_X * (x_ONE + 10), up_Y_start + scale_Y * (y_ONE + 50));
                        }

                        copy_Pic.add(0, return_Value_Picture.get(i));
                        copy_Tex.add(0, return_Value_Text.get(i));
                        copy_User.add(0,return_Value_toAdd.get(i));

                        if(index1 + X_number * Y_number < copy_Pic.size())
                        {
                            view_MOVE(new View[]{copy_Pic.get(X_number * Y_number), copy_Tex.get(X_number * Y_number)}, copy_Pic.get(X_number * Y_number).getX() + 500, copy_Pic.get(X_number * Y_number).getY());
                        }
                        else if(copy_Pic.size() >= 2)
                        {
                            scale_X = (j + 1) % x_endBy;
                            scale_Y = (j + 1) / x_endBy;

                            view_MOVE(new View[]{copy_Pic.get(j + 1), copy_Tex.get(j + 1)}, left_X_start + scale_X * (x_ONE + 10), up_Y_start + scale_Y * (y_ONE + 50));
                        }

                        //FROM--------------
                        for(j = i + 1; j < Math.min(return_Value_Picture.size(), index2 + x_endBy * y_endBy); j += 1)
                        {
                            moving_X_cur = (int)return_Value_Picture.get(j).getX();
                            moving_Y_cur = (int)return_Value_Picture.get(j).getY();

                            view_MOVE(new View[]{return_Value_Picture.get(j), return_Value_Text.get(j)}, moving_X, moving_Y);

                            moving_X = moving_X_cur;
                            moving_Y = moving_Y_cur;
                        }

                        if(j < return_Value_Picture.size())
                        {
                            Random r = new Random();

                            view_MOVE(new View[]{return_Value_Picture.get(j), return_Value_Text.get(j)}, moving_X, moving_Y);
                        }

                        return_Value_Picture.remove(i);
                        return_Value_Text.remove(i);
                        return_Value_toAdd.remove(i);

                        if(index2 + x_endBy * y_endBy > return_Value_Picture.size())
                        {
                            sheruns2.get(return_Value_Picture.size() - index2).if_Usable = false;
                        }

                        if(copy_Pic.size() == 1)
                        {
                            sheruns1.get(0).if_Usable = true;
                        }
                        else if ( ! sheruns1.get(X_number * Y_number - 1).if_Usable && ! sheruns1.get(copy_Pic.size() - 1 - index1).if_Usable)
                        {
                            sheruns1.get(copy_Pic.size() - 1 - index1).if_Usable = true;
                        }
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
            t = new Thread();
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

    public static class Stop implements Callable<Integer>
    {
        private long WAIT;

        public Stop(int WAIT)
        {
            this.WAIT = WAIT;
        }

        @Override
        public Integer call() throws Exception
        {
            long sysTime = System.currentTimeMillis();
            long currentTime = System.currentTimeMillis();

            while(currentTime - sysTime <= WAIT)
            {
                currentTime = System.currentTimeMillis();
            }

            return 10;
        }
    }
}