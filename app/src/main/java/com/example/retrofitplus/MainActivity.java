package com.example.retrofitplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.retrofitplus.api.ApiInterface;
import com.example.retrofitplus.api.Singleton;
import com.squareup.retrofitplus.api.Retrofit;
import com.squareup.retrofitplus.api.RetrofitRequest;
import com.squareup.retrofitplus.interfaces.ApiCallback;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControl();
    }

    private void initControl() {
        sharedPreferences=MainActivity.this.getSharedPreferences(Retrofit.getVpName(),MODE_PRIVATE);

        checkStatus();

        Log.d(Constants.tag,Retrofit.getVpName());


        findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean("is_login",true).commit();

                checkStatus();
            }
        });

        findViewById(R.id.checkbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrency();
                checkStatus();
            }
        });

    }

    private void showCurrency() {
        ApiInterface apiInterface= Singleton.getApiCall(MainActivity.this);
        RetrofitRequest.JsonPostRequest(MainActivity.this,
                new JSONObject().toString(),
                apiInterface.showCurrency(new JSONObject().toString()), new ApiCallback() {
                    @Override
                    public void onResponce(String resp) {

                    }
                });
    }

    private void logout() {
        JSONObject param = new JSONObject();
        try {
            param.put("user_id", "21");
        } catch (Exception e) {
        }
        ApiInterface apiInterface= Singleton.getApiCall(MainActivity.this);
        RetrofitRequest.JsonPostRequest(MainActivity.this,
                param.toString(),
                apiInterface.logout(param.toString()), new ApiCallback() {
                    @Override
                    public void onResponce(String resp) {

                    }
                });
    }

    public void checkStatus(){
        TextView statustxt=findViewById(R.id.statustxt);
        statustxt.setText("Login status "+sharedPreferences.getBoolean("is_login",false));
    }
}