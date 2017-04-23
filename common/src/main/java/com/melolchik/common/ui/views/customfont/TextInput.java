package com.melolchik.common.ui.views.customfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.melolchik.common.R;
import com.melolchik.common.util.Util;

/**
 * Created by Olga Melekhina on 14.09.2016.
 */
public class TextInput extends FrameLayout {

    protected
    TextInputLayout mTextInputLayout;

    protected
    AppCompatEditText mEditText;

    protected String mErrorText;
    protected String mErrorTextNotCorrect;

    protected
    @LayoutRes
    int getLayoutId() {
        return R.layout.view_text_input_layout;
    }

    public TextInput(Context context) {
        this(context, null);
    }

    public TextInput(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, getLayoutId(), this);
        mTextInputLayout = (TextInputLayout) findViewById(R.id.input_layout);
        mEditText = (AppCompatEditText) findViewById(R.id.input);

        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextInput,
                0, 0);
        String hint = "";
        int type = 0;
        boolean errorEnabled = false;

        int errorTextAppearance = 0;
        int hintTextAppearance = 0;
        int editTextAppearance = 0;
        try {
            hint = array.getString(R.styleable.TextInput_hint);
            type = array.getInteger(R.styleable.TextInput_inputType, 0x00000001); //default text
            errorEnabled = array.getBoolean(R.styleable.TextInput_errorShow, false);
            mErrorText = array.getString(R.styleable.TextInput_errorEmpty);
            mErrorTextNotCorrect = array.getString(R.styleable.TextInput_errorNotCorrect);

            //styles
            errorTextAppearance = array.getResourceId(R.styleable.TextInput_errorTextAppearance, 0);
            hintTextAppearance = array.getResourceId(R.styleable.TextInput_hintTextAppearance, 0);
            editTextAppearance = array.getResourceId(R.styleable.TextInput_editTextAppearance, 0);
        } finally {
            array.recycle();
        }
    /*    if(errorTextAppearance != 0){
            mTextInputLayout.setErrorTextAppearance(errorTextAppearance);
        }
        if(hintTextAppearance != 0){
            mTextInputLayout.setHintTextAppearance(hintTextAppearance);
        }

        if(editTextAppearance != 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mEditText.setTextAppearance(editTextAppearance);
            } else {
                mEditText.setTextAppearance(getContext(),editTextAppearance);
            }

        }*/
        //mEditText.setHint(hint);
        mEditText.setInputType(type);
        mTextInputLayout.setTypeface(mEditText.getTypeface());
        mTextInputLayout.setHint(hint);
        mTextInputLayout.setErrorEnabled(errorEnabled);

    }

    public void setText(String text) {
        mEditText.setText(text);
    }

    public String getText() {
        return mEditText.getText().toString().trim();
    }

    @Override
    public void setOnTouchListener(final OnTouchListener listener) {
        super.setOnTouchListener(listener);
        mEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (listener != null) {
                    listener.onTouch(TextInput.this, event);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void setOnFocusChangeListener(final OnFocusChangeListener listener) {
        super.setOnFocusChangeListener(listener);
        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (listener != null) {
                    listener.onFocusChange(TextInput.this, hasFocus);
                }
            }
        });
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        mEditText.requestFocus(direction, previouslyFocusedRect);
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    public void setErrorText(String errorText) {
        mErrorText = errorText;
    }

    public void setErrorEnable(boolean isErrorEnable) {
        mTextInputLayout.setErrorEnabled(isErrorEnable);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mEditText.setEnabled(enabled);
    }

    public void setError(String text) {
        mTextInputLayout.setError(text);
    }

    public void showErrorEmpty() {
        if (TextUtils.isEmpty(mErrorText)) return;
        setError(mErrorText);
        showErrorToast(mErrorText);
    }

    public void showErrorNotCorrect() {
        if (TextUtils.isEmpty(mErrorTextNotCorrect)) return;
        setError(mErrorTextNotCorrect);
        showErrorToast(mErrorTextNotCorrect);
    }

    public void setError(@StringRes int textResId) {
        mTextInputLayout.setError(getContext().getString(textResId));
    }

    // @Override
    public void setHint(@Nullable CharSequence hint) {
        mTextInputLayout.setHint(hint);
    }

    public void setHint(@StringRes int hint) {
        mTextInputLayout.setHint(getContext().getString(hint));
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        mEditText.addTextChangedListener(textWatcher);
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        mEditText.removeTextChangedListener(textWatcher);
    }

    public void showErrorToast(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    public int getInputType() {
        return mEditText.getInputType();
    }

    public boolean checkEmpty() {

        String text = getText();
        if (TextUtils.isEmpty(text)) {
            showErrorEmpty();
            return false;
        }
        setError("");
        return true;

    }

    public boolean checkCorrect() {

        String text = getText();
        if (mEditText.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)) {
            if (!Util.isEmailValid(text)) {
                showErrorNotCorrect();
                return false;
            }
        }
        setError("");
        return true;

    }

}
