package com.kale.gaurav.coronacount.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SPHelper {
    public static final String MY_PREFS_NAME = "SurveyAnswers";

    public static void setSP(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static String getSP(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String value = prefs.getString(key, null);
        if (value != null) {
            return value;
        }
        return "-1";
    }


    public static void removeAllSP(Context context) {
        SharedPreferences settings = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }

    public static void clearSP(Context context, String question) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(question);
        editor.commit();
    }


    public static String app_name(Context context) {
        String appName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

}

