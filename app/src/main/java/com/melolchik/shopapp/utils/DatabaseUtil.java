package com.melolchik.shopapp.utils;

import com.melolchik.common.util.AppLogger;
import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.ShopDatabase;

/**
 * Created by melolchik on 23.04.2017.
 */

public class DatabaseUtil {

    public void initIfNull(){
        ShopDatabase database = new ShopDatabase(true);
        database.cleanAllTables();
        database.release();

        updateCountryList();
        log("country list = " + Country.getList());
    }

    protected void updateCountryList(){
        if(Country.getList().size() > 0) return;
        new Country(1,"Беларусь").insertOrReplace();
        new Country(2,"Россия").insertOrReplace();

    }

    protected static void log(String message) {
        AppLogger.log(DatabaseUtil.class.getCanonicalName() + " " + message);
    }
}
