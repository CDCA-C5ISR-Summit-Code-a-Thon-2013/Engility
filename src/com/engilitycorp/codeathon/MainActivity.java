package com.engilitycorp.codeathon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.engilitycorp.codeathon.FilterAnimation;

import com.engilitycorp.R;
import com.engilitycorp.codeathon.data.Messages;
import com.engilitycorp.codeathon.data.Users;
import com.engilitycorp.codeathon.location.LocationService;
import com.engilitycorp.codeathon.location.MapHandler;
import com.engilitycorp.codeathon.location.TextHandler;
import com.engilitycorp.codeathon.messaging.MessageReceiver;
import com.engilitycorp.codeathon.messaging.MessageSender;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    RelativeLayout menuLayout, mainLayout;
    Button btFilter;
    Button sendButton;
    FilterAnimation filterAnimation;
    Resources resources;
    EditText sender;
    EditText recipient;
    EditText messageText;
    TextView notificationText;

    private MapHandler mapHandler;
    private TextHandler textHandler;
    private LocationService locationService;
    private MessageReceiver messageReceiver;
    private MessageSender messageSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        menuLayout = (RelativeLayout)findViewById(R.id.menu_layout);
        mainLayout = (RelativeLayout)findViewById(R.id.main_layout);

        btFilter = (Button)findViewById(R.id.menu_button);
        btFilter.setOnClickListener(this);

        sender = (EditText)findViewById(R.id.sender);
        recipient = (EditText)findViewById(R.id.recipient);
        messageText = (EditText)findViewById(R.id.send_text);
        sendButton = (Button)findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitMessage();
            }
        });
        notificationText = (TextView)findViewById(R.id.notification_text);

        filterAnimation = new FilterAnimation(this);

        resources = getResources();

        initializeMessageSend();
        initializeTextHandler();
        initializeLocationUpdates();
        initializeMessageReceive();
        initializeAnimations();


    }

    private void submitMessage(){
        String sName = sender.getText().toString();
        String rName = recipient.getText().toString();
        mapHandler.setUserName(sName);
        mapHandler.addUsersPhone(rName);

        String textToSend = messageText.getText().toString();

        Users sender = new Users();
        sender.setUserName(sName);
        Messages messages = new Messages();
        messages.setMsg(textToSend);
        messages.setMsg_timestamp(new Date());
        Users recipient = new Users();
        recipient.setPhoneNo(rName);
        messageSender.sendMessage(sender, messages, recipient);
    }

    private void updateLocation(Location location){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(17)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera
                .tilt(0)                   // Sets the tilt of the camera to X degrees
                .build();                   // Creates a CameraPosition from the builder


//        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//        GoogleMap map = mapFragment.getMap();

//        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        map.addMarker(new MarkerOptions().position(latLng).title("Test"));

    }

    private void initializeAnimations()
    {   //Setting GlobolLayoutListener,when layout is completely set this function will get called and we can have our layout onbject with correct width & height,else if you simply try to get width/height of your layout in onCreate it will return 0

        final ViewTreeObserver filterObserver = mainLayout.getViewTreeObserver();

        filterObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {
                menuLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                DisplayMetrics displayMetrics = resources.getDisplayMetrics();

                int deviceWidth = displayMetrics.widthPixels;

                int menuLayoutWidth = (deviceWidth * 60) / 100; //here im coverting device percentage width into pixels, in my other_slide_in.xml or other_slide_out.xml you can see that i have set the android:toXDelta="80%",so it means the layout will move to 80% of the device screen,to work across all screens i have converted percentage width into pixels and then used it

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(menuLayoutWidth, RelativeLayout.LayoutParams.MATCH_PARENT);

                menuLayout.setLayoutParams(params);//here im setting the layout params for my because its has width 260 dp,so work it across all screen i first make layout adjustments so that it work across all screens resolution

                filterAnimation.initializeFilterAnimations(menuLayout);

            }
        });

        final ViewTreeObserver findObserver = mainLayout.getViewTreeObserver();

        findObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {
                mainLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                filterAnimation.initializeOtherAnimations(mainLayout);
            }
        });

    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        switch(id)
        {

            case R.id.menu_button:

                filterAnimation.toggleSliding();

                break;
        }
    }


    private void initializeLocationUpdates(){
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        GoogleMap map = mapFragment.getMap();

        mapHandler = new MapHandler(map, messageSender);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationService = new LocationService();
        locationService.setLocationManager(locationManager);
        locationService.setRefreshRate(60000L);
        locationService.setMapHandler(mapHandler);
        locationService.startListening();
    }

    private void initializeMessageReceive(){
        messageReceiver = new MessageReceiver(mapHandler, textHandler);
        IntentFilter receivedIntentFilter = new IntentFilter();
        receivedIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(messageReceiver, receivedIntentFilter);

    }

    private void initializeMessageSend(){
        messageSender = MessageSender.getMessageSender();
    }

    private void initializeTextHandler(){
        this.textHandler = new TextHandler(notificationText);
    }
}
