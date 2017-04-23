package com.melolchik.common.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.ViewGroup;

import com.melolchik.common.R;

/**
 * Created by melolchik on 25.03.2016.
 */
public abstract class BaseDialogFragment extends AppCompatDialogFragment {

    protected  OnDismissListener mDismissListener;

    public interface OnDismissListener{
        void onDismiss();
    }

    protected abstract int getViewLayoutID();

    protected abstract void onCreateView(View rootView);

    public abstract String getOwnTag();


    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        if(getViewLayoutID() != 0) {
            final View rootView = View.inflate(getContext(), getViewLayoutID(), null);
            dialog.setContentView(rootView);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(/*android.graphics.CarColor.TRANSPARENT*/getBackgroundColor()));
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            onCreateView(rootView);
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) ((View) rootView.getParent())
                    .getLayoutParams();


            ((View) rootView.getParent()).setBackgroundColor(getBackgroundColor());
        }

    }

    protected @ColorInt
    int getBackgroundColor(){
        return ContextCompat.getColor(getContext(), R.color.color_transparent);
    }

    public void show(FragmentActivity activity) {
        if (activity == null) return;
        FragmentManager fm = activity.getSupportFragmentManager();
        if (fm.findFragmentByTag(this.getOwnTag()) != null) {
            return;
        }
        this.showAllowingStateLoss(fm, this.getOwnTag());
    }


    public void showAllowingStateLoss(FragmentManager manager, String tag) {
        if(manager == null) return;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    public OnDismissListener getDismissListener() {
        return mDismissListener;
    }

    public void setDismissListener(OnDismissListener dismissListener) {
        mDismissListener = dismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(mDismissListener != null){
            mDismissListener.onDismiss();
        }
    }
}
