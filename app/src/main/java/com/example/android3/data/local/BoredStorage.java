package com.example.android3.data.local;

import androidx.lifecycle.LiveData;

import com.example.android3.model.BoredAction;

import java.util.List;

public class BoredStorage {


    private BoredDao dao;

    public BoredStorage(BoredDao dao) {
        this.dao = dao;
    }

    public void saveBoredAction(BoredAction boredAction) {
        dao.insert(boredAction);
    }

    public BoredAction getBoredAction(String key) {
        return dao.get(key);
    }

    public List<BoredAction> getAllActions() {
        return dao.getAll();
    }
    public LiveData<List<BoredAction>> getAllLive(){
        return dao.getAllLive();
    }

    public void deleteBoredAction(BoredAction boredAction) {
        dao.delete(boredAction);
    }

}
