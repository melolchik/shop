package com.melolchik.shopapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.melolchik.common.ui.adapters.BaseListAdapter;
import com.melolchik.shopapp.dao.Purchase;
import com.melolchik.shopapp.ui.views.ProductView;
import com.melolchik.shopapp.ui.views.PurchaseView;

/**
 * Created by melolchik on 26.04.2017.
 */
public class PurchaseListAdapter extends BaseListAdapter<Purchase> {

    /**
     * Instantiates a new Purchase list adapter.
     *
     * @param recyclerView the recycler view
     */
    public PurchaseListAdapter(RecyclerView recyclerView){
        super(recyclerView);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PurchaseView view = new PurchaseView(parent.getContext());
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new PurchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof PurchaseViewHolder){
            ((PurchaseViewHolder) holder).setData(getItem(position));
        }
    }

    /**
     * The type Purchase view holder.
     */
    public static class PurchaseViewHolder extends RecyclerView.ViewHolder {
        /**
         * The M purchase view.
         */
        protected final PurchaseView mPurchaseView;

        /**
         * Instantiates a new Purchase view holder.
         *
         * @param view the view
         */
        PurchaseViewHolder(PurchaseView view) {
            super(view);
            mPurchaseView = view;
        }

        /**
         * Sets data.
         *
         * @param purchase the purchase
         */
        public void setData(Purchase purchase) {
            mPurchaseView.bind(purchase);
        }
    }
}
