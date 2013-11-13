package com.engilitycorp.codeathon.data.api;

import com.engilitycorp.codeathon.data.Users;
import com.engilitycorp.codeathon.db.DataStore;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 1:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class UsersDataService {

    private DataStore dataStore = DataStore.getDataStore();

    public Users getUsers(long id){
        return dataStore.getUsers(id);
    }

    public void saveUsers(Users users){
        dataStore.saveUsers(users);
    }

}
