package com.melolchik.common.ui.views.customfont;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;


/**
 * Created by Olga Melekhina on 15.06.2016.
 */
public class CustomFontCheckedTextView extends AppCompatCheckedTextView {

    public CustomFontCheckedTextView(Context context) {
        this(context, null);
    }

    public CustomFontCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int fontCode = CustomFontUtil.getCustomFontCode(context,attrs);
        final Typeface customTypeface = CustomFont.fromCode(fontCode).asTypeface(context);
        setTypeface(customTypeface);
    }
}


