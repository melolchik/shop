package com.melolchik.shopapp.ui.views;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.melolchik.common.ui.views.customfont.CustomFontTextView;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.dao.Purchase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 26.04.2017.
 */
public class PurchaseView extends FrameLayout {
    /**
     * The M txt product name.
     */
    @BindView(R.id.txt_product_name)
    CustomFontTextView mTxtProductName;
    /**
     * The M txt product price.
     */
    @BindView(R.id.txt_product_price)
    CustomFontTextView mTxtProductPrice;
    /**
     * The M txt cost one.
     */
    @BindView(R.id.txt_cost_one)
    CustomFontTextView mTxtCostOne;
    /**
     * The M txt cost two.
     */
    @BindView(R.id.txt_cost_two)
    CustomFontTextView mTxtCostTwo;

    /**
     * Instantiates a new Purchase view.
     *
     * @param context the context
     */
    public PurchaseView(@NonNull Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Purchase view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public PurchaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new Purchase view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public PurchaseView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.item_purchase_view, this);
        ButterKnife.bind(this, this);
    }

    public void bind(Purchase purchase){
        if(purchase == null) return;
        Product product = purchase.getProduct();
        if(product == null) return;
        mTxtProductName.setText(product.getProductName());
    }
}
