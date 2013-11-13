package com.engilitycorp.codeathon;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.engilitycorp.R;
import com.engilitycorp.codeathon.data.Messages;
import com.engilitycorp.codeathon.data.Users;
import com.engilitycorp.codeathon.location.LocationService;
import com.engilitycorp.codeathon.location.MapHandler;
import com.engilitycorp.codeathon.messaging.MessageSender;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestActivity extends Activity{

    private static int count = 0;

    private MessageSender messageSender;

    private Handler handler;
    private Marker myMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        messageSender = MessageSender.getMessageSender();
//        handler = new Handler(){
//            public void handleMessage( Message msg ){
//                Bundle locBundle = msg.getData();
//                updateLocation(locBundle.getDouble(LocationService.LAT), locBundle.getDouble(LocationService.LON), locBundle.getDouble(LocationService.TIME));
//            }
//        };

        setContentView(R.layout.test_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        GoogleMap map = mapFragment.getMap();
        handler = new MapHandler( map );

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationService locationService = new LocationService();
        locationService.setLocationManager(locationManager);
        locationService.setRefreshRate(10000L);
        locationService.setMapHandler(handler);
        locationService.startListening();
    }

    private void updateLocation(double lat, double lon, double time){
        LatLng latLng = new LatLng(lat, lon);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(17)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera
                .tilt(0)                   // Sets the tilt of the camera to X degrees
                .build();                   // Creates a CameraPosition from the builder


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        GoogleMap map = mapFragment.getMap();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        ++count;
        Marker newMarker = map.addMarker(new MarkerOptions().position(latLng).title("Test " + count));
        if(myMarker != null){
            myMarker.remove();
        }
        myMarker = newMarker;

    }


}
