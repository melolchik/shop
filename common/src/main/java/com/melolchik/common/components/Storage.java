package com.melolchik.common.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

/**
 * Created by Olga Melekhina on 30.09.2016.
 */
public abstract class Storage {

    /**
     * The M context.
     */
    protected final Context mContext;
    /**
     * The M shared preferences.
     */
    protected final SharedPreferences mSharedPreferences;


    protected Storage(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Gets boolean value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the boolean value
     */
    protected boolean getBooleanValue(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * Sets boolean value.
     *
     * @param key   the key
     * @param value the value
     */
    protected void setBooleanValue(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Gets string value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the string value
     */
    protected String getStringValue(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    /**
     * Sets string value.
     *
     * @param key   the key
     * @param value the value
     */
    protected void setStringValue(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Gets int value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the int value
     */
    protected int getIntValue(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    /**
     * Sets int value.
     *
     * @param key   the key
     * @param value the value
     */
    protected void setIntValue(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Gets double value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the double value
     */
    protected double getDoubleValue(String key, double defaultValue) {
        return Double.longBitsToDouble(mSharedPreferences.getLong(key, Double.doubleToRawLongBits(defaultValue)));
    }

    /**
     * Sets double value.
     *
     * @param key   the key
     * @param value the value
     */
    protected void setDoubleValue(String key, double value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();
    }

    protected Date getDateValue(String key, Date defaultValue) {
        long long_val = mSharedPreferences.getLong(key, 0);
        if (long_val == 0) return defaultValue;
        Date date = new Date(long_val);
        return date;
    }

    protected void setDateValue(String key, Date date) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (date == null) {
            editor.putLong(key, 0);
        } else {
            editor.putLong(key, date.getTime());
        }
        editor.commit();
    }

    /**
     * Gets float value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the float value
     */
    protected float getFloatValue(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * Sets float value.
     *
     * @param key   the key
     * @param value the value
     */
    protected void setFloatValue(String key, float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public abstract void resetData();

}
