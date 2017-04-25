package com.melolchik.shopapp.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.melolchik.shopapp.ui.adapters.KeyboardGridAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by melolchik on 25.04.2017.
 */

public class KeyboardView extends RecyclerView {

    protected final static int COUNT_IN_ROW = 3;
    protected KeyboardGridAdapter mGridAdapter;
    protected final Character[] KEYBOARD_DATA = new Character[]{'1','2','3','4','5','6','7','8','9',',','0','x'};

    public KeyboardView(Context context) {
        this(context,null);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), COUNT_IN_ROW);
        setLayoutManager(layoutManager);
        //mRecyclerView.addItemDecoration(new AlignmentItemDecoration(SettingsUtil.getProductItemMargin()));
        mGridAdapter = new KeyboardGridAdapter(this);
       // mGridAdapter.setOnProductClickListener(this);
        setAdapter(mGridAdapter);
        List<Character> listData = new ArrayList<Character>(Arrays.asList(KEYBOARD_DATA));
        mGridAdapter.setData(listData);
    }

    public void setKeyboardListener(OnKeyboardListener keyboardListener) {
        mGridAdapter.setKeyboardListener(keyboardListener);
    }
}
