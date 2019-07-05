package com.example.hasee.news.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.hasee.common.base.mvp.XDaggerFragment;
import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.news.R;
import com.example.hasee.news.di.NewsDiHelper;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

@Route(path = "/home/NewsFragment")
public class NewsFragment extends XDaggerFragment<NewsPresenter> implements INewsContract.IView{

    private Banner mBannerHomeNews;

    @Override
    public void initViewF(Bundle savedInstanceState) {
        mBannerHomeNews = (Banner) find(R.id.banner_news);


        mPresenter.getWanHomeBanner();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_news;
    }

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initInject(Bundle savedInstanceState) {
        NewsDiHelper.getFragmentComponent(getFragmentModule()).inject(this);
    }

    @Override
    public void showBannerData(List<WanHomeBannerResponse> response) {
        mBannerHomeNews.setImages(response).setImageLoader(new ImageLoader() {
//            @Override
//            public ImageView createImageView(Context context) {
//                ImageView imageView = new ImageView(context);
//
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                imageView.setAdjustViewBounds(true);
//                return imageView;
//            }

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(_mActivity).load(((WanHomeBannerResponse) path).getImagePath()).into(imageView);
            }
        }).isAutoPlay(true).start();

    }
}
