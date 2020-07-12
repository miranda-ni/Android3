package com.example.android3.data;

//import com.example.android3.model.BoredAction;
//
import android.util.Log;

import com.example.android3.model.BoredAction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BoredApiClient {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    BoredApi client = retrofit.create(BoredApi.class);



     public  void  getAction(
             String type,
             Integer participants,
             Float price,
             Float minPrice
             ,Float maxPrice
             , Float accessibility
             , Float minAccessibility,
             Float maxAccessibility,
             BoredActionCallback callBack) {

         Call<BoredAction> call = client.getAction(
                 type,
                 accessibility,
                 minAccessibility,
                 maxAccessibility,
                 minPrice,
                 maxPrice);

         Log.d("ololo", call.request().url().toString());
         call.enqueue(new CoreCallback<BoredAction>() {
             @Override
             void onSuccess(BoredAction result) {
                 callBack.onSuccess(result);
             }

             @Override
             void onFailure(Exception exception) {
                 callBack.onFailure(exception);

             }
         });
//
//         call.enqueue(new Callback<BoredAction>() {
//             @Override
//             public void onResponse(Call<BoredAction> call, Response<BoredAction> response) { //нам приходит ответ
//                 if (response.isSuccessful()) {// body это уже наша моделька(обьект нашего класса))
//                     if (response.body() != null){
//                         callBack.onSuccess(response.body());
//                     Log.d("ololo", response.body().toString());
//                 } else {
//                     callBack.onFailure(new Exception("Body ids empty"));
//
//                 }
//             } else
//
//             { callBack.onFailure(new Exception("Response code" + response.code()));
//
//             }
//         }
//
//             @Override
//             public void onFailure(Call<BoredAction> call, Throwable t) {
//                 callBack.onFailure(new Exception(t));
//
//             }
//         });

     }



    public interface BoredActionCallback extends BaseCallback<BoredAction>  { }
    public interface BaseCallback <A> {
        void onSuccess(A result);

        void onFailure(Exception exception);

    }

    private interface BoredApi{
        @GET("api/activity/")
        Call<BoredAction> getAction(
                @Query("type") String type,
                @Query("price") Float price,
                @Query("minaccessibility") Float minAccessibility,
                @Query("maxaccessibility") Float maxAccessibility,
                @Query("minprice") Float minPrice,
                @Query("maxprice") Float maxPrice);



    }
}
