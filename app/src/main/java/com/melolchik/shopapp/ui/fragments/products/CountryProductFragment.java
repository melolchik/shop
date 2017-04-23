package com.melolchik.shopapp.ui.fragments.products;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.ui.adapters.ProductGridAdapter;
import com.melolchik.shopapp.ui.adapters.decors.AlignmentItemDecoration;
import com.melolchik.shopapp.ui.presenters.products.CountryProductsPresenter;
import com.melolchik.shopapp.ui.presenters.products.CountryProductsViewImpl;
import com.melolchik.shopapp.utils.SettingsUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by melolchik on 28.03.2017.
 */
public class CountryProductFragment extends BaseFragment implements CountryProductsViewImpl {


    public static final int MAX_ITEMS_IN_ROW = 4;
    /**
     * The M recycler view.
     */
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected ProductGridAdapter mGridAdapter;

    private CountryProductsPresenter mProductsPresenter = new CountryProductsPresenter();

    /**
     * Create instance base fragment.
     *
     * @return the base fragment
     */
    public static BaseFragment createInstance() {
        CountryProductFragment fragment = new CountryProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_country_products;
    }


    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {

        mProductsPresenter.attachView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(mRecyclerView.getContext(), MAX_ITEMS_IN_ROW);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new AlignmentItemDecoration(SettingsUtil.getProductItemMargin()));
        mGridAdapter = new ProductGridAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mGridAdapter);
        mProductsPresenter.getProductForCountry(Country.COUNTRY_ID_ALL);
    }


    @Override
    public void showProductList(List<Product> productList) {
        if (mGridAdapter == null) return;
        mGridAdapter.setData(productList);
    }
}
