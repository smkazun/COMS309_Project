package com.example.admin.callardar.Connection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.admin.callardar.Connection.AppController;
import com.example.admin.callardar.MainActivity;;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public class JsonRequestActivity{

    private String TAG = JsonRequestActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";


    public JsonRequestActivity(Context C)
    {
//        pDialog = new ProgressDialog(C);
///        pDialog.setCancelable(false);
 ///       pDialog.setMessage("aaaa");
    }

    public void makeJsonArryReq_TIME(final String URL, final AppController C, final ArrayList<String> s, final Thread Time_Control)
    {
         new Thread(new Runnable()
         {
             @Override
             public void run()
             {
                 makeJsonArryReq(URL, C, s, Time_Control);
             }
         }).start();
    }

    /**
     *
     *  SEND message to the server
     *
     * @param URL
     * @param A
     *  message to send
     * @param C
     *  new AppC(CLASSName).this
     */
    public void makeJsonObjReq(String URL, JSONObject A, AppController C)
    {
//        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
               URL, A,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                pDialog.hide();
            };
        });

        C.addToRequestQueue(jsonObjReq,
                tag_json_obj);
    }

    /**
     *
     * @param URL
     * @param C
     * @param s
     *  message to receive
     */
    public void makeJsonArryReq(String URL, AppController C, final ArrayList<String> s, final Thread Time_Control)
    {
 //       pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        s.add(response.toString());

                        System.out.println("正确！");
                        Time_Control.interrupt();
 //                       pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                System.out.println("错误！");
 //               pDialog.hide();
            }
        });

        // Adding request to request queue
        C.addToRequestQueue(req,
                tag_json_arry);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

}
