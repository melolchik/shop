package com.melolchik.shopapp.ui.presenters.products;

import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.ui.presenters.Presenter;

/**
 * Created by melolchik on 23.04.2017.
 */

public class CountryProductsPresenter implements Presenter<CountryProductsViewImpl> {

    CountryProductsViewImpl mCountryProductsView;
    @Override
    public void attachView(CountryProductsViewImpl viewImpl) {
        mCountryProductsView = viewImpl;
    }

    @Override
    public void detachView() {

    }

    public void getProductForCountry(long countryId){
        if(mCountryProductsView == null) return;
        mCountryProductsView.showProductList(Product.getList(countryId));
    }
}
