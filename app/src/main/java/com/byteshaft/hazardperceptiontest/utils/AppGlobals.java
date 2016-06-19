package com.byteshaft.hazardperceptiontest.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class AppGlobals extends Application {

    public static Context sContext;
    public static final String NO_RESPONSE = "No response";
    public static String TAG = "LOGTAG";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

    public static String getLogTag(Class aclass) {
        return TAG + aclass.getSimpleName();
    }
}
