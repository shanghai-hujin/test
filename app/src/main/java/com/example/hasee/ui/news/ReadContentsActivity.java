package com.example.hasee.ui.news;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.bean.NewsArticleBean;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.utils.DateUtil;
import com.example.hasee.utils.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

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
    @BindView(R.id.sd_offlogo)
    SimpleDraweeView mSdOfflogo;
    @BindView(R.id.tv_offname)
    TextView mTvOffname;
    @BindView(R.id.tv_offupdateTime)
    TextView mTvOffupdateTime;

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
        initWebViewSetting();

    }

    private void initWebViewSetting() {
        WebSettings settings = mWvRead.getSettings();
        //设置js
        settings.setJavaScriptEnabled(true);
        mWvRead.addJavascriptInterface(new MyJavaScriptInterface(), "jsObj");
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
       /* //打开页面自动适应
        settings.setLoadWithOverviewMode(true);*/
        //设置是否可以支持缩放
        settings.setSupportZoom(false);
        //扩大比例的缩放
        settings.setUseWideViewPort(false);
        //为25%，最小缩放等级
        //mWvRead.setInitialScale(25);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(false);
        //是否允许通过FileURLs访问文件
        settings.setAllowFileAccessFromFileURLs(true);
        //设置竖直滚动条可用
        mWvRead.setVerticalScrollBarEnabled(false);
        //设置竖直滚动条过度划出效果
        mWvRead.setVerticalScrollbarOverlay(false);
        //设置水平滚动条可用
        mWvRead.setHorizontalScrollBarEnabled(false);
        //设置竖直滚动条过度划出效果
        mWvRead.setHorizontalScrollbarOverlay(false);
        //设置js能否打开一个新的窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置cache
        settings.setAppCacheEnabled(true);
        //设置cache模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置Dom存储是否可用
        settings.setDomStorageEnabled(true);
        //设置默认的文本编码名称
        //settings.setDefaultTextEncodingName("UTF-8");

        mWvRead.loadUrl("file:///android_asset/ifeng/post_detail.html");
        mWvRead.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String aid = getIntent().getStringExtra("aid");
                basePresenter.getData(aid);
            }
        });

    }

    @Override
    public void loadWebData(NewsArticleBean newsArticleBean) {
        if (newsArticleBean == null) {
            return;
        }

        mCtlRead.setTitle(newsArticleBean.getBody().getTitle());
        mTvOffupdateTime.setText(DateUtil.getTimestampString(
                DateUtil.string2Date(newsArticleBean.getBody().getUpdateTime(),"yyyy/MM/dd HH:mm:ss")));

        if(newsArticleBean.getBody().getSubscribe() != null){
            FrescoUtils.setController(newsArticleBean.getBody().getSubscribe().getLogo(),mSdOfflogo);
            mTvOffname.setText(newsArticleBean.getBody().getSubscribe().getCateSource());

        }else {
            mTvOffname.setText(newsArticleBean.getBody().getSource());
        }

        mWvRead.post(new Runnable() {
            @Override
            public void run() {
                String content = newsArticleBean.getBody().getText();
                String url = "javascript:show_content(\'" + content + "\')";
              //  mWvRead.loadUrl("https://blog.csdn.net/zhangcanyan/article/details/51930775");
                mWvRead.loadUrl(url);
            }
        });
    }



    class MyJavaScriptInterface {

        @JavascriptInterface
        public void jsFunctionimg(final String i) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("TAG", "run: " + i);
                }
            });

        }
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
