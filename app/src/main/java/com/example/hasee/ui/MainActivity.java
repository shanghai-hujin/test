package com.example.hasee.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.ui.book.BookFragment;
import com.example.hasee.ui.movie.MovieFragment;
import com.example.hasee.ui.news.NewsFragment;
import com.example.hasee.ui.setting.MyFragment;
import com.example.hasee.utils.StatusBarUtil;
import com.example.hasee.widget.BottomBar;
import com.example.hasee.widget.BottomBarTab;

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

            baseFragments[0] = NewsFragment.newInstance("0");
            baseFragments[1] = BookFragment.newInstance("1");
            baseFragments[2] = MovieFragment.newInstance("2");
            baseFragments[3] = MyFragment.newInstance("3");

            /**
             * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
             * R.id.contentContainer fragment布局或者viewpager
             * showPosition  默认显示
             * 后续fragments
             */
            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer,0,
                    baseFragments[0],
                    baseFragments[1],
                    baseFragments[2],
                    baseFragments[3]);
        }else {
            baseFragments[0] = findFragment(NewsFragment.class);
            baseFragments[1] = findFragment(BookFragment.class);
            baseFragments[2] = findFragment(MovieFragment.class);
            baseFragments[3] = findFragment(MyFragment.class);
        }

        bottomBar.addItem(new BottomBarTab(this,R.mipmap.bootom_news,"新闻"))
                .addItem(new BottomBarTab(this,R.mipmap.bootom_book,"书籍"))
                .addItem(new BottomBarTab(this,R.mipmap.bootom_movie,"电影"))
                .addItem(new BottomBarTab(this,R.mipmap.bootom_my,"我的"))
                .setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position, int prePosition) {
                        getSupportDelegate().showHideFragment(baseFragments[position], baseFragments[prePosition]);
                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }
                });

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
