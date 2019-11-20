package com.example.hasee.login;

import com.example.hasee.common.Constants;
import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.net.IDataHelper;

/**
 * 该模块的业务功能初始化
 */
//@Route(path = "/login/application2")
public class LoginApplication extends BaseApplication {


    //不要对一个 Activity Context 保持长生命周期的引用。尽量在一切可以使用应用 ApplicationContext 代替 Context 的地方进行替换。


    @Override
    public void onCreate() {//该方法只要在单独运行时作为入口APP类时才会调用
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
