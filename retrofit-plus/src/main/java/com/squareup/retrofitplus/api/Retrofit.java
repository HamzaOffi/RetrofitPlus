package com.squareup.retrofitplus.api;

public  class Retrofit {
    static {
        System.loadLibrary("retrofit-plus");
    }

    public static native String getVpName();
}
