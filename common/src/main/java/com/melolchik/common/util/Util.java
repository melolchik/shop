package com.melolchik.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.melolchik.common.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Olga Melekhina on 01.07.2016.
 */
public class Util {

    /**
     * The constant mContext.
     */
    public static Context mContext;

    /**
     * Init.
     *
     * @param context the context
     */
    public static void init(Context context){
        mContext = context;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * Not empty string string.
     *
     * @param origin the origin
     * @return the string
     */
    public static String notEmptyString(String origin) {
        return origin == null ? "" : origin.trim();
    }

    /**
     * Convert dp to pixel float.
     *
     * @param dp the dp
     * @return the float
     */
    public static float convertDpToPixel(float dp) {
        if(mContext == null) return 0f;
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * Is email valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public  static boolean isEmailValid(String email){
        if(TextUtils.isEmpty(email)) return false;

        return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Is mobile valid boolean.
     *
     * @param phone the phone
     * @return the boolean
     */
    public static boolean isMobileValid(String phone) {

        if(TextUtils.isEmpty(phone)) return false;
       /* PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber usNumberProto = phoneUtil.parse(phone, "US");
            return phoneUtil.isValidNumber(usNumberProto);
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }*/
        return false;
    }


    /**
     * Is network available boolean.
     *
     * @return the boolean
     */
    public static boolean isNetworkAvailable() {
        if (mContext == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Hide soft keyboard.
     *
     * @param activity the activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null && activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager
                    = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Show soft keyboard.
     *
     * @param activity the activity
     */
    public static void showSoftKeyboard(Activity activity) {
        if (activity != null/* && activity.getCurrentFocus() != null*/) {
            InputMethodManager inputMethodManager
                    = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    /**
     * Gets screen width.
     *
     * @return the screen width
     */
    public static int getScreenWidth() {
        if (mContext == null) return 0;
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * Gets screen height.
     *
     * @return the screen height
     */
    public static int getScreenHeight() {
        if (mContext == null) return 0;
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * Gets status bar height.
     *
     * @return the status bar height
     */
    public static int getStatusBarHeight() {
        if(mContext == null) return 0;
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight() {
        if(mContext == null) return 0;
        //return mContext.getResources().getDimensionPixelSize(android.support.v7.appcompat.R.dimen.abc_action_bar_default_height_material);
        TypedValue tv = new TypedValue();
        mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        return mContext.getResources().getDimensionPixelSize(tv.resourceId);
    }
/*
    public static int getBottomNavBarHeight(Context context) {
        return (int)context.getResources().getDimension(R.dimen.height_bottom_nav_menu);

    }*/

    /**
     * Gets fragment height.
     *
     * @return the fragment height
     */
    public static int getFragmentHeight() {
        if(mContext == null) return 0;
        return getScreenHeight() - getStatusBarHeight();
    }

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    public static String getUniqueId() {

        if(mContext == null) return "";
        final String androidId = Settings.Secure.getString(
                mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }

  /*  public static double computeDistanceBetween(LatLng from, LatLng to){
        if(from == null && to == null) return 0f;
        double R = 6371000.0D; // earth’s radius (mean radius = 6 371km)
        double fi1 = Math.toRadians(from.latitude); //self conVertDegreeToRadians:firstPoint.latitude];
        double fi2 = Math.toRadians(to.latitude);// [self conVertDegreeToRadians:secondPoint.latitude];
        double deltaFi = Math.toRadians(to.latitude - from.latitude);
        double deltaLambda =  Math.toRadians(to.longitude - from.longitude);

        double x = deltaLambda * Math.cos((fi1 + fi2) * 0.5);
        double y = deltaFi;
        double distance = Math.sqrt(x * x + y * y) * R;
        return distance;
    }

    public static double computeDistanceBetweenInMiles(LatLng from, LatLng to){
        return Util.transformMetersToMiles(computeDistanceBetween(from, to));
    }*/

    /**
     * Transform meters to miles double.
     *
     * @param meterValue the meter value
     * @return the double
     */
    public static double transformMetersToMiles(double meterValue){
        return meterValue/1609;
    }

   /* public static LatLng locationToLatLng(Location location){
        if (location == null) return null;
        return new LatLng(location.getLatitude(),location.getLongitude());
    }*/

    /**
     * Gets int from string.
     *
     * @param value the value
     * @return the int from string
     */
    public static int getIntFromString(String value) {

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    /**
     * Gets float from string.
     *
     * @param value the value
     * @return the float from string
     */
    public static float getFloatFromString(String value) {

        if (value == null) return 0.0f;
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            return 0.0f;
        }
    }


    /**
     * Equal locations boolean.
     *
     * @param one the one
     * @param two the two
     * @return the boolean
     */
    public static boolean equalLocations(Location one, Location two){
        if(one == null || two == null) return false;
        String oneString = String.format( "%.3f_%.3f", one.getLatitude(),one.getLongitude());
        String twoString = String.format( "%.3f_%.3f", two.getLatitude(),two.getLongitude());
        return oneString.equals(twoString);
    }

   /* public static boolean equalLocations(LatLng one,LatLng two){
        if(one == null || two == null) return false;
        String oneString = String.format( "%.3f_%.3f", one.latitude,one.longitude);
        String twoString = String.format( "%.3f_%.3f", two.latitude,two.longitude);
        return oneString.equals(twoString);
    }*/


    /**
     * Show error toast.
     *
     * @param message the message
     */
    public static void showErrorToast(String message) {
        if (mContext == null)
            return;
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Show error toast.
     *
     * @param message the message
     */
    public static void showErrorToast(@StringRes int message) {
        if (mContext == null)
            return;
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Read raw text string.
     *
     * @param rawRes the raw res
     * @return the string
     */
    public static String readRawText(@RawRes int rawRes) {
        try (InputStream inputStream = mContext.getResources().openRawResource(rawRes);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();){
            int i;
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
            return byteArrayOutputStream.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }

    /**
     * Gets share link.
     *
     * @return the share link
     */
    public static String getShareLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("http://play.google.com/store/apps/details?id=");
        builder.append(mContext.getPackageName());
        return builder.toString();
    }

    public static boolean isTablet(){
        if(mContext == null)
            return false;
        return (mContext.getResources().getBoolean(R.bool.isTablet));
    }
    /**
     * Log.
     *
     * @param message the message
     */
    protected static void log(String message) {
        AppLogger.log(Util.class.getSimpleName() + " " + message);
    }

}
