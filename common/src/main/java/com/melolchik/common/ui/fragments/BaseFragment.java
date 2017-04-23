package com.melolchik.common.ui.fragments;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melolchik.common.ui.activities.BaseActivity;
import com.melolchik.common.util.AppLogger;
import com.melolchik.common.util.Util;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Olga Melekhina on 03.06.2016.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Gets view layout id.
     *
     * @return the view layout id
     */
    protected abstract int getViewLayoutID();

    /**
     * On create view.
     *
     * @param rootView           the root view
     * @param savedInstanceState the saved instance state
     */
    protected abstract void onCreateView(View rootView, Bundle savedInstanceState);

    private BroadcastReceiver mBroadcastReceiver;

    /**
     * Is nested fragment boolean.
     *
     * @return the boolean
     */
    protected boolean isNestedFragment() {
        return false;
    }

    /**
     * The M unbinder.
     */
    protected Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!isNestedFragment()) {
            setRetainInstance(true);
        }
        super.onCreate(savedInstanceState);
        initBroadcastReceiver();
        IntentFilter filter = getBroadcastIntentFilter();
        if (filter != null) {
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiver, getBroadcastIntentFilter());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //log("onCreateView savedInstanceState = " + savedInstanceState);
        View view = inflater.inflate(getViewLayoutID(), container, false);
        view.setClickable(true);
        mUnbinder = ButterKnife.bind(this,view);
        onCreateView(view, savedInstanceState);
        //initBroadcastReceiver();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Util.hideSoftKeyboard(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }

        Util.hideSoftKeyboard(getActivity());
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver);
    }

    /**
     * Gets broadcast intent filter.
     *
     * @return the broadcast intent filter
     */
    protected IntentFilter getBroadcastIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        return intentFilter;
    }

    private void initBroadcastReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BaseFragment.this.onReceive(context, intent);
            }
        };
    }

    /**
     * On receive.
     *
     * @param context the context
     * @param intent  the intent
     */
    protected void onReceive(Context context, Intent intent) {

    }

    /**
     * Gets tag for stack.
     *
     * @return the tag for stack
     */
    public String getTagForStack() {
        return this.getClass().getCanonicalName();
    }

    /**
     * Is location permission granted boolean.
     *
     * @return the boolean
     */
    public boolean isLocationPermissionGranted() {
        Activity activity = getActivity();
        if (activity == null) return false;
        if (activity instanceof BaseActivity) {
            return ((BaseActivity) activity).isLocationPermissionGranted();
        }
        return false;
    }


    /**
     * Is storage permission granted boolean.
     *
     * @return the boolean
     */
    public boolean isStoragePermissionGranted() {
        Activity activity = getActivity();
        if (activity == null) return false;
        if (activity instanceof BaseActivity) {
            return ((BaseActivity) activity).isStoragePermissionGranted();
        }
        return false;
    }

    /**
     * On back pressed boolean.
     *
     * @return the boolean
     */
    public boolean onBackPressed() {
        return false;
    }

    /**
     * Log.
     *
     * @param message the message
     */
    protected void log(String message) {
        AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }

    /**
     * Log instabug.
     *
     * @param message the message
     */
    protected void log_instabug(String message) {
        //AppLogger.log_instabug(this.getClass().getSimpleName() + " " + message);
    }


}
