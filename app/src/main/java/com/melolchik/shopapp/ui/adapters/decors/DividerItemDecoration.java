package com.melolchik.shopapp.ui.adapters.decors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melolchik.common.util.Util;
import com.melolchik.shopapp.R;


/**
 * Created by melolchik on 24.03.2016.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private int mPadding = 10;

    /**
     * Instantiates a new Divider item decoration.
     *
     * @param context the context
     */
    public DividerItemDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.shape_divider_gray);
    }

    /**
     * Instantiates a new Divider item decoration.
     *
     * @param context     the context
     * @param drawableRes the drawable res
     * @param padding     the padding
     */
    public DividerItemDecoration(Context context, @DrawableRes int drawableRes, int padding) {
        mDivider = ContextCompat.getDrawable(context, drawableRes);
        mPadding = padding;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int padding = (int) Util.convertDpToPixel(mPadding);
        int left = padding;
        int right = parent.getWidth() - padding;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            if(i == childCount - 1){
                bottom = child.getBottom() + params.bottomMargin;
                top = bottom - mDivider.getIntrinsicHeight();
            }

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
