package com.example.android3;

import android.app.Application;

import androidx.room.Room;

import com.example.android3.data.BoredApiClient;
import com.example.android3.data.db.BoredDatabase;
import com.example.android3.data.local.BoredStorage;
import com.example.android3.model.BoredAction;
import com.example.android3.data.AppPreferences;

public class App extends Application {

    public static AppPreferences appPreferences;
    public static BoredApiClient boredApiClient;
    public static BoredStorage boredStorage;
    public static BoredDatabase boredDatabase;
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        boredDatabase = Room.databaseBuilder(
                this
                ,BoredDatabase.class
                ,"bored.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();

        boredStorage = new BoredStorage(boredDatabase.boredDao());
        appPreferences = new AppPreferences(this);
        boredApiClient = new BoredApiClient();
        instance = this;
    }

        public static BoredDatabase getBoredDatabase() {
            return boredDatabase;
        }

        public static App getInstance() {
            return instance;
        }

//        boredAction = new BoredAction();
//    }BoredAction
    }
