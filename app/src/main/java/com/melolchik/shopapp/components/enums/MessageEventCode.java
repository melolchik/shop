package com.melolchik.shopapp.components.enums;

/**
 * Created by Olga Melekhina on 26.01.2016.
 */
public enum MessageEventCode {


    /**
     * Go to main screen message event code.
     */
    GO_TO_MAIN_SCREEN(0),
    /**
     * Click on main menu message event code.
     */
    CLICK_ON_MAIN_MENU(1),
    /**
     * Back to prev screen message event code.
     */
    BACK_TO_PREV_SCREEN(2),
    /**
     * Show fragment message event code.
     */
    SHOW_FRAGMENT(3),
    /**
     * Show progress bar message event code.
     */
    SHOW_PROGRESS_BAR(4),
    /**
     * Hide progress bar message event code.
     */
    HIDE_PROGRESS_BAR(5),
    /**
     * Show dialog message event code.
     */
    SHOW_DIALOG(6);

    private final int mCode;


    MessageEventCode(int code) {
        this.mCode = code;

    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return mCode;
    }

    /**
     * Gets by code.
     *
     * @param code the code
     * @return the by code
     */
    public static MessageEventCode getByCode(int code) {
        MessageEventCode local = null;
        for (MessageEventCode item : MessageEventCode.values()) {
            if (item.getCode() == code) {
                local = item;
                break;
            }
        }
        if (local == null) {
            local = GO_TO_MAIN_SCREEN;
        }
        return local;
    }

}
