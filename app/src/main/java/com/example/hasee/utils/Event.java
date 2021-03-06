package com.example.hasee.utils;

import com.example.hasee.bean.Channel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/10 18:10
 */

public class Event {

    public static class LoginStausEvent {
        public boolean login;
    }

    public static class StartNavigationEvent {
        public boolean start;
    }

    public static class SlideTopEvent {
        public int position;
    }

    public static class SelectChannelEvent {

        public String channelName;

        public SelectChannelEvent(String channelName) {
            this.channelName = channelName;
        }
    }

    public static class DataActivityChange{
        public boolean isAddActivity;
    }

    public static class NewChannelEvent {
        public List<Channel> selectedDatas;

        public List<Channel> unSelectedDatas;

        public List<Channel> allChannels;

        /**
         * 添加的第一个频道名称
         */
        public String firstChannelName;

        public NewChannelEvent(List<Channel> allChannels, String firstChannelName) {
            if (allChannels == null) {
                return;
            }
            this.allChannels = allChannels;
            this.firstChannelName = firstChannelName;

            selectedDatas = new ArrayList<>();
            unSelectedDatas = new ArrayList<>();

            Iterator iterator = allChannels.iterator();
            while (iterator.hasNext()) {
                Channel channel = (Channel) iterator.next();
                if (channel.getItemType() == Channel.TYPE_MY || channel.getItemType() == Channel.TYPE_OTHER) {
                    iterator.remove();
                } else if (channel.getItemType() == Channel.TYPE_MY_CHANNEL) {
                    selectedDatas.add(channel);
                } else {
                    unSelectedDatas.add(channel);
                }
            }
        }
    }
}
