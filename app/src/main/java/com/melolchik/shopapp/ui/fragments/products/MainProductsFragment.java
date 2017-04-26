package com.melolchik.shopapp.ui.fragments.products;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.common.ui.fragments.BaseFragmentWithToolbar;
import com.melolchik.common.ui.views.customfont.CustomFontButton;
import com.melolchik.common.ui.views.customfont.CustomFontTextView;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.components.enums.MessageEventCode;
import com.melolchik.shopapp.components.events.MessageEvent;
import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.Purchase;
import com.melolchik.shopapp.ui.adapters.PurchaseListAdapter;
import com.melolchik.shopapp.ui.adapters.decors.DividerItemDecoration;
import com.melolchik.shopapp.ui.presenters.products.MainProductsPresenter;
import com.melolchik.shopapp.ui.presenters.products.MainProductsViewImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by melolchik on 23.04.2017.
 */
public class MainProductsFragment extends BaseFragmentWithToolbar implements MainProductsViewImpl {

    /**
     * The M countries tab layout.
     */
    @BindView(R.id.countries_tab_layout)
    TabLayout mCountriesTabLayout;
    /**
     * The M countries view pager.
     */
    @BindView(R.id.countries_view_pager)
    ViewPager mCountriesViewPager;


    /**
     * The M countries page adapter.
     */
    protected CountriesPageAdapter mCountriesPageAdapter;
    /**
     * The M layout empty.
     */
    @BindView(R.id.layout_empty)
    LinearLayout mLayoutEmpty;
    /**
     * The M txt total value.
     */
    @BindView(R.id.txt_total_value)
    CustomFontTextView mTxtTotalValue;
    /**
     * The M btn pay.
     */
    @BindView(R.id.btn_pay)
    CustomFontButton mBtnPay;
    /**
     * The M recycler view.
     */
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    /**
     * The M list adapter.
     */
    protected PurchaseListAdapter mListAdapter;
    /**
     * The M btn clear.
     */
    @BindView(R.id.btn_clear)
    CustomFontButton mBtnClear;
    /**
     * The M layout data.
     */
    @BindView(R.id.layout_data)
    LinearLayout mLayoutData;

    private MainProductsPresenter mProductsPresenter = new MainProductsPresenter();

    /**
     * Create instance base fragment.
     *
     * @return the base fragment
     */
    public static BaseFragment createInstance() {
        MainProductsFragment fragment = new MainProductsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_STRING_ID, R.string.products_toolbar_title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_main_products;
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar;
    }

    @Override
    protected int getTitleToolbarResId() {
        return R.id.toolbar_title;
    }

    @Override
    protected int getHomeUpIconId() {
        return R.drawable.ic_dehaze;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.menu_products;
    }

    @Override
    protected void onNavigationClick() {
        //super.onNavigationClick();
        EventBus.getDefault().post(new MessageEvent(MessageEventCode.CLICK_ON_MAIN_MENU));
    }

    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        super.onCreateView(rootView, savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        mListAdapter = new PurchaseListAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext()));

        mProductsPresenter.attachView(this);
        mProductsPresenter.getCountryList(rootView.getContext());
        mProductsPresenter.updatePurchaseList();
    }


    /**
     * On view clicked.
     */
    @OnClick(R.id.btn_pay)
    public void onViewClicked() {
    }


    /**
     * On view clicked clear.
     */
    @OnClick(R.id.btn_clear)
    public void onViewClickedClear() {
        mProductsPresenter.clearPurchaseList();
    }

    @Override
    public void showCountryList(List<Country> list) {
        if (mCountriesViewPager == null) return;
        mCountriesPageAdapter = new CountriesPageAdapter(getChildFragmentManager(), list);
        mCountriesViewPager.setAdapter(mCountriesPageAdapter);
        mCountriesTabLayout.setupWithViewPager(mCountriesViewPager);
    }

    @Override
    public void updatePurchaseList(List<Purchase> list) {


        log("list = " + list);
        if (list == null || list.isEmpty()) {
            mLayoutEmpty.setVisibility(View.VISIBLE);
            mLayoutData.setVisibility(View.GONE);
            mBtnPay.setEnabled(false);
            return;
        }

        mLayoutEmpty.setVisibility(View.GONE);
        mLayoutData.setVisibility(View.VISIBLE);
        mListAdapter.setData(list);
        mBtnPay.setEnabled(true);
    }

    @Override
    public void updateTotalCost(float total) {
        if (mTxtTotalValue == null) return;
        mTxtTotalValue.setText(getString(R.string.purchase_cost_template, total));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    /**
     * On message event.
     *
     * @param event the event
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        log("onMessageEvent: " + event.getMessageCode());
        switch (event.getMessageCode()) {
            case UPDATE_PURCHASE_LIST:
                if (mProductsPresenter != null) {
                    mProductsPresenter.updatePurchaseList();
                }
                break;
        }

    }


    private class CountriesPageAdapter extends FragmentStatePagerAdapter {

        private final List<Country> mCountryList;

        /**
         * Instantiates a new Sign up page adapter.
         *
         * @param fm          the fm
         * @param countryList the country list
         */
        public CountriesPageAdapter(FragmentManager fm, List<Country> countryList) {
            super(fm);
            mCountryList = countryList;
        }

        @Override
        public BaseFragment getItem(int position) {
            return CountryProductFragment.createInstance(mCountryList.get(position).getCountryId());

        }

        @Override
        public int getCount() {
            return mCountryList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mCountryList.get(position).getCountryName();
        }
    }

}
