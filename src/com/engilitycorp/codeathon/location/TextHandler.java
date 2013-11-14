package com.engilitycorp.codeathon.location;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.engilitycorp.codeathon.messaging.MessageReceiver;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextHandler  extends Handler {

    private TextView textView;

    public TextHandler(TextView textView){
        this.textView = textView;
    }

    public void handleMessage( Message msg ){
        Bundle bundle = msg.getData();
        String user = bundle.getString(MessageReceiver.USER);
        String text = bundle.getString(MessageReceiver.TEXT);

        String msgText = user + ": " + text;
        textView.setText(msgText);
    }


}
