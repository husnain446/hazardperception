package com.byteshaft.hazardperceptiontest.utils;

import android.app.Application;
import android.content.Context;


public class AppGlobals extends Application {

    public static Context sContext;
    public static final String NO_RESPONSE = "10001";
    public static String TAG = "LOGTAG";
    public static final String QUESTIONS_ARRAY = "question_array";
    public static final String ANSWER_HASHMAP = "answer_hashmap";

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
