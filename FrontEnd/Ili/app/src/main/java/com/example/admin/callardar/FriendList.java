package com.example.admin.callardar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.callardar.Classes.Algorithm;
import com.example.admin.callardar.Classes.Point;
import com.example.admin.callardar.Classes.User;
import com.example.admin.callardar.Classes.シルヴァホルン;

import java.util.ArrayList;

public class FriendList extends AppCompatActivity {

    private ImageView process;
    private ConstraintLayout mainLayout;
    private ConstraintLayout friends;
    private ConstraintLayout container;

    private ConstraintLayout addingprocess;
    private EditText addingprocessEnter;
    private TextView addingprocessText;
    private ImageView addingprocess_Sure;

    private ConstraintLayout makesure;
    private ConstraintLayout makesure_peopleshow;
    private TextView makesure_Text;
    private TextView makesure_YES;
    private TextView makesure_NO;

    private ImageView trans;

    private シルヴァホルン she1;
    private シルヴァホルン she11;
    private シルヴァホルン she2;
    private シルヴァホルン she21;
    private ArrayList<シルヴァホルン> sheruns2;
    private boolean deleto;

    private ArrayList<シルヴァホルン> sheruns1;
    private User arr[];
    private User sen;
    private ArrayList<ImageView> pics;
    private ArrayList<TextView> texts;
    private float iem;

    private Thread loading;
    private Handler h;

    @SuppressLint("ClickableViewAccessibility")
    private void Initialize()
    {
        process = findViewById(R.id.UI_adding_deleting_searching);
        mainLayout = findViewById(R.id.mainLayout_friend);

        mainLayout.setOnTouchListener(new View.OnTouchListener()
        {
            private float oldX;
            private float[] iniXs;

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                float x = (v.getX() + event.getX());
                float y = (v.getY() + event.getY());

                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    iniXs = new float[pics.size()];
                    oldX = v.getX() + event.getX();

                    for(int i = 0 ; i < pics.size() ; i += 1)
                    {
                        iniXs[i] = pics.get(i).getX();
                    }
                }

                float newX = v.getX() + event.getX();

                for(int i = 0 ; newX != oldX && i < pics.size() ; i += 1)
                {
                    pics.get(i).setX(iniXs[i] - (newX - oldX));
                    texts.get(i).setX(iniXs[i] - (newX - oldX));
                }

                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    iem += (oldX - newX);
                }

                if(event.getAction() == MotionEvent.ACTION_UP && newX == oldX)
                {
                    boolean flag_oneturnout = true;

                    if(flag_oneturnout && addingprocess.getVisibility() == View.VISIBLE && ! she11.if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                    {
                        friends.setVisibility(View.VISIBLE);
                        addingprocess.removeAllViews();
                        addingprocess.setVisibility(View.INVISIBLE);
                        addingprocess.addView(addingprocessEnter);
                        addingprocess.addView(addingprocess_Sure);
                        addingprocess.addView(addingprocessText);

                        trans.setVisibility(View.INVISIBLE);

                        sheruns1.clear();
                        she1.if_Usable = true;
                        she2.if_Usable = true;
                        she11.if_Usable = false;
                        flag_oneturnout = false;
                    }

                    if(flag_oneturnout && she1.if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                    {
                        friends.setVisibility(View.INVISIBLE);
                        addingprocess.setVisibility(View.VISIBLE);

                        mainLayout.removeView(trans);
                        mainLayout.addView(trans);
                        trans.setVisibility(View.VISIBLE);

                        mainLayout.removeView(addingprocess);
                        mainLayout.addView(addingprocess);

                        she1.if_Usable = false;
                        she2.if_Usable = false;
                        she11.if_Usable = true;
                    }

                    if(flag_oneturnout && deleto && ! she21.if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                    {
                        trans.setVisibility(View.INVISIBLE);

                        she1.if_Usable = true;
                        she2.if_Usable = true;
                        makesure_peopleshow.removeAllViews();
                        makesure.setVisibility(View.INVISIBLE);

                        deleto = false;
                        flag_oneturnout = false;
                    }

                    if(flag_oneturnout && she2.if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                    {
                        mainLayout.removeView(trans);
                        mainLayout.addView(trans);
                        trans.setVisibility(View.VISIBLE);

                        mainLayout.removeView(friends);
                        mainLayout.addView(friends);

                        for(int i = 0 ; i < sheruns2.size() && i < MainActivity.user.getFriends().length; i += 1)
                        {
                            sheruns2.get(i).if_Usable = true;
                        }

                        deleto = true;
                        she1.if_Usable = false;
                    }

                    for(int i = 0 ; sheruns1 != null && i < sheruns1.size() ; i += 1)
                    {
                        if(sheruns1.get(i).if_Exist(new Point((int)v.getX() + (int)event.getX(),(int)v.getY() + (int)event.getY())))
                        {
                            for(int j = 0 ; j < sheruns1.size() ; j += 1)
                            {
                                sheruns1.get(j).if_Usable = false;
                            }

                            sen = arr[i];
                            int zone[] = new int[]{0, makesure_peopleshow.getWidth(), 0, makesure_peopleshow.getHeight()};
                            Algorithm.create_ImageAndTexts(FriendList.this, makesure_peopleshow, zone, 1, 1, null, new User[]{sen}, new ArrayList<ImageView>(), new ArrayList<TextView>(), 0);

                            mainLayout.removeView(makesure);
                            mainLayout.addView(makesure);
                            makesure.setVisibility(View.VISIBLE);

                            return false;
                        }
                    }

                    for(int i = 0 ; deleto && i < sheruns2.size() ; i += 1)
                    {
                        if(sheruns2.get(i).if_Exist(new Point((int)v.getX() + (int)event.getX() - (int)iem,(int)v.getY() + (int)event.getY())))
                        {
                            for(int j = 0 ; j < sheruns2.size() ; j += 1)
                            {
                                sheruns2.get(j).if_Usable = false;
                            }

                            sen = MainActivity.user.getFriends()[i];
                            int zone[] = new int[]{0, makesure_peopleshow.getWidth(), 0, makesure_peopleshow.getHeight()};
                            Algorithm.create_ImageAndTexts(FriendList.this, makesure_peopleshow, zone, 1, 1, null, new User[]{sen}, new ArrayList<ImageView>(), new ArrayList<TextView>(), 0);

                            mainLayout.removeView(makesure);
                            mainLayout.addView(makesure);
                            makesure.setVisibility(View.VISIBLE);
                        }
                    }
                }

          //      System.out.println(x + " " + y);

                return true;
            }
        });

        container = findViewById(R.id.Container);
        friends = findViewById(R.id.constraintLayout);

        addingprocess = findViewById(R.id.adding_process);
        addingprocess.setBackgroundColor(Color.LTGRAY);
        addingprocessEnter = findViewById(R.id.adding_process_enter);
        addingprocessText = findViewById(R.id.adding_process_view);
        addingprocess_Sure = findViewById(R.id.adding_process_enter_sure);
        addingprocess_Sure.setBackgroundColor(Color.GREEN);

        addingprocess_Sure.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                String enter = addingprocessEnter.getText().toString();

                //todo
                ArrayList<User> users = null;

                if(MainActivity.user.getName().equals("test"))
                {
                    users = new ArrayList<User>();

                    users.add(new User(255, enter + "1", ""));
                    users.add(new User(256, enter + "2", ""));
                }

                if(users != null && users.size() == 0)
                {
                    return false;
                }

                arr = new User[users.size()];
                arr = users.toArray(arr);
                sheruns1 = new ArrayList<シルヴァホルン>();

                addingprocess.removeAllViews();

                int zone[] = new int[]{0, addingprocess.getWidth(), 0, addingprocess.getHeight()};

                Algorithm.create_ImageAndTexts(FriendList.this, addingprocess, zone, 4,3, null, arr, new ArrayList<ImageView>(), new ArrayList<TextView>(), 0);
                Algorithm.memberAddingProcess(FriendList.this, addingprocess, zone, zone, 4, 3, arr, new ArrayList<User>(), new ArrayList<ImageView>(), new ArrayList<TextView>(), new ArrayList<ImageView>(), new ArrayList<TextView>(), sheruns1, new ArrayList<シルヴァホルン>(), 0, 0, false);

                return false;
            }
        });

        addingprocess.setVisibility(View.INVISIBLE);

        makesure = findViewById(R.id.MakeSure);
        makesure_peopleshow = findViewById(R.id.people_show_Makesure);
        makesure_Text = findViewById(R.id.MakeSure_text);
        makesure.setBackgroundColor(Color.GREEN);
        makesure_YES = findViewById(R.id.makeSure_YES);
        makesure_YES.setBackgroundColor(Color.YELLOW);

        makesure_YES.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                container.removeAllViews();

                //adding
                if(! deleto)
                {
                    for(int i = 0 ; i < MainActivity.user.getFriends().length ; i += 1)
                    {
                        if(MainActivity.user.getFriends()[i].equals(sen))
                        {
                            throw new IllegalStateException("YOU ALREADY HAS THIS FRIENDS");
                        }
                    }

                    MainActivity.user.addFriends(new User[]{sen});


                    addingprocess.removeAllViews();
                    addingprocess.setVisibility(View.INVISIBLE);
                    addingprocess.addView(addingprocessEnter);
                    addingprocess.addView(addingprocess_Sure);
                    addingprocess.addView(addingprocessText);
                    addingprocessText.setText(new char[]{}, 0 , 0);

                    friends.setVisibility(View.VISIBLE);
                }
                else
                {
                    MainActivity.user.deleteFriends(sen);
                    deleto = false;
                }

                makesure_peopleshow.removeAllViews();
                makesure.setVisibility(View.INVISIBLE);
                trans.setVisibility(View.INVISIBLE);

                Message message = new Message();
                message.what = 10;
                h.sendMessage(message);

                she1.if_Usable = true;
                she2.if_Usable = true;

                return false;
            }
        });

        makesure_NO = findViewById(R.id.makesure_NO);

        makesure_NO.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if( ! deleto)
                {
                    makesure_peopleshow.removeAllViews();
                    makesure.setVisibility(View.INVISIBLE);

                    for(int i = 0 ; i < sheruns1.size() && i < arr.length ; i += 1)
                    {
                        sheruns1.get(i).if_Usable = true;
                    }
                }
                else
                {
                    for(int i = 0 ; i < sheruns2.size() && i < MainActivity.user.getFriends().length; i += 1)
                    {
                        sheruns2.get(i).if_Usable = true;
                    }

                    makesure_peopleshow.removeAllViews();
                    makesure.setVisibility(View.INVISIBLE);
                }

                return false;
            }
        });

        makesure_NO.setBackgroundColor(Color.YELLOW);
        makesure.setVisibility(View.INVISIBLE);

        trans = findViewById(R.id.trans_friends_adding_process);
        trans.setAlpha((float)0.8);
        trans.setBackgroundColor(Color.BLACK);
        trans.setVisibility(View.INVISIBLE);

        she1 = new シルヴァホルン("4.9768066 1335.6328 30.025635 1347.6445 45.021973 1353.6797 76.03638 1368.6797 95.05371 1384.6758 122.08008 1393.6992 " +
                "156.12671 1415.6719 172.14478 1443.7383 218.18848 1470.75 240.20508 1492.7227 249.20288 1514.7539 283.2495 1538.7773 299.26758 1563.7969 " +
                "317.26318 1597.7812 333.28125 1622.8008 333.28125 1644.832 348.31055 1647.8203 364.29565 1674.832 379.32495 1702.8398 388.32275 1727.8594 " +
                "7.976074 1718.8359");
        she2 = new シルヴァホルン("685.6128 1718.8359 691.6113 1702.8398 704.6301 1687.8398 713.6279 1665.8086 725.625 1644.832 738.6438 1622.8008 " +
                "769.6582 1588.7578 793.6853 1551.7852 827.73193 1498.7578 861.7456 1470.75 896.781 1427.6836 939.82544 1393.6992 976.87134 1372.6641 " +
                "1022.91504 1335.6328 1066.9482 1313.6602 1075.946 1313.6602 1078.9453 1724.8711");

        pics = new ArrayList<ImageView>();
        texts = new ArrayList<TextView>();

        h = new LeakHandle();

        loading = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000000000);
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
                message.what = 10;
                h.sendMessage(message);
            }
        });

        loading.start();
    }

    private class LeakHandle extends Handler
    {
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            switch (msg.what)
            {
                case 10:
                    int zone[] = new int[]{0, container.getWidth(), 0, container.getHeight()};
                    she11 = new シルヴァホルン(new Point[]{new Point((int)addingprocess.getX(), (int)addingprocess.getY()), new Point((int)addingprocess.getX() + addingprocess.getWidth(), (int)addingprocess.getY()), new Point((int)addingprocess.getX() + addingprocess.getWidth(), (int)addingprocess.getY() + addingprocess.getHeight()), new Point((int)addingprocess.getX(), (int)addingprocess.getY() + addingprocess.getHeight())});
                    she21 = new シルヴァホルン(new Point[]{new Point((int)friends.getX() + (int)container.getX(), (int)friends.getY() + (int)container.getY()), new Point((int)friends.getX() + (int)container.getX() + container.getWidth(), (int)friends.getY() + (int)container.getY()), new Point((int)friends.getX() + (int)container.getX() + container.getWidth(), (int)friends.getY() + (int)container.getY() + container.getHeight()), new Point((int)friends.getX() + (int)container.getX(), (int)friends.getY() + (int)container.getY() + container.getHeight())});
                    sheruns2 = new ArrayList<シルヴァホルン>();

                    pics.clear();
                    texts.clear();

                    int x_Max = (int)(MainActivity.user.getFriends().length / (float)(container.getHeight() / 200)) + 1;
                    Algorithm.create_ImageAndTexts_fillMode(FriendList.this, container, 150, 200, x_Max, null, MainActivity.user.getFriends(), pics, texts, sheruns2);
            }

            msg = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        Initialize();
        loading.interrupt();
    }
}
