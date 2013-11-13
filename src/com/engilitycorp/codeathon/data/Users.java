package com.engilitycorp.codeathon.data;

import android.app.Activity;
import android.os.Bundle;

import java.lang.Override;
import java.lang.String;

/**
 * Holds the registered users
 * identified by username and phoneno entered via admin registration page
 * User: pifko
 * Date: 11/13/13
 * Time: 11:15 AM
 *
 */
public class Users {


    private long id;


    //john
    private String userName;

    //123-456-7890
    private String phoneNo;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }









}
