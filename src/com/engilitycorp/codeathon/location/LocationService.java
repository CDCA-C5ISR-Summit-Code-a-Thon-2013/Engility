package com.engilitycorp.codeathon.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

    public static final String LON = "LON";
    public static final String LAT = "LAT";
    public static final String TIME = "TIME";

    private LocationManager locationManager;
    private Handler mapHandler;
    private long refreshRate;
    private long lastUpdate = 0L;


    public void setLocationManager( LocationManager locationManager ){
        this.locationManager = locationManager;
    }

    public void setRefreshRate(long refreshRate){
        this.refreshRate = refreshRate;
    }

    public void setMapHandler(Handler mapHandler){
        this.mapHandler = mapHandler;
    }

    public void startListening(){
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if( (location.getTime() - lastUpdate) >= refreshRate ){
            Message locationMessage = new Message();
            Bundle locationBundle = new Bundle();
            locationBundle.putDouble(LAT, location.getLatitude());
            locationBundle.putDouble(LON, location.getLongitude());
            locationBundle.putDouble(TIME, location.getTime());
            locationMessage.setData(locationBundle);
            mapHandler.sendMessage(locationMessage);
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
