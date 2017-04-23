package com.melolchik.common.ui.views.customfont;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/**
 * Created by Olga Melekhina on 22.12.2016.
 */
public class CustomSpan extends MetricAffectingSpan {

    /**
     * The M typeface.
     */
    protected final Typeface mTypeface;
    /**
     * The M text size.
     */
    protected final Float mTextSize;
    /**
     * The M color.
     */
    protected final Integer mColor ;


    private CustomSpan(Builder builder) {
        mTypeface = builder.mTypeface;
        mTextSize = builder.mTextSize;
        mColor = builder.mColor;
    }

    @Override
    public void updateMeasureState(TextPaint p) {

        if(mTypeface != null) {
            p.setTypeface(mTypeface);
        }
        if(mTextSize != null){
            p.setTextSize(mTextSize);
        }
        if(mColor != null){
            p.setColor(mColor);
        }
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        if(mTypeface != null) {
            tp.setTypeface(mTypeface);
        }
        if(mTextSize != null){
            tp.setTextSize(mTextSize);
        }
        if(mColor != null){
            tp.setColor(mColor);
        }
    }

    /**
     * Gets spannable.
     *
     * @param text the text
     * @return the spannable
     */
    public Spannable getSpannable(String text) {
        Spannable spannedString = new SpannableString(text);
        spannedString.setSpan(this, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannedString;
    }


    /**
     * The type Builder.
     */
    public static final class Builder {
        private Typeface mTypeface;
        private Float mTextSize;
        private Integer mColor;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        /**
         * Typeface builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder Typeface(Typeface val) {
            mTypeface = val;
            return this;
        }

        /**
         * Text size builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder TextSize(Float val) {
            mTextSize = val;
            return this;
        }

        /**
         * Color builder.
         *
         * @param val the val
         * @return the builder
         */
        public Builder Color(Integer val) {
            mColor = val;
            return this;
        }

        /**
         * Build custom span.
         *
         * @return the custom span
         */
        public CustomSpan build() {
            return new CustomSpan(this);
        }
    }
}
