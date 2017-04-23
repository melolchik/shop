package com.melolchik.common.util;

import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;


/**
 * Created by Olga Melekhina on 22.12.2016.
 */

public class MyTextUtil {

    public static Spannable getColoredSpanned(String text,@ColorInt int color) {
        Spannable spannedString = new SpannableString(text);
        spannedString.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannedString;
    }

    public static Spannable getBoldSpanned(String text) {
        Spannable spannedString = new SpannableString(text);
        spannedString.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannedString;
    }

 /*   public static Spannable getFontSpanned(String text, Typeface typeface) {
        Spannable spannedString = new SpannableString(text);
        spannedString.setSpan(new CustomSpan(typeface), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannedString;
    }

    public static Spannable getFontSpanned(String text, Typeface typeface, float textSize) {
        Spannable spannedString = new SpannableString(text);
        spannedString.setSpan(new CustomSpan(typeface,textSize), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannedString;
    }*/
}
