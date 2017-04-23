package com.melolchik.common.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;


import com.melolchik.common.network.NetworkStateReceiver;
import com.melolchik.common.ui.fragments.BaseFragment;
import com.melolchik.common.util.AppLogger;
import com.melolchik.common.util.PermissionManager;
import com.melolchik.common.util.UpdateCrashManager;
import com.melolchik.common.util.Util;

import java.util.List;


/**
 * Created by Olga Melekhina on 09.06.2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements PermissionManager.PermissionResultListener,
        NetworkStateReceiver.NetworkStateListener {

    /**
     * The constant ID_FRAGMENT_CONTAINER.
     */

    private BroadcastReceiver mBroadcastReceiver;

    /**
     * The M permission manager.
     */
    protected PermissionManager mPermissionManager = new PermissionManager();

    /**
     * Gets content view id.
     *
     * @return the content view id
     */
    protected abstract int getContentViewId();

    protected abstract @IdRes int getFragmentContainerId();

    /**
     * The M network state receiver.
     */
    protected NetworkStateReceiver mNetworkStateReceiver;
    protected UpdateCrashManager mUpdateCrashManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initBroadcastReceiver();
        mNetworkStateReceiver = new NetworkStateReceiver(this);
        //boolean allowInstabugAndHockeyAppUpdate = getResources().getBoolean(R.bool.allowInstabugAndHockeyAppUpdate);
        mUpdateCrashManager = new UpdateCrashManager(isAllowInstabugAndHockeyAppUpdate());

    }

    protected abstract boolean isAllowInstabugAndHockeyAppUpdate();

    @Override
    public void networkAvailable() {
        BaseFragment fragment = getCurrentFragment();
        if (fragment != null && fragment instanceof NetworkStateReceiver.NetworkStateListener) {
            ((NetworkStateReceiver.NetworkStateListener) fragment).networkAvailable();
        }

    }

    @Override
    public void networkUnavailable() {
        BaseFragment fragment = getCurrentFragment();
        if (fragment != null && fragment instanceof NetworkStateReceiver.NetworkStateListener) {
            ((NetworkStateReceiver.NetworkStateListener) fragment).networkUnavailable();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mNetworkStateReceiver, mNetworkStateReceiver.getIntentFilter());
        mUpdateCrashManager.registerHockeyAppManager(this);
        IntentFilter filter = getBroadcastIntentFilter();
        if (filter != null) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, getBroadcastIntentFilter());
        }

        if (mNeedGoToPrevFragment) {
            if (TextUtils.isEmpty(mPrevFragmentTag)) {
                goToPrevFragmentIfExist();
            } else {
                goToPrevFragment(mPrevFragmentTag);
            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mNetworkStateReceiver);
        mUpdateCrashManager.unregisterHockeyAppManagers();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUpdateCrashManager.unregisterHockeyAppManagers();

    }

    /**
     * Gets broadcast intent filter.
     *
     * @return the broadcast intent filter
     */
//region Broadcast Receiver
    protected IntentFilter getBroadcastIntentFilter() {
        return null;
    }

    private void initBroadcastReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BaseActivity.this.onReceive(context, intent);
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
        log("onReceive");
    }
    //endregion

    //region HockeyApp

    //region Working with fragments

    /**
     * The M need go to prev fragment.
     */
    protected boolean mNeedGoToPrevFragment = false;
    /**
     * The M prev fragment tag.
     */
    protected String mPrevFragmentTag = "";

    /**
     * Go to prev fragment.
     *
     * @param tag the tag
     */
    public void goToPrevFragment(String tag) {
        //Util.hideSoftKeyboard(this);
        try {
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStack(tag, 0);
            fm.executePendingTransactions();
            mNeedGoToPrevFragment = false;
            mPrevFragmentTag = "";
        } catch (IllegalStateException ignored) {
            // There's no way to avoid getting this if saveInstanceState has already been called.
            log("IllegalStateException " + ignored);
            mNeedGoToPrevFragment = true;
            mPrevFragmentTag = tag;
        }
    }

    /**
     * Go to prev fragment if exist boolean.
     *
     * @return true, if prev fragment exist and went to prev, false, if prev fragment not exist and stay in present state
     */
    public boolean goToPrevFragmentIfExist() {
        //Util.hideSoftKeyboard(this);
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        // log("goToPrevFragmentIfExist count = " + count);
        if (count > 1) {
            try {
                fm.popBackStack();
                fm.executePendingTransactions();
                mNeedGoToPrevFragment = false;
                mPrevFragmentTag = "";
                return true;
            } catch (IllegalStateException ignored) {
                // There's no way to avoid getting this if saveInstanceState has already been called.
                log("IllegalStateException " + ignored);
                mNeedGoToPrevFragment = true;
                mPrevFragmentTag = "";
                return false;
            }


        }
        return false;
    }


    /**
     * Show fragment with anim.
     *
     * @param newFragment    the new fragment
     * @param addToBackStack the add to back stack
     */
    public void showFragmentWithAnim(BaseFragment newFragment, boolean addToBackStack) {
        showFragment(newFragment, addToBackStack, true);
    }

    /**
     * Show fragment.
     *
     * @param newFragment       the new fragment
     * @param addToBackStack    the add to back stack
     * @param withFadeAnimation the with fade animation
     */
    public void showFragment(BaseFragment newFragment, boolean addToBackStack, boolean withFadeAnimation) {
        if (newFragment == null) return;
        FragmentManager fm = getSupportFragmentManager();
        // BaseFragment currentFragment = (BaseFragment) fm.findFragmentById(ID_FRAGMENT_CONTAINER);
        //log("changeFragment:currentFragment = " + currentFragment);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (withFadeAnimation) {
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        if (addToBackStack) {
            fragmentTransaction.add(getFragmentContainerId(), newFragment);
            fragmentTransaction.addToBackStack(newFragment.getTagForStack());
        } else {
            fragmentTransaction.replace(getFragmentContainerId(), newFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    /**
     * Gets current fragment.
     *
     * @return the current fragment
     */
    public BaseFragment getCurrentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(getFragmentContainerId());
        return (BaseFragment) currentFragment;
    }


    /**
     * Clean back stack.
     */
    protected void cleanBackStack() {
        try {
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.executePendingTransactions();
        } catch (IllegalStateException ignored) {
            // There's no way to avoid getting this if saveInstanceState has already been called.
            log("IllegalStateException " + ignored);
            log_instabug("cleanBackStack IllegalStateException " + ignored);

        }

    }
    //endregion

    @Override
    public void onBackPressed() {
        BaseFragment fragment = getCurrentFragment();
        if (fragment != null) {
            if (fragment.onBackPressed()) return;
        }
        if (goToPrevFragmentIfExist()) return;
        finish();
        super.onBackPressed();

    }

    /**
     * On home.
     */
    public void onHome() {
        Util.hideSoftKeyboard(this);
        if (goToPrevFragmentIfExist()) return;
    }

    public boolean mProgressBarVisible = false;

  /*  public void setProgressBarVisible(boolean isVisible) {
        mProgressBarVisible = isVisible;
        ProgressDialogFragment.setProgressBarVisible(this,isVisible);
    }*/


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

    //region PERMISSIONS

    /**
     * Check storage permission granted boolean.
     *
     * @return the boolean
     */
    public boolean checkStoragePermissionGranted() {
        return mPermissionManager.checkPermissionGrantedByCode(this, this,
                PermissionManager.REQUEST_CODE_ACCESS_STORAGE);
    }

    /**
     * Check location permission granted boolean.
     *
     * @return the boolean
     */
    public boolean checkLocationPermissionGranted() {
        return mPermissionManager.checkPermissionGrantedByCode(this, this,
                PermissionManager.REQUEST_CODE_ACCESS_LOCATION);
    }

    /**
     * Check phone state permission granted boolean.
     *
     * @return the boolean
     */
    public boolean checkPhoneStatePermissionGranted() {
        return mPermissionManager.checkPermissionGrantedByCode(this, this,
                PermissionManager.REQUEST_CODE_ACCESS_PHONE_STATE);
    }

    /**
     * Check bluetooth permission granted boolean.
     *
     * @return the boolean
     */
    public boolean checkBluetoothPermissionGranted() {
        return mPermissionManager.checkPermissionGrantedByCode(this, this,
                PermissionManager.REQUEST_CODE_ACCESS_BLUETOOTH);
    }

    /**
     * Is location permission granted boolean.
     *
     * @return the boolean
     */
    public boolean isLocationPermissionGranted() {
        return mPermissionManager.isLocationPermissionGranted(this);
    }

    /**
     * Is storage permission granted boolean.
     *
     * @return the boolean
     */
    public boolean isStoragePermissionGranted() {
        return mPermissionManager.isStoragePermissionGranted(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermissionManager != null) {
            mPermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * From  PermissionResultListener
     * Here can be only part of permissions granted, if permissionsNotGranted list is not empty
     * For get all requested permission list use  PermissionManager.getPermissionListByCode()
     * If two lists are equal ==> permission all denied
     *
     * @param requestCode               the request code
     * @param permissionsNotGrantedList the permissions not granted
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> permissionsNotGrantedList) {
        log("onPermissionsGranted permissionsNotGranted = " + permissionsNotGrantedList);
        BaseFragment fragment = getCurrentFragment();
        if (fragment != null && fragment instanceof PermissionManager.PermissionResultListener) {
            ((PermissionManager.PermissionResultListener) fragment).onPermissionsGranted(requestCode,permissionsNotGrantedList);
        }
    }

    /**
     * From  PermissionResultListener
     *
     * @param requestCode the request code
     */
    @Override
    public void onPermissionsDenied(int requestCode) {
        log("onPermissionsDenied");
        BaseFragment fragment = getCurrentFragment();
        if (fragment != null && fragment instanceof PermissionManager.PermissionResultListener) {
            ((PermissionManager.PermissionResultListener) fragment).onPermissionsDenied(requestCode);
        }
    }

    //endregion PERMISSIONS
}
