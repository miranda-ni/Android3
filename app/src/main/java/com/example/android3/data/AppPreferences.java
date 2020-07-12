package com.example.android3.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private final static String PREF_IS_FIRST_LAUNCHED = "is_first_launch";
    private SharedPreferences preferences;

    public AppPreferences(Context context){

        preferences = context.getSharedPreferences("bored_app_prefs",Context.MODE_PRIVATE);
    }

    public void setLaunched(){
        preferences.edit().putBoolean(PREF_IS_FIRST_LAUNCHED,false).apply();
    }


    public boolean isFirstLaunch(boolean b){
        return preferences.getBoolean(PREF_IS_FIRST_LAUNCHED,true);
    }
}
