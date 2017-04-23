package com.melolchik.shopapp.ui.presenters.products;

import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.ui.presenters.BaseViewImpl;

import java.util.List;

/**
 * Created by melolchik on 29.03.2017.
 */
public interface CountryProductsViewImpl extends BaseViewImpl{

    void showProductList(List<Product> productList);

}
