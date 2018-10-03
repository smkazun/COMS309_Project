package com.example.admin.callardar.Connection;

import android.widget.ImageView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Method_Connection {

    private Method_Connection()
    {

    }

    public static void makeImageRequest(String URL, final ImageView j)
    {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        imageLoader.get(URL, ImageLoader.getImageListener(j, 0, 0));
    }

    public static void makeJsonArrayReq(String URL, ArrayList<String> arr)
    {
        arr = null;
        arr.clone();

        JsonArrayRequest req = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                                                                                                @Override
                                                                                                public void onResponse(JSONArray response)
                                                                                                {
                                                                                                    //toDO
                                                                                                    //arr =
                                                                                                }
                                                                                            },
                new Response.ErrorListener(){
                                                @Override
                                                public void onErrorResponse(VolleyError error)
                                                {
                                                    throw new RuntimeException();
                                                }
                                            });

        AppController.getInstance().addToRequestQueue(req);
    }

    public static void makeStringReq_GET(String URL,final ArrayList<String> message)
    {
        StringRequest strReq = new StringRequest(Method.GET, URL, new Response.Listener<String>() {
                                                                                                                        @Override
                                                                                                                        public void onResponse(String response)
                                                                                                                        {
                                                                                                                           message.add(response);
                                                                                                                        }
                                                                                                                     }
        , new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error)
                                            {
                                                throw new RuntimeException();
                                            }
                                        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }

    /**
     *
     * @param URL
     * @param s
     *  the string to send
     * @param message
     *  the message return by the
     *  process
     *
     */
    public static void makeStringReq_POST(String URL, final ArrayList<String> message, final ArrayList<String> s)
    {
        StringRequest strReq = new StringRequest(Method.POST, URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                message.add(response);
            }
        }
                , new Response.ErrorListener(){
                                                    @Override
                                                    public void onErrorResponse(VolleyError error)
                                                    {
                                                        throw new RuntimeException();
                                                    }
                                               })
        {
            protected Map<String, String> getParams()
            {
                int i = 0;
                Map<String, String> map = new HashMap<String, String>();

                while(i < s.size())
                {
                    map.put(s.get(i++), s.get(i++));
                }

                return map;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }
}