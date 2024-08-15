package com.example.newsapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<News> getNews(@Query("q") String q,
                       @Query("apiKey") String apiKey);
}
