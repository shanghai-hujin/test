package com.example.hasee.ui.base;

import android.app.Dialog;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.utils.DialogHelper;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HASEE on 2018/4/28.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment
        implements IBase,BaseContract.BaseView {

    private View mRootView;
    private Unbinder unbinder;
    private P mPresenter;
    protected Context mContext;
    private Dialog loadingDialog;
    private Dialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null){
            mRootView = createView(inflater, container, savedInstanceState);
        }else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if(parent != null){
                parent.removeView(mRootView);
            }
        }

        mContext = mRootView.getContext();
        loadingDialog = DialogHelper.getLoadingDialog(getActivity());
        return mRootView;
    }


    public abstract  P createPresenter();

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getContentLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachView();
        bindView(view,savedInstanceState);
        mLoadingDialog = DialogHelper.getLoadingDialog(getActivity());
    }

    private void attachView() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //rx已经交给RxLifecyc处理
    }

    @CallSuper
    @MainThread
    protected void initLifecycleObserver(@NotNull Lifecycle lifecycle) {
        lifecycle.addObserver(null);
    }



    @Override
    public void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFaild() {

    }

    @Override
    public void showNoNet() {

    }

    @Override
    public void onRetry() {

    }


    protected void showLoadingDialog() {
        if (mLoadingDialog != null)
            mLoadingDialog.show();
    }

    protected void showLoadingDialog(String str) {
        if (mLoadingDialog != null) {
            TextView tv = (TextView) mLoadingDialog.findViewById(R.id.tv_load_dialog);
            tv.setText(str);
            mLoadingDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}
