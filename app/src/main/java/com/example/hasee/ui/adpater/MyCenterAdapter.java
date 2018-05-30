package com.example.hasee.ui.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;

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

public class MyCenterAdapter extends BaseQuickAdapter<DataActivityBean, BaseViewHolder> {

    private Context mContext;

    public MyCenterAdapter(@Nullable List<DataActivityBean> data, Context context) {
        super(R.layout.item_mydata, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, DataActivityBean dataActivityBean) {
        baseViewHolder.setText(R.id.tv_data_title, dataActivityBean.getActivityName())
                .setText(R.id.tv_starttime, dataActivityBean.getActivityDateStart())
                .setText(R.id.tv_endtime, dataActivityBean.getActivityDateEnd())
                .setText(R.id.tv_data_people, dataActivityBean.getActivityPeople())
                .setText(R.id.tv_data_things, dataActivityBean.getActivityThings());

        switch (dataActivityBean.getActivityLevel()) {
            case 0:
                //红色事件
                ((CardView) baseViewHolder.getView(R.id.cv_data)).setCardBackgroundColor(mContext.getResources().getColor(R.color.tomato));
                break;
            case 1:
                //绿色事件
                ((CardView) baseViewHolder.getView(R.id.cv_data)).setCardBackgroundColor(mContext.getResources().getColor(R.color.mediumseagreen));
                break;
            case 2:
                //蓝色事件
                ((CardView) baseViewHolder.getView(R.id.cv_data)).setCardBackgroundColor(mContext.getResources().getColor(R.color.lightskyblue));
                break;
            default:
                break;
        }

        switch (dataActivityBean.getActivityType()) {
            case 0:
                baseViewHolder.setText(R.id.tv_activity_type,"日程");
                break;
            case 1:
                baseViewHolder.setText(R.id.tv_activity_type,"纪念");
                break;
            case 2:
                baseViewHolder.setText(R.id.tv_activity_type,"生日");
                break;
            case 3:
                baseViewHolder.setText(R.id.tv_activity_type,"倒数");
                break;
            default:
                break;
        }




    }
}
