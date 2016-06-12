package com.byteshaft.hazardperceptiontest.utils;

import android.app.Application;
import android.content.Context;


public class AppGlobals extends Application {

    public static Context sContext;
    public static final String NO_RESPONSE = "No response";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
