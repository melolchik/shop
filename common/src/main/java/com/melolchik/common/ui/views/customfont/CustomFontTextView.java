package com.melolchik.common.ui.views.customfont;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * Created by Olga Melekhina on 15.06.2016.
 */
public class CustomFontTextView extends AppCompatTextView {

    public CustomFontTextView(Context context) {
        this(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int fontCode = CustomFontUtil.getCustomFontCode(context,attrs);
        final Typeface customTypeface = CustomFont.fromCode(fontCode).asTypeface(context);
        setTypeface(customTypeface);
    }
}


