package com.melolchik.shopapp.ui.fragments.products;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.common.ui.fragments.BaseFragmentWithToolbar;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.components.enums.MessageEventCode;
import com.melolchik.shopapp.components.events.MessageEvent;
import com.melolchik.shopapp.dao.Country;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by melolchik on 23.04.2017.
 */
public class MainProductsFragment extends BaseFragmentWithToolbar {

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

    protected List<Country> mCountryList;

    protected CountriesPageAdapter mCountriesPageAdapter;

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
        if(mCountryList == null){
            mCountryList = Country.getList();
            mCountryList.add(0,new Country(Country.COUNTRY_ID_ALL,getString(R.string.products_tab_all)));
        }
        mCountriesPageAdapter = new CountriesPageAdapter(getChildFragmentManager(),mCountryList);
        mCountriesViewPager.setAdapter(mCountriesPageAdapter);
        mCountriesTabLayout.setupWithViewPager(mCountriesViewPager);

    }

    private class CountriesPageAdapter extends FragmentStatePagerAdapter {

        private final List<Country> mCountryList;
        /**
         * Instantiates a new Sign up page adapter.
         *
         * @param fm the fm
         */
        public CountriesPageAdapter(FragmentManager fm,List<Country> countryList) {
            super(fm);
            mCountryList = countryList;
        }

        @Override
        public BaseFragment getItem(int position) {
            return CountryProductFragment.createInstance();

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
