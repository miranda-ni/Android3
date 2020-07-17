package com.example.android3.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android3.model.BoredAction;

import java.util.List;

@Dao
public interface BoredDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BoredAction boredAction);

    @Query("SELECT * FROM bored_action WHERE uuid = :key")
    BoredAction get(String key);

    @Query("SELECT * FROM bored_action")
    List<BoredAction> getAll();

    @Query("SELECT * FROM bored_action")
    LiveData<List<BoredAction>>getAllLive();

    @Delete
    void delete(BoredAction boredAction);


}
