package com.example.hasee.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.hasee.R;

import java.util.List;

/**
 * Created by TT on 2017/8/28.
 * 为UPMarqueeView里面的View切换提供动画效果
 */

public class UPMarqueeView extends ViewFlipper {


    private Context mContext;
    /**
     *翻转时间间隔
     */
    private int interval = 2000;
    /**
     * 是否设置了动画时间
     */
    private boolean isSetAnimDuration = false;

    public boolean isSetAutoPlay() {
        return isSetAutoPlay;
    }

    public void setSetAutoPlay(boolean setAutoPlay) {
        isSetAutoPlay = setAutoPlay;
    }

    /**
     * 是否设置了自动轮播跑马灯
     */
    private boolean isSetAutoPlay = true;

    public int getAnimDuration() {
        return animDuration;
    }

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
    }

    /**
     * 设置动画时间，默认为500ms
     */
    private int animDuration = 500;

    public UPMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        setFlipInterval(interval);
        //是否自动播放
        setAutoStart(isSetAutoPlay);
        Animation animIn = AnimationUtils.loadAnimation(mContext,  R.anim.anim_marquee_in);
        if(isSetAnimDuration){
            animIn.setDuration(animDuration);
        }
        setInAnimation(animIn);

        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if(isSetAnimDuration){
            animOut.setDuration(animDuration);
        }
        setOutAnimation(animOut);

    }

    /**
     * 点击
     */
    private OnItemClickListener onItemClickListener;

    /**
     * 设置监听接口
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * item_view的接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }


    /**
     * 由外部设置数据
      * @param views
     */
    public void  setViews(final List<View> views){
        if(views == null || views.size() == 0){
            return;
        }
        removeAllViews();
        int length = views.size();
        for (int i = 0; i < length; i++) {
            final int position=i;
            //设置监听回调
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, views.get(position));
                   }
                }
            });

            ViewGroup viewGroup = (ViewGroup) views.get(i).getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            addView(views.get(i));
        }
        startFlipping();

    }

}
