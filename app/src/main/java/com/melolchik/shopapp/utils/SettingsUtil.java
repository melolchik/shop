package com.melolchik.shopapp.utils;

import android.content.Context;

import com.melolchik.common.util.Util;
import com.melolchik.shopapp.R;

/**
 * Created by melolchik on 23.04.2017.
 */

public class SettingsUtil {

    public static int getProductRowCount(){
        return Util.getContext().getResources().getInteger(R.integer.count_product_in_row);
    }

    public static int getProductItemMargin(){
        return Util.getContext().getResources().getDimensionPixelSize(R.dimen.margin_product_items);
    }

    public static int getItemSize(int parentWidth){
        int count = getProductRowCount();
        int margin = getProductItemMargin();
        return parentWidth/count - margin;
    }
}
