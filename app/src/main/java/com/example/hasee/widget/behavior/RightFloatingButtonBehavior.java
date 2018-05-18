package com.example.hasee.widget.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;

import com.example.hasee.utils.AnimatorUtil;

/**
 * Demo ${CLASS}
 *  news的行为控制器
 * @author TT
 * @date 2018/5/18 11:11
 */

public class RightFloatingButtonBehavior extends FloatingActionButton.Behavior {

    /**
     * 是否正在动画
     */
    private boolean isAnimateIng = false;

    /**
     * 是否已经显示
     */
    private boolean isShow = true;


    public RightFloatingButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    /**
     * 竖直方向才需要开始运动
     */
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull FloatingActionButton child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target, int nestedScrollAxes, int type) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton child,
                               @NonNull View target,
                               int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed,
                               int type) {
        //向上滑动，隐藏FAB
        if((dyConsumed > 0 || dyUnconsumed >0) && !isAnimateIng && isShow){
            //向上滑动，并且不是正在动画，并且显示了
            AnimatorUtil.translateHideR(child, new StateListener(){
                @Override
                public void onAnimationStart(View view) {
                    super.onAnimationStart(view);
                    isShow = false;
                }
            });
        //向下滑动，显示FAB
        }else if(dyConsumed < 0 || dxUnconsumed < 0 && !isAnimateIng && !isShow){
            AnimatorUtil.translateShowR(child, new StateListener(){
                @Override
                public void onAnimationStart(View view) {
                    super.onAnimationStart(view);
                    isShow = true;
                }
            });
        }


        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    class StateListener implements ViewPropertyAnimatorListener {
        @Override
        public void onAnimationStart(View view) {
            isAnimateIng = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimateIng = false;
        }

        @Override
        public void onAnimationCancel(View view) {
            isAnimateIng = false;
        }
    }

}
