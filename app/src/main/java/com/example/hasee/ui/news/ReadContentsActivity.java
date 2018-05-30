package com.example.hasee.ui.news;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ReadContentsActivity extends BaseActivity<ReadContentsPresenter> implements ReadContentsContract.ReadContentsView {


    @BindView(R.id.toolbar_read)
    Toolbar mToolbarRead;
    @BindView(R.id.ctl_read)
    CollapsingToolbarLayout mCtlRead;
    @BindView(R.id.abl_read)
    AppBarLayout mAblRead;
    @BindView(R.id.wv_read)
    WebView mWvRead;
    @BindView(R.id.cd_read)
    CardView mCdRead;
    @BindView(R.id.fab_read)
    FloatingActionButton mFabRead;

    private int mMaxScrollSize;
    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private boolean mIsImageHidden;

    @Override
    public int getContentLayout() {
        return R.layout.activity_read_contents;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

        initToolbar();


    }

    private void initToolbar() {
        mToolbarRead.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAblRead.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mMaxScrollSize == 0) {
                    mMaxScrollSize = appBarLayout.getTotalScrollRange();
                }

                int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                        / mMaxScrollSize;

                if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
                    if (!mIsImageHidden) {
                        mIsImageHidden = true;

                        ViewCompat.animate(mFabRead).scaleY(0).scaleX(0).start();
                    }
                }

                if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
                    if (mIsImageHidden) {
                        mIsImageHidden = false;
                        ViewCompat.animate(mFabRead).scaleY(1).scaleX(1).start();
                    }
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public ReadContentsPresenter createPresenter() {
        return new ReadContentsPresenter();
    }



    @OnClick({R.id.cd_read, R.id.fab_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cd_read:
                break;
            case R.id.fab_read:
                break;
            default:
                break;
        }
    }
}
