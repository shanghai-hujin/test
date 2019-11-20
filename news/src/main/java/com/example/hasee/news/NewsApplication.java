package com.example.hasee.news;

import android.util.Log;

import com.example.hasee.common.Constants;
import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.net.IDataHelper;

//@Route(path = "/news/application1")
public class NewsApplication extends BaseApplication {

    @Override
    public void onCreate() {//该方法只要在单独运行时作为入口APP类时才会调用
        Log.d("NewsApplication","onCreate");
        super.onCreate();
    }


    /**
     * 必须重新设置BaseUrl
     * @return
     */
    @Override
    public  IDataHelper.NetConfig getNewConfig() {
        return new IDataHelper.NetConfig().setBaseURL(Constants.BASE_URL_LOGIN);
        //.configInterceptors(new Interceptor[]{new TokenInterceptor()})//配置Token
        // .configConverterFactory(new Converter.Factory[]{ProtoConverterFactory.create()});
        //配置Proto格式工厂
    }
}
