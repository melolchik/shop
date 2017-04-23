package com.melolchik.shopapp;

import android.support.multidex.MultiDexApplication;

import com.melolchik.common.util.AppLogger;
import com.melolchik.common.util.Util;

/**
 * Created by melolchik on 23.04.2017.
 */

public class ShopApplication extends MultiDexApplication {

    public static final String LOG_TAG = "SHOP";

    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(getApplicationContext());
        AppLogger.init(LOG_TAG,LOG_TAG);
    }
}
