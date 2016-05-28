package com.snail.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by hup on 2016/5/28.
 */
public class SwipeListView extends ListView {

    private static final String TAG = "SwipeListView";

    private SwipeListViewAdapter mAdapter;

    public SwipeListView(Context context) {
        this(context, null);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSwipeListViewAdapter(SwipeListViewAdapter adapter) {
        this.mAdapter = adapter;
        super.setAdapter(adapter);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.i(TAG, "SwipeListView---->onScrollChanged");
        //先关闭打开的SwipeLayout
        if (mAdapter != null) {
            if (mAdapter.hasOpenSwipeView()) {
                mAdapter.closeAllItems();
            }
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

}
