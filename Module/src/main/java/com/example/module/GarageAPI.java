package com.example.module;


import retrofit2.Call;
import retrofit2.http.GET;

public interface GarageAPI {

    @GET("WypPzJCt")
    Call<Garage> loadGarage();

//    @GET("garages/{garageKey}")
//    Call<Garage> loadGarageByKey(@Path(value = "garageKey", encoded = true) String _garageKey);
}
