package com.melolchik.shopapp.ui.presenters.products;

import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.dao.Purchase;
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

    public void purchaseProduct(Product product,float weight){

        Purchase purchase = new Purchase();
        purchase.setProductId(product.getProductId());
        purchase.setWeight(weight);
        purchase.setProduct(product);
        purchase.insertOrReplace();
        if(mCountryProductsView != null){
            mCountryProductsView.updatePurchases();
        }
    }
}
