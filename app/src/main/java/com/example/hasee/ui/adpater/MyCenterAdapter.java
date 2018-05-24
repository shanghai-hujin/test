package com.example.hasee.ui.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.R;
import com.example.hasee.bean.DataActivityBean;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/21 18:02
 */

public class MyCenterAdapter extends BaseQuickAdapter<DataActivityBean,BaseViewHolder> {

    private Context mContext;

    public MyCenterAdapter(@Nullable List<DataActivityBean> data, Context context) {
        super(R.layout.item_mydata, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, DataActivityBean dataActivityBean) {
        baseViewHolder.setText(R.id.tv_activity_title, dataActivityBean.getActivityName());
    }
}
