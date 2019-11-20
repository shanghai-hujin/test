package com.example.hasee.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.hasee.R;
import com.example.hasee.common.base.ui.BaseActivity;
import com.example.hasee.http.ComPath;
import com.example.hasee.ui.main.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author TT
 */
@Route( path = ComPath.PATH_WELCOMEACTIVITY)
public class WelcomeActivity extends BaseActivity {


    private ImageView mSdvWelcome;
    private TextView mTvWelcomeFlash;
    private Disposable mDisposable;


    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mSdvWelcome= (ImageView) find(R.id.sdv_welcome);
        mTvWelcomeFlash= (TextView) find(R.id.tv_welcome_flash);
        // hideBottomUIMenu();
      //  StatusBarUtil.setTranslucentForImageView(this, 0, mSdvWelcome);

        Glide.with(this)
                .load("https://i.meizitu.net/2019/04/26c03.jpg")
                .into(mSdvWelcome);


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


        initData();
        initSp();

    }

    private void initSp() {
        EventBus.getDefault().post(new WelEvent());
    }



    public void initData() {
        //回调在主线程
        if(MyApplication.isIsLeng()){
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
        }else {
            if(WelcomeActivity.this.isFinishing()){
                return;
            }
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }


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



    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }
}
