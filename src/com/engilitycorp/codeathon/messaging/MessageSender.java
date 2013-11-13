package com.engilitycorp.codeathon.messaging;

import android.telephony.SmsManager;
import com.engilitycorp.codeathon.data.Location;
import com.engilitycorp.codeathon.data.Users;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageSender {

    private static MessageSender messageSender;

    private MessageSender(){}

    public static MessageSender getMessageSender(){
        if(messageSender == null){
            messageSender = new MessageSender();
        }

        return messageSender;
    }

    public void sendLocationMessage(Users sender, Location location, Users recipient){

        JSONObject locationMessage = new JSONObject();
        try {
            locationMessage.put( MessageKeys.SENDER, sender.getUserName() );
            locationMessage.put( MessageKeys.LATITUDE, location.getLat() );
            locationMessage.put( MessageKeys.LONGITUDE, location.getLon() );
        } catch (JSONException e) {
            //TODO Replace this with something

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        SmsManager smsManager = SmsManager.getDefault();

        String locationSms = locationMessage.toString();
        smsManager.sendTextMessage(recipient.getPhoneNo(), null, locationSms, null, null);

    }

}
