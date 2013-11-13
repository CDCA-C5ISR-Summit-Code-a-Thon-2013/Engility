package com.engilitycorp.codeathon.location;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.engilitycorp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 3:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapHandler extends Handler {

    private Marker myMarker;
    private GoogleMap map;

    public MapHandler(GoogleMap map){
        super();
        this.map = map;
    }

    public void handleMessage( Message msg ){
        Bundle locBundle = msg.getData();
        updateLocation(locBundle.getDouble(LocationService.LAT), locBundle.getDouble(LocationService.LON), locBundle.getDouble(LocationService.TIME));
    }

    private void updateLocation(double lat, double lon, double time){
        LatLng latLng = new LatLng(lat, lon);

        Marker newMarker = map.addMarker(new MarkerOptions().position(latLng).title("Me"));
        if(myMarker != null){
            myMarker.remove();
        }
        myMarker = newMarker;
    }

}
