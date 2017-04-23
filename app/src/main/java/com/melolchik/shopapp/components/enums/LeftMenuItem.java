package com.melolchik.shopapp.components.enums;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.melolchik.shopapp.R;


/**
 * Created by Olga Melekhina on 26.01.2016.
 */
public enum LeftMenuItem {


    /**
     * Settings left menu item.
     */
    PRODUCTS(0, R.drawable.ic_filter_vintage,R.string.left_menu_item_products),
    /**
     * Transactions left menu item.
     */
    HISTORY(1,R.drawable.ic_filter_vintage,R.string.left_menu_item_history),
    /**
     * Invite friend left menu item.
     */
    REPORTS(2,R.drawable.ic_filter_vintage,R.string.left_menu_item_reports),
    /**
     * Security left menu item.
     */
    SETTINGS(3,R.drawable.ic_filter_vintage,R.string.left_menu_item_settings),
    /**
     * Legal left menu item.
     */
    SUPPORT(4,R.drawable.ic_filter_vintage,R.string.left_menu_item_support);

    private final int mCode;
    private final  @DrawableRes
    int mIconRes;
    private final @StringRes
    int mTitleRes;

    LeftMenuItem(int code, @DrawableRes int iconRes, @StringRes int textRes) {
        this.mCode = code;
        this.mIconRes = iconRes;
        this.mTitleRes = textRes;
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
     * Gets icon res.
     *
     * @return the icon res
     */
    public @DrawableRes
    int getIconRes() {
        return mIconRes;
    }

    /**
     * Gets title res.
     *
     * @return the title res
     */
    public @StringRes
    int getTitleRes() {
        return mTitleRes;
    }

    /**
     * Gets by code.
     *
     * @param code the code
     * @return the by code
     */
    public static LeftMenuItem getByCode(int code) {
        LeftMenuItem local = null;
        for (LeftMenuItem item : LeftMenuItem.values()) {
            if (item.getCode() == code) {
                local = item;
                break;
            }
        }
        if (local == null) {
            local = SETTINGS;
        }
        return local;
    }

}
