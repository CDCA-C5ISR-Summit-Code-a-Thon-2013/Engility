package com.engilitycorp.codeathon.db;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoResultException extends RuntimeException {

    public NoResultException(String msg){
        super(msg);
    }

}
