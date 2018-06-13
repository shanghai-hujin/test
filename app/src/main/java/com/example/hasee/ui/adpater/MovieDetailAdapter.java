package com.example.hasee.ui.adpater;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.R;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.utils.ContextUtils;
import com.example.hasee.utils.FrescoUtils;
import com.example.hasee.utils.PerfectClickListener;
import com.example.hasee.widget.ZhiHuImageView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by TT on 2017/9/21.
 */
public class MovieDetailAdapter extends BaseQuickAdapter<MovieDataBean.SubjectsBean, BaseViewHolder> {

    private Context mContext;
    private SimpleDraweeView simpleDraweeView;
    private ZhiHuImageView zhiHuImageView;

    public MovieDetailAdapter(Context context, List<MovieDataBean.SubjectsBean> data) {
        super(R.layout.item_movie_detail, data);
        this.mContext = context ;
    }


    @Override
    protected void convert(BaseViewHolder helper, final MovieDataBean.SubjectsBean data) {

        int position = helper.getPosition();

        LinearLayout llItem = helper.getView(R.id.ll_item);
        ZhiHuImageView zhiHuImageView = helper.getView(R.id.zh_img);
        if (position > 0 && position % 4 == 0) {

            llItem.setVisibility(View.GONE);
            zhiHuImageView.setVisibility(View.VISIBLE);
            FrescoUtils.setController(data.getImages().getLarge(), zhiHuImageView);
        } else {
            llItem.setVisibility(View.VISIBLE);
            zhiHuImageView.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_one_title, data.getTitle())
                .setText(R.id.tv_one_directors, ContextUtils.formatName(data.getDirectors()))
                .setText(R.id.tv_one_casts, ContextUtils.formatCastsName(data.getCasts()))
                .setText(R.id.tv_one_genres, ContextUtils.formatGenres(data.getGenres()))
                .setText(R.id.tv_one_rating_rate,"评分"+data.getRating().getAverage());
        simpleDraweeView = helper.getView(R.id.iv_one_photo);
        FrescoUtils.setController(data.getImages().getLarge(), simpleDraweeView);

        helper.itemView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {


            }
        });

    }


}
