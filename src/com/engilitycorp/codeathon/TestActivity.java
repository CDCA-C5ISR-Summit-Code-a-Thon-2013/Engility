package com.engilitycorp.codeathon;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.engilitycorp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_map);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocation(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onProviderEnabled(String s) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onProviderDisabled(String s) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    private void updateLocation(Location location){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(17)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera
                .tilt(0)                   // Sets the tilt of the camera to X degrees
                .build();                   // Creates a CameraPosition from the builder


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        GoogleMap map = mapFragment.getMap();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        map.addMarker(new MarkerOptions().position(latLng).title("Test"));

    }


}
