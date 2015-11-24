package com.example.nixoid.taksitest.rest.api;

import com.example.nixoid.taksitest.rest.models.Suggest;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface SuggestApi {

    @Headers("Content-Type: application/json")
    @GET("/geo/suggest")
    Suggest getSuggestions(@Query("clid") String clid,
                                    @Query("apikey") String apikey,
                                    @Query("lat") double lat,
                                    @Query("lon") double lon,
                                    @Query("query") String query);
}

