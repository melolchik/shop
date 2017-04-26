package com.melolchik.shopapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.melolchik.common.ui.adapters.BaseListAdapter;
import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.ui.views.ProductView;
import com.melolchik.shopapp.utils.SettingsUtil;

/**
 * Created by melolchik on 23.04.2017.
 */
public class ProductGridAdapter extends BaseListAdapter<Product> {

    /**
     * The interface On product click listener.
     */
    public interface OnProductClickListener {
        /**
         * On product click.
         *
         * @param product the product
         */
        void onProductClick(Product product);
    }

    /**
     * The M on item click listener.
     */
    protected OnProductClickListener mOnItemClickListener;

    /**
     * Instantiates a new Product grid adapter.
     *
     * @param recyclerView the recycler view
     */
    public ProductGridAdapter(RecyclerView recyclerView){
        super(recyclerView);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductView productView = new ProductView(parent.getContext());
        int width = SettingsUtil.getItemSize(parent.getWidth());
        productView.setLayoutParams(new RelativeLayout.LayoutParams(width,width));
        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ProductViewHolder){
            ((ProductViewHolder) holder).setData(getItem(position),mOnItemClickListener);
        }
    }

    /**
     * The type Product view holder.
     */
    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        /**
         * The M product view.
         */
        protected final ProductView mProductView;

        /**
         * Instantiates a new Product view holder.
         *
         * @param view the view
         */
        ProductViewHolder(ProductView view){
            super(view);
            mProductView = view;
        }

        /**
         * Set data.
         *
         * @param product  the product
         * @param listener the listener
         */
        public void setData(Product product,final OnProductClickListener listener){
            mProductView.bind(product);
            mProductView.setClickable(true);
            mProductView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.onProductClick(mProductView.getProduct());
                    }
                }
            });
        }
    }

    /**
     * Sets on product click listener.
     *
     * @param onItemClickListener the on item click listener
     */
    public void setOnProductClickListener(OnProductClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
