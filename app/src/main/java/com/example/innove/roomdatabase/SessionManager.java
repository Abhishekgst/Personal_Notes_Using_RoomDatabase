package com.example.innove.roomdatabase;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Abhishek on 2/16/2017.
 */
public class SessionManager {

    public static String putStringInPreferences(Context context, String value, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        return value;
    }

    public static boolean putBooleanInPreferences(Context context, boolean value, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
        return value;
    }

    public static boolean getBooleanFromPreferences(Context context, String key)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        boolean temp = sharedPreferences.getBoolean(key,false);
        return temp;
    }

    public static String getStringFromPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, null);
        return temp;
    }
}
