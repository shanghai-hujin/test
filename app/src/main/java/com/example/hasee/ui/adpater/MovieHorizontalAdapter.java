package com.example.hasee.ui.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.R;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.utils.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/7 11:27
 */

public class MovieHorizontalAdapter extends BaseQuickAdapter<MovieDataBean.SubjectsBean, BaseViewHolder> {

    private Context mContext;

    public MovieHorizontalAdapter(@Nullable List<MovieDataBean.SubjectsBean> data, Context context) {
        super(R.layout.item_h_movie, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MovieDataBean.SubjectsBean item) {
        SimpleDraweeView movieImg = (SimpleDraweeView) baseViewHolder.getView(R.id.sd_movie_banner);
        FrescoUtils.setController(item.getImages().getMedium(), movieImg);
    }
}
