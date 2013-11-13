package com.engilitycorp.codeathon.data;

import android.app.Activity;
import android.os.Bundle;

import java.lang.Override;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Location {


    private long locid;

    private double lat;

    private double lon;

    //cafeteria
    private String locName;


    public long getLocid() {
        return locid;
    }

    public void setLocid(long locid) {
        this.locid = locid;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }






}
