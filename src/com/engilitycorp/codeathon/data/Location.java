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

    //1,1
    private String coords;

    //cafeteria
    private String locName;


    public long getLocid() {
        return locid;
    }

    public void setLocid(long locid) {
        this.locid = locid;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }






}
