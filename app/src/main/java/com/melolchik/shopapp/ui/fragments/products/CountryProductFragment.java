package com.melolchik.shopapp.ui.fragments.products;

import android.os.Bundle;
import android.view.View;

import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.common.ui.fragments.BaseFragmentWithToolbar;
import com.melolchik.shopapp.R;

/**
 * Created by melolchik on 28.03.2017.
 */
public class CountryProductFragment extends BaseFragment {


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

    }

}
