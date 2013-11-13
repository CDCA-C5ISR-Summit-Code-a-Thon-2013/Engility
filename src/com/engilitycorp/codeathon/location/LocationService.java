package com.engilitycorp.codeathon.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.engilitycorp.codeathon.data.Users;
import com.engilitycorp.codeathon.messaging.MessageSender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocationService implements LocationListener{

    private LocationManager locationManager;
    private long refreshRate;
    private long lastUpdate = 0L;


    public void setLocationManager( LocationManager locationManager ){
        this.locationManager = locationManager;
    }

    public void setRefreshRate(long refreshRate){
        this.refreshRate = refreshRate;
    }

    public void startListening(){
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if( (location.getTime() - lastUpdate) >= refreshRate ){
            double lat = location.getLatitude();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //TODO something
    }

    @Override
    public void onProviderEnabled(String provider) {
        //TODO something
    }

    @Override
    public void onProviderDisabled(String provider) {
        //TODO something
    }


}
