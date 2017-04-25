package com.melolchik.shopapp.ui.activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.melolchik.common.ui.activities.BaseActivity;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.components.enums.LeftMenuItem;
import com.melolchik.shopapp.components.events.DialogMessageEvent;
import com.melolchik.shopapp.components.events.FragmentMessageEvent;
import com.melolchik.shopapp.components.events.MessageEvent;
import com.melolchik.shopapp.ui.adapters.LeftMenuAdapter;
import com.melolchik.shopapp.ui.fragments.products.MainProductsFragment;
import com.melolchik.shopapp.ui.views.LeftSideMenuView;
import com.melolchik.shopapp.utils.DatabaseUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Main activity.
 */
public class MainActivity extends BaseActivity implements LeftSideMenuView.OnExitClickListener,
        LeftMenuAdapter.OnItemClickListener {

    /**
     * The M fragment container.
     */
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    /**
     * The M drawer layout.
     */
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    /**
     * The M left side menu view.
     */
    @BindView(R.id.left_side_menu)
    LeftSideMenuView mLeftSideMenuView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mLeftSideMenuView.setOnExitClickListener(this);
        mLeftSideMenuView.setOnItemClickListener(this);
        new DatabaseUtil().initIfNull();
        showFragment(MainProductsFragment.createInstance(), true, true);
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
     * Open nav drawer.
     */
    protected void openLeftSideMenu() {
        if (mDrawerLayout == null) return;
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    /**
     * Close nav drawer.
     */
    protected void closeLeftSideMenu() {
        if (mDrawerLayout == null) return;
        mDrawerLayout.closeDrawer(GravityCompat.START);

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        log("onMessageEvent: " + event.getMessageCode());
        switch (event.getMessageCode()) {
            case CLICK_ON_MAIN_MENU:
                openLeftSideMenu();
                break;

            case BACK_TO_PREV_SCREEN:
                goToPrevFragmentIfExist();
                break;
            case SHOW_FRAGMENT:
                FragmentMessageEvent fragmentMessageEvent = (FragmentMessageEvent) event;
                showFragment(fragmentMessageEvent.getBaseFragment(), true, true);
                break;
            case SHOW_DIALOG:
                DialogMessageEvent dialogMessageEvent = (DialogMessageEvent) event;
                if(dialogMessageEvent.getBaseDialogFragment() != null){
                    dialogMessageEvent.getBaseDialogFragment().show(this);
                }
                break;
            case SHOW_PROGRESS_BAR:

                break;
            case HIDE_PROGRESS_BAR:

                break;

        }
    }

    @Override
    public void onExitClick() {
        closeLeftSideMenu();
    }

    @Override
    public void onItemClick(LeftMenuItem item) {
        switch (item) {
            case PRODUCTS:
            case HISTORY:
                //...
            default:
                closeLeftSideMenu();
        }
    }
}
