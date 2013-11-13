package com.engilitycorp.codeathon.db;

import com.engilitycorp.codeathon.data.Location;
import com.engilitycorp.codeathon.data.Messages;
import com.engilitycorp.codeathon.data.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataStore {

    private static DataStore dataStore;

    private Map<Long, Location> locationMap = new HashMap<Long, Location>();
    private Map<Long, Messages> messagesMap = new HashMap<Long, Messages>();
    private Map<Long, Users> usersMap = new HashMap<Long, Users>();

    private DataStore(){

    }

    public static DataStore getDataStore(){
        if(dataStore == null){
            dataStore = new DataStore();
        }

        return dataStore;
    }

    public Location getLocation(long id){
        if(locationMap.containsKey(id)){
            return locationMap.get(id);
        }

        throw new NoResultException("Location with id = " + id + " does not exist.");
    }

    public void saveLocation(Location location){
        locationMap.put(location.getLocid(), location);
    }

    public Messages getMessage(long id){
        if(messagesMap.containsKey(id)){
            return messagesMap.get(id);
        }

        throw new NoResultException("Message with id = " + id + " does not exist.");
    }

    public void saveMessage(Messages messages){
        messagesMap.put(messages.getMsgid(), messages);
    }

    public Users getUsers(long id){
        if(usersMap.containsKey(id)){
            return usersMap.get(id);
        }

        throw new NoResultException("Users with id = " + id + " does not exist.");
    }

    public void saveUsers(Users users){
        usersMap.put(users.getId(), users);
    }

}
