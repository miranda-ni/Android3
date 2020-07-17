package com.example.android3.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android3.data.local.BoredDao;
import com.example.android3.model.BoredAction;

@Database(
        entities = {BoredAction.class},version = BoredDatabase.VERSION
        ,exportSchema = false
)

public abstract class BoredDatabase extends RoomDatabase {
    public final static int VERSION = 1;

    public abstract BoredDao boredDao();
}
