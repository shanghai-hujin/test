package com.example.hasee.ui.drawer.chat.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hasee.R;


/**
 * Created by ZengZeHong on 2017/5/15.
 */

public class ToastHolder extends RecyclerView.ViewHolder{
    private TextView tvToast;
    public ToastHolder(View itemView) {
        super(itemView);
        tvToast = (TextView)itemView.findViewById(R.id.tv_toast);
    }

    public TextView getTvToast() {
        return tvToast;
    }

    public void setTvToast(TextView tvToast) {
        this.tvToast = tvToast;
    }
}