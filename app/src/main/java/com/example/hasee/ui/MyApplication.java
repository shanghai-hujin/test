package com.example.hasee.ui;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hasee.R;
import com.example.hasee.common.Constants;
import com.example.hasee.common.base.InitializeService;
import com.example.hasee.common.base.app.AsLibUtil;
import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.net.IDataHelper;
import com.example.hasee.ui.async.AsyncLayoutLoader;


/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/28 16:12
 */
public class MyApplication extends BaseApplication {

    private static final String[] MODULESLIST =
            {"com.example.hasee.news.NewsApplication",
                    "com.example.hasee.login.LoginApplication"};


    private static MyApplication instance;

    public static boolean isIsLeng() {
        return isLeng;
    }

    public static void setIsLeng(boolean isLeng) {
        MyApplication.isLeng = isLeng;
    }

    private static boolean isLeng;

//    @Autowired(name = "/news/application1")
//    ApplicationLike mApplicationLikeMoudle1;
//    @Autowired(name = "/login/application2")
//    ApplicationLike mApplicationLikeMoudle2;adb shell am start -W -n com.example.hasee/.ui.WelcomeActivity

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ARouter.getInstance().inject(this);
//        if (mApplicationLikeMoudle1!=null)adb shell am force-stop com.example.hasee
//            AsLibUtil.addAsLIbChild(mApplicationLikeMoudle1);
//        if (mApplicationLikeMoudle2!=null)
//            AsLibUtil.addAsLIbChild(mApplicationLikeMoudle2);

        AsLibUtil.doCreateAsLibrary(this);
        //耗时并不重要的初始化
        InitializeService.start(this);
        Toast.makeText(instance,"启动",Toast.LENGTH_LONG).show();
        Log.d("启动","时间=="+System.currentTimeMillis());
        isLeng = true;

        initView();
    }

    private void initView() {
        //异步加载布局
        AsyncLayoutLoader.getInstance(getApplicationContext()).inflate(R.layout.activity_main, null, null);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 必须重新设置网络config
     *
     * @return
     */
    @Override
    public IDataHelper.NetConfig getNewConfig() {
        return new IDataHelper.NetConfig().setBaseURL(Constants.BASE_URL_LOGIN);
    }

    /**
     * 终止方法中destory路由
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}