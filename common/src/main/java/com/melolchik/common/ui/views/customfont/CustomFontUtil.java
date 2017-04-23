package com.melolchik.common.ui.views.customfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.melolchik.common.R;


/**
 * Created by melolchik on 27.03.2017.
 */

public class CustomFontUtil {

    public static int getCustomFontCode(Context context, AttributeSet attrs) {

        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomFont,
                0, 0);
        int fontCode = 0;
        try {
            fontCode = array.getInteger(R.styleable.CustomFont_customFont, 100);
        } finally {
            array.recycle();
        }
        return fontCode;

    }
}
