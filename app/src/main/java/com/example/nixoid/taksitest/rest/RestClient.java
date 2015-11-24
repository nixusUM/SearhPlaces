package com.example.nixoid.taksitest.rest;

import com.example.nixoid.taksitest.rest.api.GeoApi;
import com.example.nixoid.taksitest.rest.api.SuggestApi;

import retrofit.RestAdapter;

public class RestClient {

    private static final String BASE_URL = "http://dev.nstaxi.ru/api";
    private SuggestApi suggestApi;
    private GeoApi geoApi;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();

        suggestApi = restAdapter.create(SuggestApi.class);
        geoApi = restAdapter.create(GeoApi.class);
    }

    public SuggestApi getSuggestApi() {
        return suggestApi;
    }

    public GeoApi getGeoApi() {
        return geoApi;
    }
}

