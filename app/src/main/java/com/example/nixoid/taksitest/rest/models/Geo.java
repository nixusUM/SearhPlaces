package com.example.nixoid.taksitest.rest.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geo {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("geo_objects")
    @Expose
    private List<GeoObject> geoObjects = new ArrayList<GeoObject>();

    /**
     *
     * @return
     * The error
     */
    public Integer getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Integer error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The geoObjects
     */
    public List<GeoObject> getGeoObjects() {
        return geoObjects;
    }

    /**
     *
     * @param geoObjects
     * The geo_objects
     */
    public void setGeoObjects(List<GeoObject> geoObjects) {
        this.geoObjects = geoObjects;
    }
}