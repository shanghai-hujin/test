package com.example.hasee.ui.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.R;

import java.nio.channels.Channel;
import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/21 18:02
 */

public class MyCenterAdapter extends BaseQuickAdapter<Channel,BaseViewHolder> {
    public MyCenterAdapter(@Nullable List<Channel> data, Context context) {
        super(R.layout.layout_dialog, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Channel item) {

    }
}
