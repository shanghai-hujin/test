package com.example.hasee.common.utils;

import android.view.View;
import android.view.View.OnClickListener;

import java.util.Calendar;

/**
 * Created by TT on 2017/6/29.
 * 防止一个控件一秒内多次点击
 */

public abstract class PerfectClickListener implements OnClickListener {
    public static final int MIN_CLICK_INTERVAL =1000;
    private long lastTime =0;
    private int id = -1;


    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        int mId =view.getId();
        if(mId!=id){
            id = mId;
            lastTime =currentTime;
            onNoDoubleClick(view);
            return;
        }

        if(currentTime-lastTime>MIN_CLICK_INTERVAL){
            lastTime=currentTime;
            onNoDoubleClick(view);
        }
    }

    protected abstract void onNoDoubleClick(View view);
}
