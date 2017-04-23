package com.melolchik.shopapp.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.common.ui.fragments.BaseFragmentWithToolbar;
import com.melolchik.shopapp.R;

/**
 * Created by melolchik on 28.03.2017.
 */
public class TemplateFragmentWithToolbar extends BaseFragmentWithToolbar {

    /**
     * The constant ID_TOOLBAR.
     */
    protected final static int ID_TOOLBAR = R.id.toolbar;
    /**
     * The constant ID_TOOLBAR_TITLE.
     */
    protected final static int ID_TOOLBAR_TITLE = R.id.toolbar_title;

    /**
     * Create instance base fragment.
     *
     * @return the base fragment
     */
    public static BaseFragment createInstance() {
        TemplateFragmentWithToolbar fragment = new TemplateFragmentWithToolbar();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_STRING_ID, 0/* add title string res here*/);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getHomeUpIconId() {
        return R.drawable.ic_dehaze;
    }

    @Override
    protected int getViewLayoutID() {
        return 0/* add title layout res here*/;
    }


    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        super.onCreateView(rootView, savedInstanceState);
    }

    @Override
    protected int getToolbarResId() {
        return ID_TOOLBAR;
    }

    @Override
    protected int getTitleToolbarResId() {
        return ID_TOOLBAR_TITLE;
    }

    /**
     * On view clicked.
     */
   /* @OnClick(R.id.welcome_btn_next)
    public void onViewClicked() {
        FragmentMessageEvent messageEvent = new FragmentMessageEvent(EmailFragment.createInstance());
        EventBus.getDefault().post(messageEvent);
    }*/
}
