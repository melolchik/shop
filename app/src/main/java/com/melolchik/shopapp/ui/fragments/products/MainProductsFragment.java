package com.melolchik.shopapp.ui.fragments.products;

import android.os.Bundle;

import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.common.ui.fragments.BaseFragmentWithToolbar;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.components.enums.MessageEventCode;
import com.melolchik.shopapp.components.events.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by melolchik on 23.04.2017.
 */

public class MainProductsFragment extends BaseFragmentWithToolbar {

    public static BaseFragment createInstance() {
        MainProductsFragment fragment = new MainProductsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_STRING_ID, R.string.left_menu_item_products);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_products;
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

}
