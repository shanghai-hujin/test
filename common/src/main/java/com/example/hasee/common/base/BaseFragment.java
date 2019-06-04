package com.example.hasee.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public abstract class BaseFragment extends SupportFragment implements IBaseFragment,LifecycleProvider<FragmentEvent> {
    private View mRootView;
    private Context mContext;
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
    @Override
    public View getView() {
        return mRootView;
    }


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public <T extends View> T find(int resId) {
        return (T) mRootView.findViewById(resId);
    }


    /**
     * The activity is onAttach to the Fragment
     */
    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        mRootView = createView(inflater,container,savedInstanceState);
        return mRootView;
    }

    /**
     * 当前fragmeng可见
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }


    /**
     * The default is landscape animation
     *
     * @return
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!useLazy()) {
            bindView(view,savedInstanceState);
        }
    }


    /**
     * 子类可以设置
     * useLazy
     * @return
     */
    public boolean useLazy() {
        return true;
    }


}
