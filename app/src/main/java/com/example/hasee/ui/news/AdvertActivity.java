package com.example.hasee.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc: .
 * author: Will .
 * date: 2017/9/24 .
 */
public class AdvertActivity extends BaseActivity {

    private static final String Url = "url";

    @BindView(R.id.pb_progress)
    ProgressBar mPbProgress;
    @BindView(R.id.webview_advert)
    WebView mWebviewAdvert;

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, AdvertActivity.class);
        intent.putExtra(Url, url);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_advert;
    }



    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        getSetting(mWebviewAdvert);
        mWebviewAdvert.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        mWebviewAdvert.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (mPbProgress == null) {
                    return;
                }
                if (newProgress == 100) {
                    mPbProgress.setVisibility(View.GONE);
                } else {
                    mPbProgress.setVisibility(View.VISIBLE);
                    mPbProgress.setProgress(newProgress);
                }
            }
        });
    }

    @Override
    public void initData() {
        if (getIntent() == null) {
            return;
        }
        String url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            mWebviewAdvert.loadUrl(url);
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public BaseContract.BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onRetry() {

    }

    private void getSetting(WebView webview) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.getSettings().setUseWideViewPort(false);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webview.getSettings().setAllowFileAccessFromFileURLs(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//LOAD_CACHE_ELSE_NETWORK模式下，无论是否有网络，只要本地有缓存，都使用缓存。本地没有缓存时才从网络上获取
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
