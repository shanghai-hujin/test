package com.example.hasee.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.di.component.ApplicationComponent;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.main.MainActivity;
import com.example.hasee.utils.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author TT
 */
public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.sdv_welcome)
    SimpleDraweeView mSdvWelcome;
    @BindView(R.id.tv_welcome_flash)
    TextView mTvWelcomeFlash;
    private Disposable mDisposable;

    @Override
    public int getContentLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
       // hideBottomUIMenu();
      //  StatusBarUtil.setTranslucentForImageView(this, 0, mSdvWelcome);
        FrescoUtils.setController("https://api.lylares.com/bing/image/?1080/1920/0", mSdvWelcome ,1);

        mTvWelcomeFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WelcomeActivity.this.isFinishing()){
                    return;
                }
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        //回调在主线程
        mDisposable = Flowable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mTvWelcomeFlash.setText("直接跳过：" + (5-aLong));
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if(WelcomeActivity.this.isFinishing()){
                            return;
                        }
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }
                })
                .subscribe();
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {

    }

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
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }

    }
}
