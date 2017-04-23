package com.melolchik.shopapp.ui.views;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.melolchik.common.ui.views.customfont.CustomFontTextView;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.dao.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by melolchik on 23.04.2017.
 */
public class ProductView extends FrameLayout {
    /**
     * The M item image.
     */
    @BindView(R.id.item_image)
    ImageView mItemImage;
    /**
     * The M item name.
     */
    @BindView(R.id.item_name)
    CustomFontTextView mItemName;

    private Product mProduct = null;

    /**
     * Instantiates a new Product view.
     *
     * @param context the context
     */
    public ProductView(@NonNull Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Product view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ProductView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new Product view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public ProductView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.item_product_view, this);
        ButterKnife.bind(this, this);
    }

    public void bind(Product product){
        mProduct = product;
        if(product == null) return;
        mItemName.setText(product.getProductName());
    }

    public Product getProduct() {
        return mProduct;
    }
}
