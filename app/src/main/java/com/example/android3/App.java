package com.example.android3;

import android.app.Application;

import com.example.android3.data.BoredApiClient;
import com.example.android3.model.BoredAction;
import com.example.android3.data.AppPreferences;

public class App extends Application {

    public static AppPreferences appPreferences;
    public static BoredApiClient boredApiClient;
    public static BoredAction boredAction;

    @Override
    public void onCreate() {
        super.onCreate();

        appPreferences = new AppPreferences(this);
        boredApiClient = new BoredApiClient();
//        boredAction = new BoredAction();
//    }BoredAction
    }
}