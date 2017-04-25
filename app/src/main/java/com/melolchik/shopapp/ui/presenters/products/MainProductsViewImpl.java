package com.melolchik.shopapp.ui.presenters.products;

import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.Purchase;
import com.melolchik.shopapp.ui.presenters.BaseViewImpl;

import java.util.List;

/**
 * Created by melolchik on 29.03.2017.
 */
public interface MainProductsViewImpl extends BaseViewImpl {

    void showCountryList(List<Country> list);

    void updatePurchaseList(List<Purchase> list);
}
