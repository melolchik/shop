package com.melolchik.common.ui.views.customfont;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Olga Melekhina on 20.06.2016.
 */
public class LinkableTextView extends CustomFontTextView {
    public LinkableTextView(Context context) {
        this(context,null);
    }

    public LinkableTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LinkableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    public void setLinkableText(String text, final View.OnClickListener listener) {
        Spannable spLabel = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if(listener != null){
                    listener.onClick(LinkableTextView.this);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.bgColor = 0x00000000;
                super.updateDrawState(ds);
            }
        };

        spLabel.setSpan(clickableSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setMovementMethod(LinkMovementMethod.getInstance());
        setText(spLabel);
    }

    public void setLinkableText(@StringRes int stringResId, final View.OnClickListener listener){
        String value = getContext().getString(stringResId);
        setLinkableText(value,listener);
    }
}
