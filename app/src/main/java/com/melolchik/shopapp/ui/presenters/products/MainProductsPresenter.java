package com.melolchik.shopapp.ui.presenters.products;

import android.content.Context;

import com.melolchik.shopapp.R;
import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.Purchase;
import com.melolchik.shopapp.ui.presenters.Presenter;

import java.util.List;

/**
 * Created by melolchik on 23.04.2017.
 */

public class MainProductsPresenter implements Presenter<MainProductsViewImpl> {

    MainProductsViewImpl mMainProductsView;
    protected List<Country> mCountryList;
    @Override
    public void attachView(MainProductsViewImpl viewImpl) {
        mMainProductsView = viewImpl;
    }

    @Override
    public void detachView() {

    }

    public void getCountryList(Context context){

        if (mCountryList == null) {
            mCountryList = Country.getList();
            mCountryList.add(0, new Country(Country.COUNTRY_ID_ALL, context.getString(R.string.products_tab_all)));
        }
        if(mMainProductsView != null){
            mMainProductsView.showCountryList(mCountryList);
        }

    }

    public void updatePurchaseList(){
        List<Purchase> list = Purchase.getList();
        if(mMainProductsView != null){
            mMainProductsView.updatePurchaseList(list);
        }
    }
}
