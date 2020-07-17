package com.example.android3.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "bored_action")
public class BoredAction {

    public BoredAction(@NonNull String key) {
        this.key = key;
    }

    @SerializedName("key")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String key;

    @SerializedName("activity")
    @ColumnInfo(name = "activity")
    private String activity;

    @SerializedName("type")
    @ColumnInfo(name = "type")
    private String type;



    @SerializedName("participants")
    @ColumnInfo(name = "participants")
    private Integer participants;

    @SerializedName("price")
    @ColumnInfo(name = "price")
    private Float price;

    @SerializedName("accessibility")
    @ColumnInfo(name = "accessibility")
    private Float accessibility;







    public BoredAction(String activity, String type, String key, Integer participants, Float price, String link, Float accessibility, Float minAccessibility, Float maxAccessibility, Float minPrice, Float maxPrice) {
        this.activity = activity;
        this.type = type;
        this.key = key;
        this.participants = participants;
        this.price = price;
        this.accessibility = accessibility;

    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


    public Float getAccessibility()
    {
        return accessibility;
    }

    public void setAccessibility(Float accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public  String toString(){
        return  "BoredAction{" + "key="
                +key + ",activity="
                + activity + ",type="
                + ",participants="
                + participants+
                ",price=" +price +
            "+accessibility ="+ accessibility +"}";

    }
}

