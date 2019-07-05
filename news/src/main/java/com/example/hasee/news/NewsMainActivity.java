package com.example.hasee.news;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hasee.common.base.ui.BaseActivity;
import com.example.hasee.news.ui.main.NewsFragment;

import me.yokeyword.fragmentation.SupportFragment;

public class NewsMainActivity extends BaseActivity {

    private SupportFragment[] baseFragments = new SupportFragment[4];
    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            //阿里路由+supportfragment 拆分fragment
            baseFragments[0] = (SupportFragment) ARouter.getInstance().build("/home/NewsFragment").navigation();



            // baseFragments[1] = (SupportFragment) ARouter.getInstance().build("/home/BookFragment").navigation();
            //baseFragments[2] = (SupportFragment) ARouter.getInstance().build("/home/MovieFragment").navigation();
            //baseFragments[1] = (SupportFragment) ARouter.getInstance().build("/home/MyFragment").navigation();


            /**
             * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
             * R.id.contentContainer fragment布局或者viewpager
             * showPosition  默认显示
             * 后续fragments
             */
            getSupportDelegate().loadMultipleRootFragment(R.id.content, 0,
                    baseFragments[0]);
        } else {
            baseFragments[0] = findFragment(NewsFragment.class);
        }
        getSupportDelegate().showHideFragment(baseFragments[0], baseFragments[0]);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_main;
    }



}
