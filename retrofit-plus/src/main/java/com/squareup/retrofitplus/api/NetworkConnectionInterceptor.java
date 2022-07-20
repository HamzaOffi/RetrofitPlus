package com.squareup.retrofitplus.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkConnectionInterceptor implements Interceptor {

    private Context mContext;
    private String apiKey;

    public NetworkConnectionInterceptor(Context context,String apiKey) {
        mContext = context;
        this.apiKey=apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isConnected()) {
//            Functions.logDMsg("no internet Found");
            throw new NoConnectivityException();
            // Throwing our custom exception 'NoConnectivityException'
        }

        Request.Builder builder = chain.request().newBuilder();
        builder.header("Content-Type", "application/json");
        builder.header("Api-Key", apiKey);
        Methods.printLogs(mContext,"ApiKey: "+apiKey);
        return chain.proceed(builder.build());
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    public class NoConnectivityException extends IOException {

        @Override
        public String getMessage() {
            Methods.printLogs(mContext,"No Internet Connection");
            return "No Internet Connection";
            // You can send any message whatever you want from here.
        }
    }
}