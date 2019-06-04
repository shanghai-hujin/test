package com.example.hasee.common.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.common.R;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;



import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * Demo ${CLASS}
 *  新的基类 不要耦合mvp  部分activity使用mvc更方便
 * @author TT
 * @date 2018/4/26 15:00
 */

public abstract class BaseActivity extends SupportActivity implements LifecycleProvider<ActivityEvent>, IBaseActivity {

    private View mRootView;
    /**
     * Rewrite RxLife to control the life cycle
     */
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
    }



    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(getLayoutId(), container);
        //存放静态页面，后续添加
        bindView(view, savedInstanceState);
        return view;
    }

    /**
     * replace  findViewById
     *
     * @param resId   layout resId
     * @param <T>   View
     * @return    View
     */
    @Override
    public  <T extends View> T find(int resId) {
        if(mRootView == null){
            return (T) super.findViewById(resId);
        }else {
            return (T) mRootView.findViewById(resId);
        }

    }


    @Override
    public void finishAnim() {
        finishAnim(false);
    }

    @Override
    public void finishAnim(boolean isAnim) {
        finish();
        if(isAnim){
            this.overridePendingTransition(0, R.anim.slide_out_right);
        }
    }

    /**
     * Please try to override this method to avoid copying onBackPress(),
     * To ensure that the onBackPressedSupport() rewind event in the SupportFragment is executed normally
     */
    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }



    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }




    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }


}
