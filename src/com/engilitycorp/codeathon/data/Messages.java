package com.engilitycorp.codeathon.data;

import android.app.Activity;
import android.os.Bundle;

import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.Date;

/**
 * Holds MessageS that will be brodcasted.
 * User: pifko
 * Date: 11/13/13
 * Time: 11:20 AM
 *
 */
public class Messages {


    //database generated
    private long msgid;

    /**
     * indicate if message is active or old
     */
    private Boolean active;

    /**
     * Timestamp of the msg
     */
    private Date msg_timestamp;



    public long getMsgid() {
        return msgid;
    }

    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getMsg_timestamp() {
        return msg_timestamp;
    }

    public void setMsg_timestamp(Date msg_timestamp) {
        this.msg_timestamp = msg_timestamp;
    }












}
