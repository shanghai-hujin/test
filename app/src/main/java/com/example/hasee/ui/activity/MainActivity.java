package com.example.hasee.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BasePresenter;
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
     * 加载数据
     * view 返回的布局view
     */
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this,0, null);
        if(savedInstanceState == null) {
            contentContainer.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
