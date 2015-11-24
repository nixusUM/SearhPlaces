package com.example.nixoid.taksitest.rest.api;

import com.example.nixoid.taksitest.rest.models.Geo;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface GeoApi {

    @Headers("Content-Type: application/json")
    @GET("/geo/geocode")
    Geo getGeoObjects(@Query("clid") String clid,
                      @Query("apikey") String apikey,
                      @Query("lat") double lat,
                      @Query("lon") double lon,
                      @Query("query") String query);
}