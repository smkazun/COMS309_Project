package com.example.admin.callardar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ChatActivity extends AppCompatActivity {

    Button  b1,b2;
    EditText e1,e2;
    TextView t1;
    Handler h;

    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        b2=(Button)findViewById(R.id.bt2);
        e2=(EditText)findViewById(R.id.et2);
        t1=(TextView)findViewById(R.id.tx1);


        final String[] s= new String[]{ ""};
        h = new Handler()
        {
            public void handleMessage(Message meg)
            {
                t1.setText(s[0]);
                meg = null;
            }
        };

        new Thread(new Runnable()
        {
            private String now;

            @Override
            public void run()
            {
                while (true)
                {
                    if( ! s[0].equals("") && ! s[0].equals(now))
                    {
                        Message message = new Message();
                        message.what = 10;
                        h.sendMessage(message);
                        now = s[0];
                    }
                }
            }
        }).start();

        Draft[] drafts = {new Draft_6455()};
        String w = "ws://proj309-vc-03.misc.iastate.edu:8080/websocket/" + MainActivity.user.getName();

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                @Override
                public void onMessage(String message)
                {
                    Log.d("", "run() returned: " + message);

                    if(s[0].length() >= 170)
                    {
                        s[0] = "";
                    }

                    s[0] += message.toString() + "\n";

                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e)
                {
                    Log.d("Exception:", e.toString());
                }
            };
        }
        catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }

        cc.connect();

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    cc.send(e2.getText().toString());

                    for(int i = 0 ; i < MainActivity.user.getCalender()[CalendarList.iem].getCurrentUser().length ; i += 1)
                    {
                        String name = MainActivity.user.getCalender()[CalendarList.iem].getCurrentUser()[i].getName();

                        if(MainActivity.user.getName().equals(name))
                        {
                            continue;
                        }

                        cc.send("@" + name + " " + e2.getText().toString());
                    }
                }
                catch (Exception e)
                {

                }
            }
        });
    }
}


