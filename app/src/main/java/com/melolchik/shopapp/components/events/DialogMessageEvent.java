package com.melolchik.shopapp.components.events;


import com.melolchik.common.ui.dialogs.BaseDialogFragment;
import com.melolchik.shopapp.components.enums.MessageEventCode;

/**
 * Created by melolchik on 03.03.2017.
 */

public class DialogMessageEvent extends MessageEvent {
    protected BaseDialogFragment mBaseFragment;
    public DialogMessageEvent() {
        super(MessageEventCode.SHOW_DIALOG);
    }

    public DialogMessageEvent(BaseDialogFragment baseDialogFragment) {
        super(MessageEventCode.SHOW_DIALOG);
        setBaseDialogFragment(baseDialogFragment);
    }

    public BaseDialogFragment getBaseDialogFragment() {
        return mBaseFragment;
    }

    public void setBaseDialogFragment(BaseDialogFragment baseFragment) {
        mBaseFragment = baseFragment;
    }
}
