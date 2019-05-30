package com.example.hasee.ui.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hasee.R;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.utils.GlideApp;
import com.example.hasee.widget.recyclebanner.BannerAdapterHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/7 11:27
 */

public class MovieHorizontalAdapter extends RecyclerView.Adapter<MovieHorizontalAdapter.ViewHolder> {
    private  List<MovieDataBean.SubjectsBean> subjectsBeanList = new ArrayList<>();

    public void setSubjectsBeanList(List<MovieDataBean.SubjectsBean> subjectsBeanList) {
        this.subjectsBeanList = subjectsBeanList;
    }

    private BannerAdapterHelper mBannerAdapterHelper = new BannerAdapterHelper();
    private Context mContext;

    public MovieHorizontalAdapter(Context context, List<MovieDataBean.SubjectsBean> subjectsBeanList) {
        this.mContext = context;
        this.subjectsBeanList = subjectsBeanList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_h_movie, parent, false);
        mBannerAdapterHelper.onCreateViewHolder(parent, itemView);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mBannerAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        ImageView imageView = holder.mImageView;
        if(subjectsBeanList.size() == 0)return;
        GlideApp.with(mContext)
                .load(subjectsBeanList.get(position%subjectsBeanList.size()).getImages().getMedium())
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.sd_movie_banner);
        }

    }
}
