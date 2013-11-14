package com.engilitycorp.codeathon.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsMessage;
import android.widget.Toast;
import com.engilitycorp.codeathon.location.MapHandler;
import com.engilitycorp.codeathon.location.TextHandler;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageReceiver extends BroadcastReceiver {

    public static final int OTHER_LOCATION = 200;
    public static final String LON = "LON";
    public static final String LAT = "LAT";
    public static final String TIME = "TIME";
    public static final String USER = "USER";
    public static final String TEXT = "TEXT";

    private MapHandler mapHandler;
    private TextHandler textHandler;

    public MessageReceiver(MapHandler mapHandler, TextHandler textHandler){
        this.mapHandler = mapHandler;
        this.textHandler = textHandler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";

        if (bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                try {
                    JSONObject msg = new JSONObject(msgs[i].getMessageBody().toString());
                    parseSmsJSON(msg);
                } catch (JSONException e) {
                    //TODO something
                }
            }
        }

    }

    private void parseSmsJSON( JSONObject json ) throws JSONException {
        String type = json.getString(MessageKeys.TYPE);

        if( MessageKeys.TYPE_LOCATION.equals(type) ){
            handleLocationMessage(json);
        }
        else if( MessageKeys.TYPE_MESSAGE.equals(type) ){
            handleOtherMessage(json);
        }
    }

    private void handleLocationMessage(JSONObject location) throws JSONException {
        String user = location.getString(MessageKeys.SENDER);
        double lat = location.getDouble(MessageKeys.LATITUDE);
        double lon = location.getDouble(MessageKeys.LONGITUDE);

        Message message = new Message();
        message.what = OTHER_LOCATION;
        Bundle locationBundle = new Bundle();
        locationBundle.putString(USER, user);
        locationBundle.putDouble(LAT, lat);
        locationBundle.putDouble(LON, lon);
        message.setData(locationBundle);
        mapHandler.sendMessage(message);
    }

    private void handleOtherMessage(JSONObject textMessage) throws JSONException{
        String user = textMessage.getString(MessageKeys.SENDER);
        String msg = textMessage.getString(MessageKeys.MESSAGE);

        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(USER, user);
        bundle.putString(TEXT, msg);
        message.setData(bundle);
        textHandler.sendMessage(message);
    }

}
