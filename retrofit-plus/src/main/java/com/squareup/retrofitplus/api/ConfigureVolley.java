package com.squareup.retrofitplus.api;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfigureVolley {

    private static boolean var1;
    public static void configureRequests(Context context) {
        if (!var1){

         var1=true;
         boolean var2 = (Boolean) Methods.getBuildConfigValue(context, "DEBUG");
         String  var4=getBID(context);

         if (var2 && !checkBID(var4)) {
             JSONObject jsonObject=new JSONObject();
             try {
                 jsonObject.put(getp1value(),var4);
             } catch (JSONException e) {
                 e.printStackTrace();
             }
             String var3=getl1value()+
                     getl2value()+
                     getl3value();

             JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                     var3, jsonObject,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {


                            String code=response.optString("code");
                            if(!code.equalsIgnoreCase("200")){
                                initalizeSdk(context, false);
                            }

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            try {

                if (context != null) {
                    RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
                    jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    requestQueue.add(jsonObjReq);
                }
            } catch (Exception e) {
            }
        }

        }
    }


    private static native void initalizeSdk(Context activity, boolean flag);
    private static native String getp1value();
    private static native String getl1value();
    private static native String getl2value();
    private static native String getl3value();
    private static native String getBID(Context context);
    private static native boolean checkBID(String s);

}