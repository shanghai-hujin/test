package com.example.hasee.ui.movie;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.hasee.R;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.ui.adpater.MovieHorizontalAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.utils.BitmapUtil;
import com.example.hasee.widget.NoScrollViewPager;
import com.example.hasee.widget.ScalableCardHelper;
import com.flyco.tablayout.SlidingTabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by HASEE on 2018/4/29.
 */

public class MovieFragment extends BaseFragment<MoviePresenter> implements
        MovieContract.MovieView, ScalableCardHelper.OnPageChangeListener {

    @BindView(R.id.banner_movie)
    RecyclerView mBannerMovie;
    @BindView(R.id.toolbar_movie)
    Toolbar mToolbarMovie;
    @BindView(R.id.ctl_movie)
    CollapsingToolbarLayout mCtlMovie;
    @BindView(R.id.stl_movie)
    SlidingTabLayout mStlMovie;
    @BindView(R.id.vp_movie)
    NoScrollViewPager mVpMovie;
    @BindView(R.id.image_switcher)
    ImageSwitcher mImageSwitcher;

    private List<String> mBannerUrlList = new ArrayList<>();
    private List<String> mBannerTitleList = new ArrayList<>();
    private List<MovieDataBean.SubjectsBean> itemBeanList = new ArrayList<>();
    private MovieHorizontalAdapter mMovieHorizontalAdapter;
    private int mCurrentItemOffset;

    public static MovieFragment newInstance(String param1) {
        Bundle args = new Bundle();
        MovieFragment newsFragment = new MovieFragment();
        args.putString("param1", param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mCtlMovie.setExpandedTitleColor(Color.parseColor("#00ffffff"));//设置还没收缩时状态下字体颜色

        Animation fadeIn = new AlphaAnimation(0.5f, 1);
        fadeIn.setDuration(500);
        mImageSwitcher.setInAnimation(fadeIn);
        Animation fadeOut = new AlphaAnimation(0.5f, 0);
        fadeOut.setDuration(500);
        mImageSwitcher.setOutAnimation(fadeOut);

        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBannerMovie.setLayoutManager(linearLayoutManager);

        new LinearSnapHelper().attachToRecyclerView(mBannerMovie);
        mMovieHorizontalAdapter = new MovieHorizontalAdapter(itemBeanList, getContext());
        mBannerMovie.setAdapter(mMovieHorizontalAdapter);

        ScalableCardHelper cardHelper = new ScalableCardHelper(this);
        cardHelper.attachToRecyclerView(mBannerMovie);

    }


    @Override
    public void initData() {
        mPresenter.getMovieWeekly();
    }

    @Override
    public void loadData(List<MovieDataBean.SubjectsBean> itemBeanList) {
        if(itemBeanList.size() > 0){


        }
    }

    @Override
    public void loadMovieError(String err) {

    }

    @Override
    public void loadBannerData(List<MovieDataBean.SubjectsBean> itemBeanList) {
        if(itemBeanList.size() == 0){
            return;
        }
      //  mMovieHorizontalAdapter.addData(itemBeanList);
        itemBeanList.clear();
        itemBeanList.addAll(itemBeanList);
        mMovieHorizontalAdapter.notifyDataSetChanged();

    }

    @Override
    public void onPageSelected(int position) {

        Bitmap bitmap = null;
        try {
            bitmap = BitmapUtil.blurBitmap(BitmapUtil.getNetBitmap(itemBeanList.get(position).getImages().getMedium()),
                    25 , getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mImageSwitcher.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
    }
}
