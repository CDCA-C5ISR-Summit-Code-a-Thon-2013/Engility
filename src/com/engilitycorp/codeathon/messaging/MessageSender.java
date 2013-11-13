package com.engilitycorp.codeathon.messaging;

import android.telephony.SmsManager;
import com.engilitycorp.codeathon.data.Location;
import com.engilitycorp.codeathon.data.Messages;
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

    public void sendLocation(Users sender, Location location, Users recipient){

        JSONObject locationMessage = new JSONObject();
        try {
            locationMessage.put( MessageKeys.SENDER, sender.getUserName() );
            locationMessage.put( MessageKeys.LATITUDE, location.getLat() );
            locationMessage.put( MessageKeys.LONGITUDE, location.getLon() );
        } catch (JSONException e) {
            //TODO Replace this with something

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        String sms = locationMessage.toString();
        sendSmsMessage(recipient.getPhoneNo(), sms);
    }

    public void sendMessage(Users sender, Messages messages, Users recipient){
        JSONObject json = new JSONObject();
        try {
            json.put( MessageKeys.SENDER, sender.getUserName() );
            json.put( MessageKeys.MESSAGE, messages.getMsg() );
            json.put( MessageKeys.TIMESTAMP, messages.getMsg_timestamp().getTime() );

        } catch (JSONException e) {
            //TODO Replace this with something

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        String sms = json.toString();
        sendSmsMessage(recipient.getPhoneNo(), sms);
    }

    private void sendSmsMessage(String dest, String sms){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(dest, null, sms, null, null);
    }

}
