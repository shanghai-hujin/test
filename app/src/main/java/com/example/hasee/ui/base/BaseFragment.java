package com.example.hasee.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.utils.DialogHelper;
import com.trello.rxlifecycle2.LifecycleTransformer;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HASEE on 2018/4/28.
 */

public abstract class BaseFragment<P extends BaseContract.BasePresenter> extends SupportFragment
        implements IBase,BaseContract.BaseView {

    private View mRootView;
    private Unbinder unbinder;
    protected P mPresenter;
    protected Context mContext;
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


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //rx已经交给RxLifecyc处理
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showError() {

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

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }


    protected void showLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
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
