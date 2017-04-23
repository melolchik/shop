package com.melolchik.common.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.melolchik.common.util.AppLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Olga Melekhina on 08.06.2016.
 */
public abstract class BaseListAdapter<ItemType> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * The M recycler view.
     */
    protected RecyclerView mRecyclerView;
    /**
     * The M context.
     */
    protected final Context mContext;
    /**
     * The M business list.
     */
    protected List<ItemType> mList;

    /**
     * The M list click listener.
     */
    protected LayoutInflater inflater;

    /**
     * Instantiates a new Business list adapter.
     *
     * @param recyclerView the recycler view
     */
    public BaseListAdapter(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setClickable(true);
        mContext = mRecyclerView.getContext();
        mList = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return mContext;
    }

    public int setData(List<ItemType> list) {
        setList(list);
        notifyDataSetChanged();
       return mList.size() - 1;
    }

    public List<ItemType> getData(){
        return mList == null ? Collections.<ItemType>emptyList() : mList;
    }

    public void addData(List<ItemType> list) {
        addList(list);
        notifyDataSetChanged();
        //mRecyclerView.invalidate();
    }


    protected void addList(List<ItemType> list) {
        if(mList == null || mList.isEmpty()){
           setList(list);
        }else if (list != null && !list.isEmpty()) {
            mList.addAll(list);
        }
    }

    protected void setList(List<ItemType> list) {
        if (list == null) {
            mList = new ArrayList<>();
        } else {
            mList = new ArrayList<>(list);
        }
    }

    public int getItemPosition(ItemType itemType){
        if(itemType == null) return -1;
        if (mList == null || mList.isEmpty())
            return -1;
        for(int i = mList.size() - 1;  i>=0; i--){
            ItemType item = mList.get(i);
            if(item != null && item.equals(itemType))
                return i;
        }
        return -1;
    }


    public ItemType getItem(int position) {
        if (mList == null || mList.isEmpty())
            return null;
        if(position >= 0 && position < mList.size()) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * Log.
     *
     * @param message the message
     */
    protected void log(String message) {
        AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }
}
