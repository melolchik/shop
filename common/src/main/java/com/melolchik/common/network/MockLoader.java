package com.melolchik.common.network;

import com.melolchik.common.util.AppLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Melekhina on 24.11.2016.
 */

public class MockLoader<ItemType> extends BaseLoader<ItemType> {

    public static int MAX_PAGE_COUNT = 5;
    public static int PAGE_SIZE = 10;

    protected RandomCreator<ItemType> mRandomCreator;

    public static final <ItemType> MockLoader<ItemType> createLoader() {
        return new <ItemType>MockLoader<ItemType>(1);
    }

    protected <ItemType> MockLoader(int page) {
        super(page);
    }

    @Override
    public MockLoader<ItemType> getNext() {
        MockLoader<ItemType> mockLoader =  new <ItemType>MockLoader<ItemType>(mPage + 1);
        mockLoader.setRandomCreator(mRandomCreator);
        return mockLoader;
    }

    @Override
    public boolean hasNext() {
        return  (mPage < MAX_PAGE_COUNT) ;
    }

    public void setRandomCreator(RandomCreator<ItemType> randomCreator) {
        mRandomCreator = randomCreator;
    }

    protected void loadList() {
        android.os.Handler handler = new android.os.Handler();
        if (mLoaderListener != null) {
            mLoaderListener.onStartLoad(mPage);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIsLoaded = true;
                if (mLoaderListener != null) {
                    mLoaderListener.onDataLoaded(mPage, generateMockData());
                }
            }
        }, 500);
    }

    @Override
    public void onStartLoad() {
        if (mLoaderListener != null) {
            mLoaderListener.onStartLoad(mPage);
        }
    }

    @Override
    public void onSuccess(List<ItemType> response) {
        mIsLoaded = true;

        if (mLoaderListener != null) {
            mLoaderListener.onDataLoaded(mPage, generateMockData());
        }
    }

    protected List<ItemType> generateMockData() {
        List<ItemType> list = new ArrayList<>(PAGE_SIZE);

        for (int i = 0; i < PAGE_SIZE; i++) {
            if (mRandomCreator != null) {
                list.add(mRandomCreator.createRandomItem(i + (mPage - 1) * PAGE_SIZE));
            } else {
                list.add(null);
            }
        }


        return list;
    }


    protected void log(String message) {
        AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }

}
