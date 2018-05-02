package com.example.hasee.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.ui.book.BookFragment;
import com.example.hasee.ui.movie.MovieFragment;
import com.example.hasee.ui.news.NewsFragment;
import com.example.hasee.ui.person.LoginActivity;
import com.example.hasee.ui.setting.SettingFragment;
import com.example.hasee.utils.StatusBarUtil;
import com.example.hasee.widget.BottomBar;

import butterknife.BindView;

/**
 * @author TT
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.contentContainer)
    FrameLayout contentContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    private BaseFragment[] baseFragments = new BaseFragment[4];
    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    /**
     * 加载布局
     * view 返回的布局view
     */
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this,0, null);
        if(savedInstanceState == null) {
            //contentContainer.setBackgroundColor(Color.RED);

            new CountDownTimer(1000,3000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            }.start();

            baseFragments[0] = NewsFragment.newInstance("0");
            baseFragments[1] = BookFragment.newInstance("1");
            baseFragments[2] = MovieFragment.newInstance("2");
            baseFragments[3] = SettingFragment.newInstance("3");
        }
    }

    @Override
    /**
     * 请求数据
     */
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
