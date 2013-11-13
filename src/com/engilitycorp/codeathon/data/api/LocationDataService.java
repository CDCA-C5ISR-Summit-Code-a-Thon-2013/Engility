package com.engilitycorp.codeathon.data.api;

import com.engilitycorp.codeathon.data.Location;
import com.engilitycorp.codeathon.data.Messages;
import com.engilitycorp.codeathon.data.Users;
import com.engilitycorp.codeathon.db.DataStore;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocationDataService {

    private DataStore dataStore = DataStore.getDataStore();

    public Location getLocation(long id){
        return dataStore.getLocation(id);
    }

    public void saveLocation(Location location){
        dataStore.saveLocation(location);
    }


}
