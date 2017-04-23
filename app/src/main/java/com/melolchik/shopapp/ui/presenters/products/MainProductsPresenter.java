package com.melolchik.shopapp.ui.presenters.products;

import com.melolchik.shopapp.ui.presenters.Presenter;

/**
 * Created by melolchik on 23.04.2017.
 */

public class MainProductsPresenter implements Presenter<MainProductsViewImpl> {

    MainProductsViewImpl mMainProductsView;
    @Override
    public void attachView(MainProductsViewImpl viewImpl) {
        mMainProductsView = viewImpl;
    }

    @Override
    public void detachView() {

    }
}
