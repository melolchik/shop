package com.melolchik.shopapp.components.events;


import com.melolchik.shopapp.components.enums.MessageEventCode;

/**
 * Created by melolchik on 07.02.2017.
 */

public class MessageEvent {

    protected MessageEventCode mMessageCode;

    public MessageEvent(MessageEventCode messageCode){
        mMessageCode = messageCode;
    }

    public MessageEventCode getMessageCode() {
        return mMessageCode;
    }
}
