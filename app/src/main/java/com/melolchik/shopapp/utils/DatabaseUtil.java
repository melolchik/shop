package com.melolchik.shopapp.utils;

import com.melolchik.common.util.AppLogger;
import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.dao.ShopDatabase;

/**
 * Created by melolchik on 23.04.2017.
 */

public class DatabaseUtil {

    public static final int COUNTRY_RUSSIA = 2;
    public static final int COUNTRY_BELORUSSIA = 1;

    public void initIfNull(){
       /* ShopDatabase database = new ShopDatabase(true);
        database.cleanAllTables();
        database.release();*/

        updateCountryList();
        log("country list = " + Country.getList());
        updateProductList();
        log("product list = " + Product.getList(Country.COUNTRY_ID_ALL));
    }

    protected void updateCountryList(){
        if(Country.getList().size() > 0) return;
        new Country(COUNTRY_BELORUSSIA,"Беларусь").insertOrReplace();
        new Country(COUNTRY_RUSSIA,"Россия").insertOrReplace();

    }

    protected void updateProductList(){
        if(Product.getList(Country.COUNTRY_ID_ALL).size() > 0) return;
        new Product(1,"Картофель","",30.0f,(long)COUNTRY_RUSSIA).insertOrReplace();
        new Product(2,"Лук","",60.0f,(long)COUNTRY_BELORUSSIA).insertOrReplace();
        new Product(3,"Морковь","",40.0f,(long)COUNTRY_RUSSIA).insertOrReplace();
        new Product(4,"Петрушка","",300.0f,(long)COUNTRY_BELORUSSIA).insertOrReplace();
        new Product(5,"Свекла","",50.0f,(long)COUNTRY_RUSSIA).insertOrReplace();
        new Product(6,"Укроп","",300.0f,(long)COUNTRY_BELORUSSIA).insertOrReplace();
        new Product(7,"Чеснок","",220.0f,(long)COUNTRY_RUSSIA).insertOrReplace();

    }

    protected static void log(String message) {
        AppLogger.log(DatabaseUtil.class.getCanonicalName() + " " + message);
    }
}
