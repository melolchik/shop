package com.melolchik.shopapp.ui.presenters.products;

import android.content.Context;

import com.melolchik.shopapp.R;
import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.Purchase;
import com.melolchik.shopapp.ui.presenters.Presenter;

import java.util.Collections;
import java.util.List;

/**
 * Created by melolchik on 23.04.2017.
 */
public class MainProductsPresenter implements Presenter<MainProductsViewImpl> {

    /**
     * The M main products view.
     */
    MainProductsViewImpl mMainProductsView;
    /**
     * The M country list.
     */
    protected List<Country> mCountryList;
    @Override
    public void attachView(MainProductsViewImpl viewImpl) {
        mMainProductsView = viewImpl;
    }

    @Override
    public void detachView() {

    }

    /**
     * Get country list.
     *
     * @param context the context
     */
    public void getCountryList(Context context){

        if (mCountryList == null) {
            mCountryList = Country.getList();
            mCountryList.add(0, new Country(Country.COUNTRY_ID_ALL, context.getString(R.string.products_tab_all)));
        }
        if(mMainProductsView != null){
            mMainProductsView.showCountryList(mCountryList);
        }

    }

    /**
     * Update purchase list.
     */
    public void updatePurchaseList(){
        List<Purchase> list = Purchase.getList();

        float total = 0;
        if(!list.isEmpty()){
            for(Purchase purchase : list){
                total += purchase.getProduct().getProductPrice() * purchase.getWeight();
            }
        }
        if(mMainProductsView != null){
            mMainProductsView.updatePurchaseList(list);
            mMainProductsView.updateTotalCost(total);
        }
    }

    /**
     * Clear purchase list.
     */
    public void clearPurchaseList(){

        Purchase.deleteAll();

        if(mMainProductsView != null){
            mMainProductsView.updatePurchaseList(Collections.<Purchase>emptyList());
            mMainProductsView.updateTotalCost(0f);
        }
    }


}
