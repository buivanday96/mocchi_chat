package com.example.appchatdemo;

import android.app.Application;

import com.google.gson.Gson;

public class BaseApplication extends Application {

    private static BaseApplication instance;
    public static final float GRAPHIC_WIDTH = 750f;
    public static final float GRAPHIC_HEIGHT = 1335f;
    public static int widthPixels;
    public static float sizePixel;
    private Gson mGson;

    private float DEFAULT_GRAPHIC_WIDTH = 750f;
    private float DEFAULT_GRAPHIC_HEIGHT = 1335f;

    private float DEFAULT_CUSTOM_SIZE_PT = 16f;

    private float DEFAULT_CUSTOM_SIZE_PX = 36f;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        widthPixels = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        sizePixel = ((float) widthPixels * (27f / GRAPHIC_WIDTH)) / 14f; // height of text, font size of text

        mGson = new Gson();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public interface RequestKey {
        public void GetKey(String key);
    }

    public static RequestKey mRequestKey;

    public void setRKey(RequestKey mKey) {
        this.mRequestKey = mKey;
    }

    public Gson GetGson() {
        return mGson;
    }

}
