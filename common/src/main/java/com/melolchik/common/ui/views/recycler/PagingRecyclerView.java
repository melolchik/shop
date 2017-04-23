package com.melolchik.common.ui.views.recycler;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.melolchik.common.R;
import com.melolchik.common.network.BaseLoader;
import com.melolchik.common.ui.adapters.BaseListAdapter;
import com.melolchik.common.ui.views.customfont.CustomFontTextView;
import com.melolchik.common.ui.views.recycler.swipe.BottomSwipeRefreshLayout;
import com.melolchik.common.util.Util;

import java.util.List;

import butterknife.BindView;

/**
 * Created by melolchik on 06.04.2017.
 *
 * @param <ItemType> the type parameter
 */
public abstract class PagingRecyclerView<ItemType> extends FrameLayout
        implements BottomSwipeRefreshLayout.OnRefreshListener,
        BaseLoader.OnLoaderListener<ItemType> {
    /**
     * The M txt empty.
     */
    CustomFontTextView mTxtEmpty;
    /**
     * The M recycler view.
     */
    RecyclerView mRecyclerView;
    /**
     * The M refresh layout.
     */
    BottomSwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * The M progress.
     */
    @BindView(android.R.id.progress)
    ProgressBar mProgressBar;

    /**
     * The M adapter.
     */
    protected BaseListAdapter<ItemType> mAdapter;

    /**
     * The M loader.
     */
    protected BaseLoader<ItemType> mLoader;

    /**
     * Instantiates a new Paging recycler view.
     *
     * @param context the context
     */
    public PagingRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Paging recycler view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public PagingRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new Paging recycler view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public PagingRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_paging_recycler_view, this);
        mTxtEmpty = (CustomFontTextView) findViewById(R.id.txt_empty);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (BottomSwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mProgressBar = (ProgressBar) findViewById(android.R.id.progress);
        initRecyclerView(mRecyclerView);
        mAdapter = createAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.GRAY);
            mSwipeRefreshLayout.setOnRefreshListener(this);
        }

        mLoader = createLoader();
        setProgressBarVisible(false);
        changeState(false);
    }

    /**
     * Create adapter base list adapter.
     *
     * @param recyclerView the recycler view
     * @return the base list adapter
     */
    protected abstract BaseListAdapter<ItemType> createAdapter(RecyclerView recyclerView);

    /**
     * Create loader base loader.
     *
     * @return the base loader
     */
    protected abstract BaseLoader<ItemType> createLoader();

    /**
     * Init recycler view.
     *
     * @param recyclerView the recycler view
     */
    protected void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Load list.
     *
     * @param refresh the refresh
     */
    public void loadList(boolean refresh) {
       // log("loadList = " + refresh);
        if (mLoader == null || refresh) {
            mAdapter.setData(null);
            mLoader = createLoader();
        } else {
            if (mLoader.isLoaded()) {
                //log("mLoader.hasNext() = " + mLoader.hasNext());
                if (mLoader.hasNext()) {
                    mLoader = mLoader.getNext();
                } else {
                    setSwipeRefreshing(false);
                    setSwipeEnable(false);
                    return;
                }
            }
        }
        if (mLoader != null) {
            mLoader.load(this);
        }
    }

    /**
     * Sets progress bar visible.
     *
     * @param isVisible the is visible
     */
    protected void setProgressBarVisible(boolean isVisible) {
        if (mProgressBar == null) return;
        mProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }


    /**
     * Change state.
     *
     * @param isEmptyList the is empty list
     */
    protected void changeState(boolean isEmptyList) {
        if (mTxtEmpty != null) {
            mTxtEmpty.setVisibility(isEmptyList ? View.VISIBLE : View.GONE);
        }
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setVisibility(isEmptyList ? View.GONE : View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(isEmptyList ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Sets swipe enable.
     *
     * @param swipeEnable the swipe enable
     */
    protected void setSwipeEnable(boolean swipeEnable) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setEnabled(swipeEnable);
        }
    }

    /**
     * Sets swipe refreshing.
     *
     * @param refreshing the refreshing
     */
    protected void setSwipeRefreshing(boolean refreshing) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(refreshing);
        }
    }

    @Override
    public void onStartLoad(int page) {
        if (page == 1) {
            setSwipeEnable(false);
            mAdapter.setData(null);
            setProgressBarVisible(true);
        }
    }

    @Override
    public void onDataLoaded(int page, List<ItemType> objectList) {
        //log("onDataLoaded: " + objectList);
        if (mAdapter == null) return;
        mAdapter.addData(objectList);
        changeState(page == 1 && objectList.isEmpty());
        if (page == 1) {
            setSwipeEnable(true);
            setProgressBarVisible(false);
        } else {
            setSwipeRefreshing(false);
        }
        if (!mLoader.hasNext()) {
            setSwipeRefreshing(false);
            setSwipeEnable(false);
        }
    }

    @Override
    public void onFailure(int page, int statusCode, String message) {
        Util.showErrorToast(message);
        changeState(page == 1);
        if (page == 1) {
            setSwipeEnable(true);
            setProgressBarVisible(false);
        } else {
            setSwipeRefreshing(false);
        }

    }

    @Override
    public void onError(int page, int statusCode, String message) {
        Util.showErrorToast(message);
        changeState(page == 1);
        if (page == 1) {
            setSwipeEnable(true);
            setProgressBarVisible(false);
        } else {
            setSwipeRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        loadList(false);
    }
}
