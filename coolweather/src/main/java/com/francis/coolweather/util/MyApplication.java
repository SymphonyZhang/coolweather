package com.francis.coolweather.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Francis
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
    }

    public static Context getContext(){
        return context;
    }
}
