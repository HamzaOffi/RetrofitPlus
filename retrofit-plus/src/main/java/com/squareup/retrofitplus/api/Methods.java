package com.squareup.retrofitplus.api;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;

public class Methods {

    public static Object getBuildConfigValue(Context context, String fieldName) {
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static boolean ischeck=false;
    private static boolean var=false;
    public static void printLogs(Context context,String s){

        if(!ischeck) {
           ischeck=true;
           var = (Boolean) Methods.getBuildConfigValue(context, "DEBUG");
       }

       if(var)
        Log.d("retrofitPlus",s);

    }

}
