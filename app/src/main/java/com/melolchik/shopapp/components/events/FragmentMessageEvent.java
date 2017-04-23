package com.melolchik.shopapp.components.events;

import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.shopapp.components.enums.MessageEventCode;

/**
 * Created by melolchik on 03.03.2017.
 */

public class FragmentMessageEvent extends MessageEvent {
    protected BaseFragment mBaseFragment;
    public FragmentMessageEvent() {
        super(MessageEventCode.SHOW_FRAGMENT);
    }

    public FragmentMessageEvent(BaseFragment baseFragment) {
        super(MessageEventCode.SHOW_FRAGMENT);
        setBaseFragment(baseFragment);
    }

    public BaseFragment getBaseFragment() {
        return mBaseFragment;
    }

    public void setBaseFragment(BaseFragment baseFragment) {
        mBaseFragment = baseFragment;
    }
}
