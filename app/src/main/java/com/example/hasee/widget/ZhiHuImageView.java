package com.example.hasee.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;


/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2017/12/5 17:50
 */

public class ZhiHuImageView extends ImageView {

    /**
     * 测量的实际最小高度
     */
    private int mMinDx;
    /**
     * 移动的距离
     */
    private int mDx;

    public ZhiHuImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMinDx = h;
    }

    /**
     * 暴露给外界来设置移动距离的
     * @param dx  滑动的距离
     */
    public void setDy(int dx){
        /**
         * 拿到图片的Drawable，判空处理
         */
        if(getDrawable() == null){
            return;
        }
        //需要移动的距离
        mDx = dx - mMinDx;

        //滑动距离 必须大于0
        if (mDx <= 0) {
            mDx = 0;
        }
        //图片滑动最大距离  =  图片实际高度   -   显示的最小高度（xml布局中设置的高度）
        if (mDx > getDrawable().getBounds().height() - mMinDx) {
            mDx = getDrawable().getBounds().height() - mMinDx;
        }

        //每次算好距离开始重绘
        invalidate();
    }


    public int getDx(){
        return mDx;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if(drawable == null){
            Log.e("Tag","NULL=drawable");
            return;
        }
        int w = getWidth();
        /**
         * 高度  = （宽度/实际宽度）*实际高度
         * 因为宽度是match的，getIntrinsicWidth()获得是固有宽度
         */
        int h = (int) (getWidth() * 1.0f / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, w, h);

        //锁画布
        canvas.save();
        //画布原点移动到新的坐标
        canvas.translate(0, -getDx());
        super.onDraw(canvas);
        canvas.restore();
    }
}
