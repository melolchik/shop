package com.melolchik.shopapp.ui.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.melolchik.common.ui.dialogs.BaseDialogFragment;
import com.melolchik.common.ui.views.customfont.CustomFontEditText;
import com.melolchik.common.ui.views.customfont.CustomFontTextView;
import com.melolchik.common.util.Util;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.ui.views.OnKeyboardListener;
import com.melolchik.shopapp.ui.views.KeyboardView;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

import static com.melolchik.common.util.AppLogger.log;

/**
 * Created by melolchik on 25.04.2017.
 */
public class AddProductDialogFragment extends BaseDialogFragment implements OnKeyboardListener {

    protected final static String ARG_PRODUCT = "ARG_PRODUCT";

    /**
     * The M txt product name.
     */
    @BindView(R.id.txt_product_name)
    CustomFontTextView mTxtProductName;
    /**
     * The M txt product cost.
     */
    @BindView(R.id.txt_product_cost)
    CustomFontTextView mTxtProductCost;
    /**
     * The M edit weight.
     */
    @BindView(R.id.edit_weight)
    CustomFontEditText mEditWeight;
    /**
     * The M keyboard view.
     */
    @BindView(R.id.keyboard_view)
    KeyboardView mKeyboardView;

    protected Product mProduct;

    protected float mWeight = 0f;

    protected String mWeightString = "0";

    final static String REG_EXP_WEIGHT = "[0-9]+|([0-9]+,?[0-9]{0,3})";

    public interface OnAddProductListener{
        void addProduct(Product product,float weight);
    }

    protected OnAddProductListener mOnAddProductListener;

    /**
     * Create instance base dialog fragment.
     *
     * @return the base dialog fragment
     */
    public static AddProductDialogFragment createInstance(Product product) {
        AddProductDialogFragment dialogFragment = new AddProductDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT,product);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    protected int getViewLayoutID() {
        return R.layout.dialog_fragment_add_product;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mProduct = args.getParcelable(ARG_PRODUCT);
        }
    }

    @Override
    protected void onCreateView(View rootView) {

        mKeyboardView.setKeyboardListener(this);
        if(mProduct == null) return;
        mTxtProductName.setText(mProduct.getProductName());
        updateWeight();
        updateCost();

    }

    public void setOnAddProductListener(OnAddProductListener onAddProductListener) {
        mOnAddProductListener = onAddProductListener;
    }

    protected void updateCost(){
        if(mProduct == null) return;
        mWeight = Util.getFloatFromString(mWeightString.replace(",","."));
        String value = getString(R.string.add_product_cost_template,mProduct.getProductPrice(),mWeight);
        mTxtProductCost.setText(value);
    }

    @Override
    public String getOwnTag() {
        return getClass().getCanonicalName();
    }

    @Override
    public void onResume() {

        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(params);
        super.onResume();
    }


    /**
     * On view clicked.
     *
     * @param view the view
     */
    @OnClick({R.id.btn_cancel, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismissAllowingStateLoss();
                break;
            case R.id.btn_add:
                log("m = " + mWeightString);
                if(mWeightString.isEmpty() || mWeightString.equals("0")) return;
                dismissAllowingStateLoss();
                if(mOnAddProductListener != null){
                    mOnAddProductListener.addProduct(mProduct,mWeight);
                }
                break;
        }
    }

    protected boolean isValid(String value){
        if(TextUtils.isEmpty(value)) return true;
        final Pattern PATTERN = Pattern.compile(REG_EXP_WEIGHT);
        return  PATTERN.matcher(value).matches();
    }

    @Override
    public void onChar(Character character) {
        if(isValid(mWeightString + character)){
            mWeightString += character;
            updateWeight();
            updateCost();
        }

        //log("isValid = " + isValid(mEditWeight.getText().toString()));
    }

    @Override
    public void onClear() {
        int length = mWeightString.length();
        if(length > 0) {
            mWeightString = mWeightString.substring(0, length - 1);
            updateWeight();
            updateCost();
        }
    }

    protected void updateWeight(){
        if(mWeightString.isEmpty()){
            mWeightString = "0";
        }
        while(mWeightString.length() > 1 && mWeightString.startsWith("0")){
            mWeightString = mWeightString.substring(1);
        }
        mEditWeight.setText(mWeightString);
        mEditWeight.append(getString(R.string.add_product_kg));
    }
}
