package com.melolchik.shopapp.components.events;

import com.melolchik.shopapp.components.enums.MessageEventCode;

/**
 * Created by melolchik on 10.03.2017.
 */

public class StringMessageEvent extends MessageEvent {
    protected String mStringValue;

    public StringMessageEvent(MessageEventCode messageCode) {
        super(messageCode);
    }

    public String getStringValue() {
        return mStringValue;
    }

    public void setStringValue(String stringValue) {
        mStringValue = stringValue;
    }
}
