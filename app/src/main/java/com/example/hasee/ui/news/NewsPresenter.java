package com.example.hasee.ui.news;

import com.example.hasee.R;
import com.example.hasee.bean.Channel;
import com.example.hasee.bean.WeatherBean;
import com.example.hasee.http.OtherHttpApi;
import com.example.hasee.ui.MyApplication;
import com.example.hasee.ui.base.BaseObserver;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.utils.RxUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/4 17:19
 */

class NewsPresenter extends BasePresenter<NewsContract.NewsView> implements NewsContract.NewsPresenter{

    OtherHttpApi otherHttpApi;
    /**
     *说明:被inject标记了，会被注入到被 component标记的目标
     * 构造函数中的参数由 module来提供
     * 然后在 component标记的目标中使用  @Inject标记后，直接使用该对象
     *作者:hujin
     *添加时间:2018/8/20 11:07
     *修改人:hujin
     *修改时间:2018/8/20 11:07
     */
    @Inject
    public NewsPresenter(OtherHttpApi otherHttpApi) {
        this.otherHttpApi = otherHttpApi;
    }

    @Override
    public void getChannel() {
        //规定新闻的类别，存在本地数据库
        List<Channel> channelList;
        List<Channel> myChannels = new ArrayList<>();
        List<Channel> otherChannels = new ArrayList<>();
//        channelList = ChannelDao.getChannels();
//        if(channelList.size() < 1){
//            //空的，取本地数据
//            List<String> channelName = Arrays.asList(MyApplication.getContext().getResources()
//            .getStringArray(R.array.news_channel));
//            List<String> channelId = Arrays.asList(MyApplication.getContext().getResources()
//                    .getStringArray(R.array.news_channel_id));
//
//            List<Channel> channels = new ArrayList<>();
//
//            for (int i = 0; i < channelName.size(); i++) {
//                Channel channel = new Channel();
//                channel.setChannelId(channelId.get(i));
//                channel.setChannelName(channelName.get(i));
//                //只有第1个，index为0的为不能移除
//                channel.setChannelType(i< 1 ? 1 : 0);
//                //最后四个默认不在tab上面
//                channel.setChannelSelect(i < channelId.size()-3);
//                if (i < channelId.size() - 3) {
//                    myChannels.add(channel);
//                } else {
//                    otherChannels.add(channel);
//                }
//                channels.add(channel);
//            }
//            //保存数据在数据库里面
//            DataSupport.saveAllAsync(channels).listen(new SaveCallback() {
//                @Override
//                public void onFinish(boolean success) {
//
//                }
//            });
//
//            channelList = new ArrayList<>();
//            channelList.addAll(channels);
//        }else {
//            //从数据库里面取数据
//            channelList = ChannelDao.getChannels();
//            Iterator<Channel> iterator = channelList.iterator();
//            while (iterator.hasNext()){
//                Channel channel = iterator.next();
//                if(!channel.isChannelSelect()){
//                    //未选中的加入其它，并移除，剩下的为tab选中的
//                    otherChannels.add(channel);
//                    iterator.remove();
//                }
//            }
//            myChannels.addAll(channelList);
//
//        }
//
//        mView.loadData(myChannels,otherChannels);

    }

    /**
     * 获取网络
     */
    public void getWeather(){
        otherHttpApi.getWeather("96tev0flvjjg4a1o","shanghai")
                .compose(RxUtils.<WeatherBean>rxSchedulerHelper())
                .compose(mView.<WeatherBean>bindToLife())
                .subscribe(new BaseObserver<WeatherBean>(mView) {
                    @Override
                    public void onNext(WeatherBean weatherBean) {
                        mView.showWeather(weatherBean);
                    }
                });

    }

}
