package com.example.hasee.ui.adpater;

import android.content.Context;
import android.view.ViewGroup;

import com.example.hasee.widget.vlayout.DelegateAdapter;
import com.example.hasee.widget.vlayout.LayoutHelper;
import com.example.hasee.widget.vlayout.VirtualLayoutManager;

/**
 * Demo ${CLASS}
 *
 * @author 廖望
 * @date 2018/8/27 17:31
 */
public  class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {


    private Context mContext;

    private LayoutHelper mLayoutHelper;


    private VirtualLayoutManager.LayoutParams mLayoutParams;

    private int mCount = 0;

    private int mResId ;

    public SubAdapter(Context context, LayoutHelper layoutHelper,
                      int count, int resId) {
        mContext = context;
        mLayoutHelper = layoutHelper;
        mCount = count;
        mResId = resId;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(parent, mResId);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return mCount;
    }
}
