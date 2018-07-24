package com.example.user.weatherapp.Results;

import com.example.user.weatherapp.model.MyPojo;
import com.example.user.weatherapp.model2.MyPojo2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET("v1/public/yql")
     Call<MyPojo> getquery(@Query("q") String q, @Query("format") String format, @Query("env") String env);
    @GET("v2/top-headlines")
    Call<MyPojo2> getNews(@Query("Country" )String Country,@Query("category" )String category,@Query("apiKey") String apiKey);
}
