package com.engilitycorp.codeathon.location;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.engilitycorp.R;
import com.engilitycorp.codeathon.data.Location;
import com.engilitycorp.codeathon.data.Users;
import com.engilitycorp.codeathon.messaging.MessageReceiver;
import com.engilitycorp.codeathon.messaging.MessageSender;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Marker> userToMarkerMap = new HashMap<String, Marker>();
    private MessageSender messageSender;
    private boolean initialZoom = false;

    public MapHandler(GoogleMap map, MessageSender sender){
        super();
        this.map = map;
        this.messageSender = sender;
    }

    public void handleMessage( Message msg ){
        switch (msg.what){
            case LocationService.MY_LOCATION:
                handleMyLocation(msg);
                break;
            case MessageReceiver.OTHER_LOCATION:
                handleOtherLocation(msg);
                break;
            default:
                break;
        }


    }

    private void handleMyLocation(Message msg){
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

        if( !initialZoom  ){
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(15)                   // Sets the zoom
                    .bearing(0)                // Sets the orientation of the camera
                    .tilt(0)                   // Sets the tilt of the camera to X degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            initialZoom = true;
        }


        Users sender = new Users(  );
        sender.setUserName("KEITH");
        Location location = new Location();
        location.setLat(lat);
        location.setLon(lon);
        Users receiver = new Users();
        receiver.setPhoneNo("8595367600");
        messageSender.sendLocation(sender, location, receiver);
    }

    private void handleOtherLocation(Message msg){
        Bundle locBundle = msg.getData();
        double lat = locBundle.getDouble(MessageReceiver.LAT);
        double lon = locBundle.getDouble(MessageReceiver.LON);
        String user = locBundle.getString(MessageReceiver.USER);

        LatLng latLng = new LatLng(lat, lon);

        Marker newMarker = map.addMarker( new MarkerOptions().position(latLng).title(user).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)) );
        Marker currentMarker = userToMarkerMap.get(user);
        if(currentMarker != null){
            currentMarker.remove();
        }
        userToMarkerMap.put(user, newMarker);
    }

}
