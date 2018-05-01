package com.example.hasee.ui.base;

import android.app.Dialog;
import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.http.RxLifecycleUtils;
import com.example.hasee.utils.DialogHelper;
import com.orhanobut.logger.Logger;
import com.uber.autodispose.AutoDisposeConverter;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 15:00
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBase,BaseContract.BaseView {

    private View mRootView;
    protected P basePresenter;
    private Unbinder unbinder;
    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
        initLifecycleObserver(getLifecycle());
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


    @CallSuper
    @MainThread
    protected void initLifecycleObserver(@NotNull Lifecycle lifecycle) {
        if(basePresenter != null){
            lifecycle.addObserver(basePresenter);
        }

    }

    private void attachView() {
        basePresenter = createPresenter();
        if (basePresenter != null) {
            basePresenter.attachView(this);
        }else {
            Logger.e("P为空");
        }
    }

    public abstract P createPresenter();

    public P getPresenter() {
        return basePresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //rx已经交给RxLifecyc处理
    }


    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
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
