package com.engilitycorp.codeathon.data.api;

import com.engilitycorp.codeathon.data.Messages;
import com.engilitycorp.codeathon.db.DataStore;

/**
 * Created with IntelliJ IDEA.
 * User: pifko
 * Date: 11/13/13
 * Time: 1:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessagesDataService {

    private DataStore dataStore = DataStore.getDataStore();

    public Messages getMessage(long id){
        return dataStore.getMessage(id);
    }

    public void saveMessages(Messages messages){
        dataStore.saveMessage(messages);
    }

}
