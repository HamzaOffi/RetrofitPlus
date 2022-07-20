package com.squareup.retrofitplus.api;

import android.content.Context;

import com.squareup.retrofitplus.api.Methods;
import com.squareup.retrofitplus.interfaces.ApiCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRequest {

    static {
        System.loadLibrary("retrofit-plus");
    }


    public static void JsonPostRequest(Context context,String params,Call<String> callRes, ApiCallback callback){

        Methods.printLogs(context,"URL: "+callRes.request().url());

        Call<String> call=callRes;

        Methods.printLogs(context,"Params: "+params);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String resp=response.body();
                Methods.printLogs(context,"Response: "+resp);
                callback.onResponce(resp);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Methods.printLogs(context,"Failure: "+t);
                callback.onResponce(""+t);
            }
        });
    }


    public native static void initalizeSdk(Context activity);

}
