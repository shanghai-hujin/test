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
import com.example.hasee.ui.MyApplication;
import com.example.hasee.utils.DialogHelper;
import com.example.hasee.widget.stateview.MultipleStatusView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * Created by HASEE on 2018/4/28.
 */

public abstract class BaseFragment<P extends BaseContract.BasePresenter> extends SupportFragment
        implements IBase, BaseContract.BaseView {


    private View mRootView;
    private Unbinder unbinder;

    protected Context mContext;
    private Dialog mLoadingDialog;
    @Nullable
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    /**
     * 父类标记了
     */
    @Inject
    protected P mPresenter;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = createView(inflater, container, savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        mLoadingDialog = DialogHelper.getLoadingDialog(getActivity());
        mContext = mRootView.getContext();
        return mRootView;
    }

    @Override
    public View getView() {
        return mRootView;
    }


    @Override
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getContentLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInjector(MyApplication.getInstance().getApplicationComponent());
        attachView();
        bindView(view, savedInstanceState);
        //初始化state布局
        initStateView();

    }

    private void initStateView() {
        if(multipleStatusView != null){
            multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRetry();
                }
            });
        }
    }

    private void attachView() {
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
            mPresenter = null;
        }
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        Toasty.normal(getActivity(), errorMsg).show();
    }

    @Override
    public void showError() {
        if (multipleStatusView != null) {
            multipleStatusView.showError();
        }
    }


    @Override
    public void showLoading() {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
    }

    @Override
    public void showSuccess(String str) {
        if (multipleStatusView != null) {
            multipleStatusView.showContent();
        }
    }

    @Override
    public void showFaild() {
        if (multipleStatusView != null) {
            multipleStatusView.showError();
        }
    }

    @Override
    public void showNoNet() {
        if (multipleStatusView != null) {
            multipleStatusView.showNoNetwork();
        }
    }

    @Override
    public void onRetry() {
        multipleStatusView.showLoading();
        multipleStatusView.postDelayed(new Runnable() {
            @Override public void run() {
                multipleStatusView.showContent();
            }
        }, 600);
    }


    protected void errToast(String err) {
        Toasty.error(getActivity(), err, 1).show();
    }

    protected void sucToast(String scs) {
        Toasty.success(getActivity(), scs, 1).show();
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
