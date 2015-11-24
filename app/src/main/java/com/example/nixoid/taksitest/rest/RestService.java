package com.example.nixoid.taksitest.rest;

import com.example.nixoid.taksitest.rest.models.Geo;
import com.example.nixoid.taksitest.rest.models.Suggest;

public class RestService {

    RestClient restClient;

    public RestService() {
        restClient = new RestClient();
    }

    public Suggest getSuggest(String clid, String apikey, double lat, double lon, String query) {
        return restClient.getSuggestApi().getSuggestions(clid, apikey, lat, lon, query);
    }

    public Geo getGeo(String clid, String apikey, double lat, double lon, String query) {
        return restClient.getGeoApi().getGeoObjects(clid, apikey, lat, lon, query);
    }
}