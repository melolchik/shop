package com.melolchik.shopapp.ui.presenters;

/**
 * Created by melolchik on 29.03.2017.
 */

public interface Presenter<T extends BaseViewImpl> {

    public void attachView(T viewImpl);
    public void detachView();
}
