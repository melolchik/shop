package com.melolchik.shopapp.ui.adapters.decors;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by melolchik on 23.03.2016.
 */
public class AlignmentItemDecoration extends RecyclerView.ItemDecoration {
    protected final int mItemMargin;


    public AlignmentItemDecoration(int itemMargin) {
        mItemMargin = itemMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final int parentWidth = parent.getWidth();
        int padding = mItemMargin / 2;
        outRect.left = padding;
        outRect.right = padding;
        outRect.top = padding;
        outRect.bottom = padding;

    }
}