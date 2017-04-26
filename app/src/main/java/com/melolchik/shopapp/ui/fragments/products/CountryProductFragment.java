package com.melolchik.shopapp.ui.fragments.products;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.components.enums.MessageEventCode;
import com.melolchik.shopapp.components.events.DialogMessageEvent;
import com.melolchik.shopapp.components.events.MessageEvent;
import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.ui.adapters.LeftMenuAdapter;
import com.melolchik.shopapp.ui.adapters.ProductGridAdapter;
import com.melolchik.shopapp.ui.adapters.decors.AlignmentItemDecoration;
import com.melolchik.shopapp.ui.dialogs.AddProductDialogFragment;
import com.melolchik.shopapp.ui.presenters.products.CountryProductsPresenter;
import com.melolchik.shopapp.ui.presenters.products.CountryProductsViewImpl;
import com.melolchik.shopapp.utils.SettingsUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 * Created by melolchik on 28.03.2017.
 */
public class CountryProductFragment extends BaseFragment implements CountryProductsViewImpl,
        ProductGridAdapter.OnProductClickListener{


    /**
     * The constant ARG_COUNTRY_ID.
     */
    protected final static String ARG_COUNTRY_ID = "ARG_COUNTRY_ID";

    /**
     * The M recycler view.
     */
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    /**
     * The M country id.
     */
    protected long mCountryId = 0;

    /**
     * The M grid adapter.
     */
    protected ProductGridAdapter mGridAdapter;

    private CountryProductsPresenter mProductsPresenter = new CountryProductsPresenter();

    /**
     * Create instance base fragment.
     *
     * @param countryId the country id
     * @return the base fragment
     */
    public static BaseFragment createInstance(long countryId) {
        CountryProductFragment fragment = new CountryProductFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_COUNTRY_ID,countryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mCountryId = args.getLong(ARG_COUNTRY_ID,Country.COUNTRY_ID_ALL);
        }
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_country_products;
    }


    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {

        mProductsPresenter.attachView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(mRecyclerView.getContext(), SettingsUtil.getProductRowCount());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new AlignmentItemDecoration(SettingsUtil.getProductItemMargin()));
        mGridAdapter = new ProductGridAdapter(mRecyclerView);
        mGridAdapter.setOnProductClickListener(this);
        mRecyclerView.setAdapter(mGridAdapter);
        mProductsPresenter.getProductForCountry(mCountryId);
    }


    @Override
    public void showProductList(List<Product> productList) {
        if (mGridAdapter == null) return;
        mGridAdapter.setData(productList);
    }

    @Override
    public void updatePurchases() {
        EventBus.getDefault().post(new MessageEvent(MessageEventCode.UPDATE_PURCHASE_LIST));
    }

    @Override
    public void onProductClick(Product product) {
        log("onProductClick = " + product);

        AddProductDialogFragment dialogFragment = AddProductDialogFragment.createInstance(product);
        dialogFragment.setOnAddProductListener(new AddProductDialogFragment.OnAddProductListener() {
            @Override
            public void addProduct(Product product, float weight) {
                mProductsPresenter.purchaseProduct(product,weight);
            }
        });

        EventBus.getDefault().post(new DialogMessageEvent(dialogFragment));


    }
}
