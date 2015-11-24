package com.example.nixoid.taksitest.rest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoObject {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("accuracy")
    @Expose
    private Integer accuracy;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("thoroughfare")
    @Expose
    private String thoroughfare;
    @SerializedName("premise")
    @Expose
    private String premise;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public GeoObject(String name, String desc, Integer accuracy, String country,
                     String locality, String thoroughfare, String premise,
                     Double lat, Double lon, Boolean active) {
        this.name = name;
        this.desc = desc;
        this.accuracy = accuracy;
        this.country = country;
        this.locality = locality;
        this.thoroughfare = thoroughfare;
        this.premise = premise;
        this.lat = lat;
        this.lon = lon;
        this.active = active;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @param desc
     * The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @return
     * The accuracy
     */
    public Integer getAccuracy() {
        return accuracy;
    }

    /**
     *
     * @param accuracy
     * The accuracy
     */
    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     *
     * @param locality
     * The locality
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     *
     * @return
     * The thoroughfare
     */
    public String getThoroughfare() {
        return thoroughfare;
    }

    /**
     *
     * @param thoroughfare
     * The thoroughfare
     */
    public void setThoroughfare(String thoroughfare) {
        this.thoroughfare = thoroughfare;
    }

    /**
     *
     * @return
     * The premise
     */
    public String getPremise() {
        return premise;
    }

    /**
     *
     * @param premise
     * The premise
     */
    public void setPremise(String premise) {
        this.premise = premise;
    }

    /**
     *
     * @return
     * The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }
}