package com.example.hasee.ui.adpater;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Demo ${CLASS}
 *
 * @author hj
 * @date 2018/8/27 17:32
 */
public class MainViewHolder extends RecyclerView.ViewHolder {

    public MainViewHolder(ViewGroup parent, @LayoutRes int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    /**
     * 获取布局中的View
     * @param viewId view的Id
     * @param <T> View的类型
     * @return view
     */
    public <T extends View>T getView(@IdRes int viewId){
        return (T) (itemView.findViewById(viewId));
    }

    /**
     * 获取Context实例
     * @return context
     */
    public Context getContext() {
        return itemView.getContext();
    }
}
