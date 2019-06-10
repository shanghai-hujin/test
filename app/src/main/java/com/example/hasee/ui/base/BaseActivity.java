package com.example.hasee.ui.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hasee.R;
import com.example.hasee.ui.MyApplication;
import com.example.hasee.utils.DialogHelper;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleTransformer;


import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 15:00
 */

public abstract class BaseActivity<P extends BaseContract.BasePresenter> extends SupportActivity
        implements IBase,BaseContract.BaseView{

    private View mRootView;
    @Inject
    protected P basePresenter;
    private Unbinder unbinder;
    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
        initInjector(MyApplication.getInstance().getApplicationComponent());
        //基类注入
        ARouter.getInstance().inject(this);
        attachView();
        bindView(mRootView, savedInstanceState);
        initStateView();
        mLoadingDialog = DialogHelper.getLoadingDialog(this);
    }


    /**
     * 静态页
     */
    private void initStateView() {


    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }




    private void attachView() {
        if (basePresenter != null) {
            basePresenter.attachView(this);
        }else {
            Logger.e("P为空");
        }
    }

    public P getPresenter() {
        return basePresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if(basePresenter != null){
            basePresenter.detachView();
        }
    }


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(getContentLayout(), container);
        //存放静态页面，后续添加
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public View getView() {
        return mRootView;
    }


    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showError() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess(String str) {

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

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    @Override
    public void onSwipeBackLayoutCancel() {

    }

}
