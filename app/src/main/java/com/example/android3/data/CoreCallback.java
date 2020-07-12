package com.example.android3.data;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class CoreCallback<T> implements Callback<T> {
    abstract void onSuccess(T result);
    abstract void onFailure(Exception exception);


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {// body это уже наша моделька(обьект нашего класса))
            if (response.body() != null){
                onSuccess(response.body());
                Log.d("ololo", response.body().toString());
            } else {
                onFailure(new Exception("Body ids empty"));

            }
        } else

        { onFailure(new Exception("Response code" + response.code()));

        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(new Exception(t));


    }
}
