package com.melolchik.shopapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.melolchik.common.ui.adapters.BaseListAdapter;
import com.melolchik.common.util.Util;
import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.ui.views.ProductView;
import com.melolchik.shopapp.utils.SettingsUtil;

/**
 * Created by melolchik on 23.04.2017.
 */

public class ProductGridAdapter extends BaseListAdapter<Product> {

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
            ((ProductViewHolder) holder).setData(getItem(position));
        }
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        protected final ProductView mProductView;
        ProductViewHolder(ProductView view){
            super(view);
            mProductView = view;
        }

        public void setData(Product product){
            mProductView.bind(product);
        }
    }
}
