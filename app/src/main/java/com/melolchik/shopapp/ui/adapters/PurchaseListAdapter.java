package com.melolchik.shopapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.melolchik.common.ui.adapters.BaseListAdapter;
import com.melolchik.shopapp.dao.Purchase;
import com.melolchik.shopapp.ui.views.ProductView;
import com.melolchik.shopapp.ui.views.PurchaseView;

/**
 * Created by melolchik on 26.04.2017.
 */

public class PurchaseListAdapter extends BaseListAdapter<Purchase> {

    public PurchaseListAdapter(RecyclerView recyclerView){
        super(recyclerView);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PurchaseViewHolder(new PurchaseView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof PurchaseViewHolder){
            ((PurchaseViewHolder) holder).setData(getItem(position));
        }
    }

    public static class PurchaseViewHolder extends RecyclerView.ViewHolder {
        protected final PurchaseView mPurchaseView;

        PurchaseViewHolder(PurchaseView view) {
            super(view);
            mPurchaseView = view;
        }

        public void setData(Purchase purchase) {
            mPurchaseView.bind(purchase);
        }
    }
}
