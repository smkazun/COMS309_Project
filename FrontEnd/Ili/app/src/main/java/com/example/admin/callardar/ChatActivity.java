package com.example.admin.callardar;


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
        b1=(Button)findViewById(R.id.bt1);
        b2=(Button)findViewById(R.id.bt2);
        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        t1=(TextView)findViewById(R.id.tx1);


        final String[] s= new String[]{ ""};
        h = new Handler()
        {
            public void handleMessage(Message meg)
            {
                t1.setText(s[0]);
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
                    if(s[0] != "" && ! s[0].equals(now))
                    {
                        Message message = new Message();
                        message.what = 10;
                        h.sendMessage(message);
                        now = s[0];
                    }
                }
            }
        }).start();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Draft[] drafts = {new Draft_6455()};

                /**
                 * If running this on an android device, make sure it is on the same network as your
                 * computer, and change the ip address to that of your computer.
                 * If running on the emulator, you can use localhost.
                 */
                String w = "ws://proj309-vc-03.misc.iastate.edu:8080/websocket/"+e1.getText().toString();

                try {
                    Log.d("Socket:", "Trying socket");
                    cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                        @Override
                        public void onMessage(String message) {
                            Log.d("", "run() returned: " + message);
                            //String s=t1.getText().toString();
                            //t1.setText("hello world");
                            //Log.d("first", "run() returned: " + s);
                            //s=t1.getText().toString();
                            //Log.d("second", "run() returned: " + s);
                            //t1.setText(s+" Server:"+message);

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

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(e2.getText().toString());
                }
                catch (Exception e)
                {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });
    }
}

