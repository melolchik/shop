package com.melolchik.shopapp.ui.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.melolchik.shopapp.R;
import com.melolchik.shopapp.ui.adapters.LeftMenuAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by melolchik on 26.01.2017.
 */
public class LeftSideMenuView extends FrameLayout {

    /**
     * The M left menu email.
     */
    @BindView(R.id.left_menu_email)
    TextView mLeftMenuEmail;

    /**
     * The M recycler view.
     */
    protected
    @BindView(R.id.left_menu_recycler_view)
    RecyclerView mRecyclerView;


    /**
     * The interface On close click listener.
     */
    public interface OnExitClickListener {
        /**
         * On close click.
         */
        void onExitClick();
    }


    /**
     * The M adapter.
     */
    protected LeftMenuAdapter mAdapter;

    /**
     * The M on close click listener.
     */
    protected OnExitClickListener mOnCloseClickListener;

    /**
     * Instantiates a new Left side menu view.
     *
     * @param context the context
     */
    public LeftSideMenuView(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Left side menu view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public LeftSideMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new Left side menu view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public LeftSideMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_left_side_menu, this);
        ButterKnife.bind(this, this);

        if (mRecyclerView != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mAdapter = new LeftMenuAdapter(mRecyclerView);
            mRecyclerView.setAdapter(mAdapter);
            // mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        }


    }

    /**
     * Sets on item click listener.
     *
     * @param onItemClickListener the on item click listener
     */
    public void setOnItemClickListener(LeftMenuAdapter.OnItemClickListener onItemClickListener) {
        mAdapter.setOnItemClickListener(onItemClickListener);
    }

    /**
     * Sets on close click listener.
     *
     * @param onCloseClickListener the on close click listener
     */
    public void setOnExitClickListener(OnExitClickListener onCloseClickListener) {
        mOnCloseClickListener = onCloseClickListener;
    }



    /**
     * Set email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        mLeftMenuEmail.setText(email);
    }

    /**
     * On click close.
     */
    @OnClick(R.id.txt_exit)
    void onClickClose() {
        if (mOnCloseClickListener != null) {
            mOnCloseClickListener.onExitClick();
        }
    }
}
