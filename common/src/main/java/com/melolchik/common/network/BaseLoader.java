package com.melolchik.common.network;

import com.melolchik.common.util.AppLogger;

import java.util.List;

/**
 * Created by Olga Melekhina on 24.11.2016.
 */

public abstract class BaseLoader<ItemType> {

    protected final int mPage;
    protected boolean mHasNext = true;
    protected OnLoaderListener<ItemType> mLoaderListener;
    protected boolean mIsLoaded = false;

    public interface OnLoaderListener<ItemType> {
        void onStartLoad(int page);

        void onDataLoaded(int page, List<ItemType> objectList);

        void onFailure(int page, int statusCode, String message);

        void onError(int page, int statusCode, String message);
    }

    protected BaseLoader(int page) {
        mPage = page;
    }

    public boolean hasNext() {
        return mHasNext;
    }

    public abstract BaseLoader<ItemType>  getNext();

    public boolean isLoaded() {
        return mIsLoaded;
    }

    public void load(OnLoaderListener<ItemType> loaderListener) {
        mLoaderListener = loaderListener;
        loadList();
    }

    protected abstract void loadList();

    public void onStartLoad() {
        if (mLoaderListener != null) {
            mLoaderListener.onStartLoad(mPage);
        }
    }

    public void onSuccess(List<ItemType> list) {
        mIsLoaded = true;
        if (list.isEmpty()) {
            mHasNext = false;
        }
        if (mLoaderListener != null) {
            mLoaderListener.onDataLoaded(mPage, list);
        }
    }

    public void onError(int statusCode, String message) {
        if (mLoaderListener != null) {
            mLoaderListener.onError(mPage, statusCode, message);
        }
    }

    public void onFailure(int statusCode, String message) {
        if (mLoaderListener != null) {
            mLoaderListener.onFailure(mPage, statusCode, message);
        }
    }


    protected void log(String message) {
        AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }

}
