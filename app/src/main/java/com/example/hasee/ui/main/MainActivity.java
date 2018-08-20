package com.example.hasee.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.di.component.ApplicationComponent;
import com.example.hasee.di.component.DaggerHttpComponent;
import com.example.hasee.http.cookies.CookiesManager;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.ui.book.BookFragment;
import com.example.hasee.ui.movie.MovieFragment;
import com.example.hasee.ui.mycenter.MyFragment;
import com.example.hasee.ui.news.NewsFragment;
import com.example.hasee.ui.person.LEDSettingActivity;
import com.example.hasee.ui.person.LoginActivity;
import com.example.hasee.utils.Event;
import com.example.hasee.utils.PasswordHelp;
import com.example.hasee.utils.PerfectClickListener;
import com.example.hasee.utils.RxBus;
import com.example.hasee.utils.StatusBarUtil;
import com.example.hasee.widget.BottomBar;
import com.example.hasee.widget.BottomBarTab;
import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * @author TT
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView,  NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.contentContainer)
    FrameLayout contentContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    private BaseFragment[] baseFragments = new BaseFragment[4];
    private double exitTime;
    private ConstraintLayout mClHeader;
    private TextView mTvName;
    private TextView mTvLv;
    private TextView mTvState;
    private TextView mTvIcon;
    private TextView mTvB;
    private ImageView mIvHeadNoftiy;
    private ImageView mIvHeadSwitchMode;
    private SimpleDraweeView mIcUser;
    private ConstraintLayout mClLogin;
    private ImageView mIvLogin;

    @Override
    public int getContentLayout() {
        return R.layout.activity_main_nv;
    }

    @Override
    /**
     * 加载布局
     * view 返回的布局view
     */
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.colorPrimary), 125);
        disableNavigationViewScrollbars(navView);

        if (savedInstanceState == null) {

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
            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer, 0,
                    baseFragments[0],
                    baseFragments[1],
                    baseFragments[2],
                    baseFragments[3]);
        } else {
            baseFragments[0] = findFragment(NewsFragment.class);
            baseFragments[1] = findFragment(BookFragment.class);
            baseFragments[2] = findFragment(MovieFragment.class);
            baseFragments[3] = findFragment(MyFragment.class);
        }

        bottomBar.addItem(new BottomBarTab(this, R.mipmap.bootom_news, "新闻"))
                .addItem(new BottomBarTab(this, R.mipmap.bootom_book, "书籍"))
                .addItem(new BottomBarTab(this, R.mipmap.bootom_movie, "电影"))
                .addItem(new BottomBarTab(this, R.mipmap.bootom_my, "我的"))
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

    /**
     * 去掉滚动条
     *
     * @param navigationView
     */
    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }


    @Override
    /**
     * 初始化数据数据
     */
    public void initData() {
        initToolbar();
        initHttpData();
        initNav();
        initLoginStatus();

        //监听事件
        RxBus.INSTANCE.toFlowable(Event.StartNavigationEvent.class)
                .compose(bindToLifecycle())
                .subscribe(event -> {
                    if (event.start) {
                        toggleDrawer();//打开
                    }
                });

        RxBus.INSTANCE.toFlowable(Event.LoginStausEvent.class)
                .compose(bindToLifecycle())
                .subscribe(event -> {

                        mClLogin.post(new Runnable() {
                            @Override
                            public void run() {
                                if (event.login) {
                                    mClLogin.setVisibility(View.GONE);
                                    navView.getMenu().findItem(R.id.item_loginout).setVisible(true);
                                }else {
                                    mClLogin.setVisibility(View.VISIBLE);
                                    navView.getMenu().findItem(R.id.item_loginout).setVisible(false);
                                }
                            }
                        });

                });


    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
                .inject(this);
    }

    /**
     * 初始化登录状态
     */
    private void initLoginStatus() {

        if(PasswordHelp.readLoginStatus()){
            //登录了
            mClLogin.setVisibility(View.GONE);
            navView.getMenu().findItem(R.id.item_loginout).setVisible(true);
        }else {
            mClLogin.setVisibility(View.VISIBLE);
            navView.getMenu().findItem(R.id.item_loginout).setVisible(false);
        }
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 初始化侧边栏
     */
    private void initNav() {
        navView.setNavigationItemSelectedListener(this);
        View headerView = navView.getHeaderView(0);
        mClHeader = (ConstraintLayout)headerView.findViewById(R.id.cl_header);
        mTvName = (TextView)headerView.findViewById(R.id.tv_name);
        mTvLv = (TextView)headerView.findViewById(R.id.tv_lv);
        mTvState = (TextView)headerView.findViewById(R.id.tv_state);
        mTvIcon = (TextView)headerView.findViewById(R.id.tv_icon);
        mTvB = (TextView)headerView.findViewById(R.id.tv_b);
        mIvHeadNoftiy = (ImageView)headerView.findViewById(R.id.iv_head_noftiy);
        mIvHeadSwitchMode = (ImageView)headerView.findViewById(R.id.iv_head_switch_mode);
        mIcUser = (SimpleDraweeView)headerView.findViewById(R.id.ic_user);
        mClLogin = (ConstraintLayout) headerView.findViewById(R.id.cl_login);
        mIvLogin = (ImageView) headerView.findViewById(R.id.iv_login);

        mIvLogin.setOnClickListener(listener);
        mClLogin.setOnClickListener(listener);
        mTvName.setOnClickListener(listener);
        mTvLv.setOnClickListener(listener);
        mTvState.setOnClickListener(listener);
        mTvIcon.setOnClickListener(listener);
        mTvB.setOnClickListener(listener);
        mIvHeadNoftiy.setOnClickListener(listener);
        mIvHeadSwitchMode.setOnClickListener(listener);
        mIcUser.setOnClickListener(listener);
    }

    /**
     * 获取网络数据
     */
    private void initHttpData() {

    }

    /**
     * 初始化头部
     */
    private void initToolbar() {
       /* setSupportActionBar(commonToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            tvCommonTitle.setText("新闻");
            ibCommonBack.setVisibility(View.VISIBLE);
            actionBar.setDisplayShowTitleEnabled(false);
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(drawerLayout.getChildAt(1))) {
                drawerLayout.closeDrawers();
            } else {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    Toasty.normal(MainActivity.this, "再按一次退出", 1).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
        }
        return true;
    }

    /**
     * 侧边栏监听事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (item.getItemId()) {
                    case R.id.item_vip:
                        break;

                    case R.id.item_down:
                        break;
                    case R.id.item_led:
                        //显示led字幕
                        startActivity(new Intent(MainActivity.this, LEDSettingActivity.class));
                        break;
                    case R.id.item_theme:

                        break;
                    case R.id.item_loginout:
                        //退出登录
                        MaterialDialog materialDialog = new MaterialDialog(MainActivity.this);
                        materialDialog.content("您是否确定退出登录")
                                .btnText("取消","确定")
                                .showAnim(new BounceTopEnter())
                                .dismissAnim(new SlideBottomExit())
                                .show();
                        materialDialog.setOnBtnClickL(new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                materialDialog.dismiss();
                            }
                        }, new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                materialDialog.dismiss();
                                loginout();
                            }
                        });
                        break;

                    default:
                        break;
                }
            }
        }, 250);

        return false;
    }

    /**
     * 登出，清除本地cook
     */
    private void loginout() {
        CookiesManager.clearAllCookies();
        PasswordHelp.cleanPassword();
        Event.LoginStausEvent event = new Event.LoginStausEvent();
        event.login = false;
        RxBus.INSTANCE.post(event);
    }

    /**
     * 自定义的接口点击事件
     */
    private PerfectClickListener listener = new PerfectClickListener() {

        @Override
        protected void onNoDoubleClick(final View view) {
            drawerLayout.closeDrawer(GravityCompat.START);
            //加上延迟，使抽屉关闭动画完成后在进行点击事件判断
            drawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    switch (view.getId()) {
                        case R.id.iv_login:
                            //跳登录
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            break;
                        default:
                            break;
                    }
                }
            }, 250);
        }
    };

}
