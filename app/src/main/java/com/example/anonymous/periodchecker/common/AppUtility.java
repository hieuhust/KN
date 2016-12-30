package com.example.anonymous.periodchecker.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public final class AppUtility {

    public static void setLocale(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public static void putSharedPrefBooleanValue(Context context, String preference, String key, boolean value) {
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getSharedPrefBooleanValue(Context context, String preference, String key) {
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        return pref.getBoolean(key, false);
    }

    public static long getSharedPrefLongValue(Context context, String preference, String key) {
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        return pref.getLong(key,0);
    }

    public static void putSharedPrefLongValue(Context context, String preference, String key, long value) {
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void putSharedPrefStringValue(Context context, String preference, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getSharedPrefStringValue(Context context, String preference, String key) {
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        return pref.getString(key, "");
    }

    public static void putSharedPrefStringSetValue(Context context, String preference, String key, ArrayList<String> values){
        Set<String> set = new HashSet<String>(values);
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, set);
        editor.apply();
    }

    public static ArrayList<String> getSharedPrefStringSetValue(Context context, String preference, String key){
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        return new ArrayList<String>(pref.getStringSet(key,new HashSet<String>()));
    }

    public static void putSharedPrefLongArrayValue(Context context, String preference, String key, ArrayList<Long> values) {
        SharedPreferences prefs = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray jArr = new JSONArray();
        if (values != null && values.size() != 0) {
            for (int i = 0; i < values.size(); i++) {
                jArr.put(values.get(i));
            }
            editor.remove(key);
            editor.putString(key, jArr.toString());
            editor.apply();
        }
    }

    public static ArrayList<Long> getSharedPrefLongArrayValue(Context context, String preference, String key) {
        SharedPreferences prefs = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        String json = prefs.getString(key, null);
        ArrayList<Long> list = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray jArr = new JSONArray(json);
                for (int i = 0; i < jArr.length(); i++) {
                    long item = jArr.optLong(i);
                    list.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void removeSharedPrefKey(Context context, String preference, String key){
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void removeSharedPref(Context context, String preference){
        SharedPreferences pref = context.getSharedPreferences(preference, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }
}
