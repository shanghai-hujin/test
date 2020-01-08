package com.example.hasee.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hasee.R;
import com.example.hasee.common.base.mvp.XDaggerActivity;
import com.example.hasee.di.ClientDiHelper;
import com.example.hasee.http.ComPath;
import com.example.hasee.http.cookies.CookiesManager;
import com.example.hasee.news.ui.main.NewsFragment;
import com.example.hasee.ui.MyApplication;
import com.example.hasee.ui.drawer.BluetoothActivity;
import com.example.hasee.ui.drawer.chat.MainChatActivity;
import com.example.hasee.utils.Event;
import com.example.hasee.utils.PasswordHelp;
import com.example.hasee.utils.PerfectClickListener;
import com.example.hasee.utils.RxBus;
import com.example.hasee.utils.StatusBarUtil;
import com.example.hasee.widget.BottomBar;
import com.example.hasee.widget.BottomBarTab;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import es.dmoral.toasty.Toasty;
import io.reactivex.functions.Consumer;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author TT
 */
@Route(path = ComPath.PATH_MainActivity)
public class MainActivity extends XDaggerActivity<MainPresenter> implements MainContract.MainView, NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout contentContainer;
    private BottomBar bottomBar;

    private NavigationView navView;
    private DrawerLayout drawerLayout;


    /**
     * 先加2个
     */
    private SupportFragment[] baseFragments = new SupportFragment[4];
    private double exitTime;
    private ConstraintLayout mClHeader;
    private TextView mTvName;
    private TextView mTvLv;
    private TextView mTvState;
    private TextView mTvIcon;
    private TextView mTvB;
    private ImageView mIvHeadNoftiy;
    private ImageView mIvHeadSwitchMode;
    private ImageView mIcUser;
    private ConstraintLayout mClLogin;
    private ImageView mIvLogin;


    /**
     * 加载布局
     * view 返回的布局view
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        //测试异步
//        AsyncLayoutLoader layoutLoader = AsyncLayoutLoader.getLayoutLoader(R.layout.activity_main);
//        View realView = layoutLoader.getRealView();
//        if(realView != null){
//            System.out.print("不为空");
//            View viewById = realView.findViewById(R.id.bottomBar);
//            setContentView(R.layout.activity_main);
//        }
        contentContainer = (FrameLayout) find(R.id.contentContainer);
        bottomBar = (BottomBar) find(R.id.bottomBar);
        navView = (NavigationView) find(R.id.nav_view);
        drawerLayout = (DrawerLayout) find(R.id.drawer_layout);

        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.colorPrimary), 125);
        disableNavigationViewScrollbars(navView);

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
            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer, 0,
                    baseFragments[0]);
        } else {
            baseFragments[0] = findFragment(NewsFragment.class);
        }

        bottomBar.addItem(new BottomBarTab(this, R.mipmap.bootom_news, "新闻"))
                //  .addItem(new BottomBarTab(this, R.mipmap.bootom_book, "书籍"))
                // .addItem(new BottomBarTab(this, R.mipmap.bootom_movie, "电影"))
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

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                super.onFragmentCreated(fm, f, savedInstanceState);
                Log.i("MainActivity", "onFragmentCreated--->" + f.getClass().getSimpleName());
            }

            @Override
            public void onFragmentStopped(FragmentManager fm, Fragment f) {
                super.onFragmentStopped(fm, f);
                Log.i("MainActivity", "onFragmentStopped--->" + f.getClass().getSimpleName());
            }
        }, true);

        initData();
        MyApplication.setIsLeng(false);


    }

    /**
     * 返回一个xx..16位..xx-xx..16位..xx-xx..16位..xx的id
     * @return
     */
    public String  getDeviceId(){
        StringBuilder sbDeviceId = new StringBuilder();
        //mac地址 如果mac == 02:00:00:00:00:0 视为未获取到
        String wifiMac = md5Decode16(getWifiMac()).toUpperCase();
        sbDeviceId.append(wifiMac);
        sbDeviceId.append("-");


        //imei地址 如果imeiId == "" 视为未获取到
        String imeiId = md5Decode16(getImeiId());
        sbDeviceId.append(imeiId);
        sbDeviceId.append("-");


        //几个核心硬件参数生成的uuid  分辨率 cpu核心数 cpu型号 屏幕尺寸 存储
        //这些是不会随刷机或者返厂设置而改变 但是这些参数单独作为设备id 会大量重复
        String deviceBuildID = md5Decode16(getBuildUUid());
        sbDeviceId.append(deviceBuildID);
        sbDeviceId.append("-");


        //型号 制造商 版本号 android_id
        //会随刷机放生变化
        String deviceBuildIDChange = md5Decode16(getBuildUUidTwo());
        sbDeviceId.append(deviceBuildIDChange);

        return sbDeviceId.toString();

        /**
         * 区分几个纬度，来定义你的需求（假设 wifiMac 和 imeiId 正常获取下 ）
         *      如果mac地址 或者 deviceBuildIDChange 改变 ，而 imeiId deviceBuildID没发生改变
         *      是不是可判断 同一用户下该设备刷机了，视为同一用户，羊毛党屏蔽掉
         *
         *      如果 imeiId 发生改变，换手机了（imeiId 还是作为比较强的 设备映射）
         *
         *      各种策略都可以自己后端定制
         */

    }

    private String getBuildUUidTwo() {
        return null;
    }

    private String getBuildUUid() {
        return null;
    }

    private String getImeiId() {
        return null;
    }

    public String md5Decode32(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 16位MD5加密
     * 实际是截取的32位加密结果的中间部分(8-24位)
     * @param content
     * @return
     */
    public String md5Decode16(String content) {
        if(TextUtils.isEmpty(md5Decode32(content).substring(8, 24))){
            return "0000000000000000";
        }
        return md5Decode32(content).substring(8, 24);
    }


    private void rxPermissionTest() {
        String androidID1 = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidID1.hashCode(), ((long)androidID1.hashCode() << 32));

        String randomUUID = UUID.randomUUID().toString();
        //存储在本地


        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        String androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);



        getWifiMac();

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {

                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    //不同的手机设备返回IMEI，MEID或者ESN码
                    String deviceId = tm.getDeviceId();
                    //返回IMEI
                    String imei = tm.getImei();
                    //返回MEID
                    String meid = tm.getMeid();
                    //手机SIM卡唯一标识
                    String simSerialNumber = tm.getSimSerialNumber();
                    //返回例如独特的用户ID，一个GSM手机的号码。
                    String subscriberId = tm.getSubscriberId();
                    //手机号码
                    String line1Number = tm.getLine1Number();
                } else {

                }
            }
        });
    }

    private  String getMacAddress() {
        String WifiAddress = "02:00:00:00:00:00";
        try {
            WifiAddress = new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WifiAddress;
    }

    public  String getWifiMac() {
        //默认的值
        String mac = "02:00:00:00:00:00";

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_WIFI_STATE)
                    != PackageManager.PERMISSION_GRANTED){
                // 小于6.0手机
                WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo winfo = wifi.getConnectionInfo();
                mac =  winfo.getMacAddress();
            }
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            try {
                mac = new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED){
                WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo winfo = wifi.getConnectionInfo();
                mac =  winfo.getMacAddress();
                try {
                    List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
                    for (NetworkInterface nif : all) {
                        if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                        byte[] macBytes = nif.getHardwareAddress();
                        if (macBytes == null) {
                            return mac;
                        }

                        StringBuilder res1 = new StringBuilder();
                        for (byte b : macBytes) {
                            res1.append(String.format("%02X:", b));
                        }

                        if (res1.length() > 0) {
                            res1.deleteCharAt(res1.length() - 1);
                        }
                        return res1.toString();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return mac;
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


    public void initData() {
        EventBus.getDefault().register(this);
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
                            } else {
                                mClLogin.setVisibility(View.VISIBLE);
                                navView.getMenu().findItem(R.id.item_loginout).setVisible(false);
                            }
                        }
                    });

                });


    }


    /**
     * 初始化登录状态
     */
    private void initLoginStatus() {

        if (PasswordHelp.readLoginStatus()) {
            //登录了
            mClLogin.setVisibility(View.GONE);
            navView.getMenu().findItem(R.id.item_loginout).setVisible(true);
        } else {
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
        mClHeader = (ConstraintLayout) headerView.findViewById(R.id.cl_header);
        mTvName = (TextView) headerView.findViewById(R.id.tv_name);
        mTvLv = (TextView) headerView.findViewById(R.id.tv_lv);
        mTvState = (TextView) headerView.findViewById(R.id.tv_state);
        mTvIcon = (TextView) headerView.findViewById(R.id.tv_icon);
        mTvB = (TextView) headerView.findViewById(R.id.tv_b);
        mIvHeadNoftiy = (ImageView) headerView.findViewById(R.id.iv_head_noftiy);
        mIvHeadSwitchMode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        mIcUser = (ImageView) headerView.findViewById(R.id.ic_user);
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
        EventBus.getDefault().unregister(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LoginEvent loginEvent) {
        if (loginEvent != null) {

        }

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
                        startActivity(new Intent(MainActivity.this, BluetoothActivity.class));
                        break;
                    case R.id.item_point:
                        startActivity(new Intent(MainActivity.this, MainChatActivity.class));

                        break;

                    case R.id.item_down:
                        //  startActivity(new Intent(MainActivity.this, TestXidingActivity.class));
                        break;
                    case R.id.item_led:
                        //显示led字幕
                        rxPermissionTest();
                        //    startActivity(new Intent(MainActivity.this, LEDSettingActivity.class));
                        break;
                    case R.id.item_theme:

                        break;
                    case R.id.item_loginout:
                        //退出登录
                        MaterialDialog materialDialog = new MaterialDialog(MainActivity.this);
                        materialDialog.content("您是否确定退出登录")
                                .btnText("取消", "确定")
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
                            ARouter.getInstance().build("/login/LoginActivity").navigation();
                            //跳登录
                            break;
                        default:
                            break;
                    }
                }
            }, 250);
        }
    };

    @Override
    public void initInject(Bundle savedInstanceState) {
        ClientDiHelper.getActivityComponent(getActivityModule()).inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_nv;
    }
}
