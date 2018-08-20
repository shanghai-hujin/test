package com.example.hasee.ui.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.bean.NewsArticleBean;
import com.example.hasee.bean.NewsDetail;
import com.example.hasee.di.component.ApplicationComponent;
import com.example.hasee.di.component.DaggerHttpComponent;
import com.example.hasee.http.Common;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.utils.StatusBarUtil;
import com.example.hasee.widget.swipeback.HackyViewPager;
import com.example.hasee.widget.swipeback.HalfScrollView;
import com.example.hasee.widget.swipeback.SwipeBackLayout;
import com.example.hasee.widget.zoom.DoubleTapGestureListener;
import com.example.hasee.widget.zoom.ZoomableDraweeView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/31 14:05
 */

public class ImageBrowseActivity extends BaseActivity<
        ReadContentsPresenter> implements ReadContentsContract.ReadContentsView {
    private static final String AID = "aid";
    private static final String ISCMPP = "isCmpp";
    @BindView(R.id.hvp_layout)
    HackyViewPager mHvpLayout;
    @BindView(R.id.sbl_layout)
    SwipeBackLayout mSblLayout;
    @BindView(R.id.v_blacktop)
    RelativeLayout mVBlacktop;
    @BindView(R.id.tv_imgtitle)
    TextView mTvImgtitle;
    @BindView(R.id.ib_finish)
    ImageView mIbFinish;
    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.hv_info)
    HalfScrollView mHvInfo;
    @BindView(R.id.cl_img)
    RelativeLayout mClImg;
    private boolean isShow = true;

    private NewsArticleBean newsArticleBean;
    @Override
    public int getContentLayout() {
        return R.layout.activity_imagebrowse;
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        //hideBottomUIMenu();
      //  StatusBarUtil.statusBarHide(this);
        StatusBarUtil.setWindowStatusBarColor(this,android.R.color.black);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, android.R.color.black));
        mSwipeBackHelper.setSwipeBackEnable(true);
        mClImg.getBackground().setAlpha(255);
        mSblLayout.setDragDirectMode(SwipeBackLayout.DragDirectMode.VERTICAL);
        mSblLayout.setOnSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
                mClImg.getBackground().setAlpha((255-(int) Math.ceil(255 * fractionAnchor)));
                DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
                df.setMaximumFractionDigits(1);
                df.setRoundingMode(RoundingMode.HALF_UP);
                //设置小数的四舍五入
                String dd = df.format(fractionAnchor);
                double alpha = 1 - (Float.valueOf(dd) + 0.8);
                if (fractionAnchor == 0 && isShow) {
                    mHvInfo.setAlpha(1f);
                    mVBlacktop.setAlpha(1f);
                    mVBlacktop.setVisibility(View.VISIBLE);
                    mHvInfo.setVisibility(View.VISIBLE);
                } else {
                    if (alpha == 0) {
                        mVBlacktop.setVisibility(View.GONE);
                        mHvInfo.setVisibility(View.GONE);
                        mHvInfo.setAlpha(1f);
                        mVBlacktop.setAlpha(1f);
                    } else {
                        if (mVBlacktop.getVisibility() != View.GONE) {
                            mVBlacktop.setAlpha((float) alpha);
                            mHvInfo.setAlpha((float) alpha);
                        }
                    }
                }

            }
        });

        mHvInfo.getBackground().mutate().setAlpha(100);
        mVBlacktop.getBackground().mutate().setAlpha(100);
        mHvpLayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTvInfo.setText((position + 1) + " / " + newsArticleBean.getBody().getSlides().size() + " " + newsArticleBean.getBody().getSlides().get(position).getDescription());
                if (position == 0) {
                    mSwipeBackHelper.setSwipeBackEnable(true);
                } else {
                    mSwipeBackHelper.setSwipeBackEnable(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        if (getIntent().getExtras() == null) {
            return;
        }
        String aid = getIntent().getStringExtra(AID);
        boolean isCmpp = getIntent().getBooleanExtra(ISCMPP, false);
        basePresenter.getData(aid);
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
                .inject(this);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    public static void launch(Activity activity, NewsDetail.ItemBean itemBean) {
        Intent intent = new Intent(activity, ImageBrowseActivity.class);
        if (itemBean.getId().contains(Common.GetNewsArticleCmppApi) || itemBean.getDocumentId().startsWith("cmpp")) {
            intent.putExtra(ISCMPP, true);
        } else {
            intent.putExtra(ISCMPP, false);
        }
        intent.putExtra(AID, itemBean.getDocumentId());
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onRetry() {
        super.onRetry();
        initData();
    }

    @OnClick(R.id.ib_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void loadWebData(NewsArticleBean newsArticleBean) {
        try {
            this.newsArticleBean = newsArticleBean;
            mTvInfo.setText("1 / " + newsArticleBean.getBody().getSlides().size() + " " + newsArticleBean.getBody().getSlides().get(0).getDescription());
            mHvpLayout.setAdapter(new ViewPagerAdapter(newsArticleBean.getBody().getSlides()));
            mTvImgtitle.setText(newsArticleBean.getBody().getTitle());
            //showSuccess();
        } catch (Exception e) {
            // showFaild();
            e.printStackTrace();
        }
    }

    private class ViewPagerAdapter extends PagerAdapter{


        private List<NewsArticleBean.BodyBean.SlidesBean> mSlidesBeans;
        private ZoomableDraweeView mPhotoView;
        private ProgressBar mProgressBar;

        public ViewPagerAdapter(List<NewsArticleBean.BodyBean.SlidesBean> slidesBeanList) {
            this.mSlidesBeans = slidesBeanList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ImageBrowseActivity.this).inflate(R.layout.pager_photo, null);
            mPhotoView = (ZoomableDraweeView) view.findViewById(R.id.photoview);
            mProgressBar = (ProgressBar) view
                    .findViewById(R.id.loading);


            mProgressBar.setVisibility(View.GONE);

            //允许缩放时切换
            mPhotoView.setAllowTouchInterceptionWhileZoomed(true);
            //长按
            mPhotoView.setIsLongpressEnabled(false);

            //双击击放大或缩小
            mPhotoView.setTapListener(new DoubleTapGestureListener(mPhotoView));

            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setUri(mSlidesBeans.get(position).getImage())
                    .build();
            //加载图片
            mPhotoView.setController(draweeController);
            container.addView(view);
            view.requestLayout();

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public int getCount() {
            return mSlidesBeans != null? mSlidesBeans.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
