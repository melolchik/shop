package com.melolchik.shopapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.melolchik.common.ui.adapters.BaseListAdapter;
import com.melolchik.shopapp.R;
import com.melolchik.shopapp.ui.views.OnKeyboardListener;

/**
 * Created by melolchik on 25.04.2017.
 */
public class KeyboardGridAdapter extends BaseListAdapter<Character> {

    /**
     * The M keyboard listener.
     */
    protected OnKeyboardListener mKeyboardListener;

    /**
     * Instantiates a new Keyboard grid adapter.
     *
     * @param recyclerView the recycler view
     */
    public KeyboardGridAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_keyboard_view, parent, false);
        return new KeyboardItemHolder(view);
    }

    /**
     * Sets keyboard listener.
     *
     * @param keyboardListener the keyboard listener
     */
    public void setKeyboardListener(OnKeyboardListener keyboardListener) {
        mKeyboardListener = keyboardListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof KeyboardItemHolder) {
            ((KeyboardItemHolder) holder).setData(getItem(position),mKeyboardListener);
        }
    }

    /**
     * The type Keyboard item holder.
     */
    public static class KeyboardItemHolder extends RecyclerView.ViewHolder {

        /**
         * The M button.
         */
        Button mButton;

        /**
         * Instantiates a new Keyboard item holder.
         *
         * @param view the view
         */
        KeyboardItemHolder(View view) {
            super(view);
            mButton = (Button) view.findViewById(R.id.item_button);
        }

        /**
         * Sets data.
         *
         * @param character the character
         * @param listener  the listener
         */
        void setData(final Character character,final OnKeyboardListener listener) {
            mButton.setText(character.toString());
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        if(character == 'x'){
                            listener.onClear();
                        }else {
                            listener.onChar(character);
                        }
                    }
                }
            });
        }
    }
}
