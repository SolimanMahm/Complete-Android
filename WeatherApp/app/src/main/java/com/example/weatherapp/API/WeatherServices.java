package com.example.weatherapp.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherServices {
    @GET("weather")
    Call<WeatherModel> getWeather(
            @Query("q") String q,
            @Query("appid") String key);
}
