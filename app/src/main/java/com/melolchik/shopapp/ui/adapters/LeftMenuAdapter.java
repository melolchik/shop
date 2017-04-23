package com.melolchik.shopapp.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melolchik.shopapp.R;
import com.melolchik.shopapp.components.enums.LeftMenuItem;


/**
 * Created by Olga Melekhina on 08.06.2016.
 */
public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.MenuItemViewHolder> {

    public static interface OnItemClickListener{
        void onItemClick(LeftMenuItem item);
    }

    /**
     * The M recycler view.
     */
    protected final RecyclerView mRecyclerView;
    /**
     * The M context.
     */
    protected final Context mContext;
    /**
     * The M list click listener.
     */
    protected LayoutInflater inflater;

    protected OnItemClickListener mOnItemClickListener;

    /**
     * Instantiates a new Business list adapter.
     *
     * @param recyclerView the recycler view
     */
    public LeftMenuAdapter(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setClickable(true);
        mContext = mRecyclerView.getContext();
        inflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return mContext;
    }


    public LeftMenuItem getItem(int position) {
        return LeftMenuItem.values()[position];
    }

    @Override
    public int getItemCount() {
        return LeftMenuItem.values().length;
    }


    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_left_side_menu,parent,false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {

        holder.setData(getItem(position));
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder{

        protected TextView mTextView;
        public MenuItemViewHolder(View root){
            super(root);
            mTextView = (TextView) root.findViewById(R.id.item_title);
        }

        public void setData(LeftMenuItem leftMenuItem){


            mTextView.setText(leftMenuItem.getTitleRes());
            Drawable drawable = ContextCompat.getDrawable(mTextView.getContext(),leftMenuItem.getIconRes());
            mTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable,null,null,null);

            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = mRecyclerView.getChildLayoutPosition(itemView);
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(LeftMenuItem.getByCode(position));
                    }
                }
            });
        }

    }

}
