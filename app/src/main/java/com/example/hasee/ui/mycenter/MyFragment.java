package com.example.hasee.ui.mycenter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.bean.DataActivityBean;
import com.example.hasee.ui.adpater.MyCenterAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tr4android.support.extension.widget.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by HASEE on 2018/4/29.
 */

public class MyFragment extends BaseFragment<MyCenterPresenter> implements MyCenterContract.MyCenterView {

    @BindView(R.id.xrv_my)
    RecyclerView mXrvMy;
    @BindView(R.id.refresh_mylayout)
    SmartRefreshLayout mRefreshMylayout;
    @BindView(R.id.fab_document)
    FloatingActionButton mFabDocument;
    @BindView(R.id.fab_spreadsheet)
    FloatingActionButton mFabSpreadsheet;
    @BindView(R.id.fab_presentation)
    FloatingActionButton mFabPresentation;
    @BindView(R.id.fab_menu)
    FloatingActionMenu mFabMenu;
    @BindView(R.id.cl_my)
    ConstraintLayout mClMy;
    @BindView(R.id.iv_drag)
    ImageView mIvDrag;
    @BindView(R.id.tv_centent_title)
    TextView mTvCententTitle;
    @BindView(R.id.tb_centent)
    Toolbar mTbCentent;
    Unbinder unbinder1;
    @BindView(R.id.dimming_view)
    View mDimmingView;
    Unbinder unbinder;

    private List<DataActivityBean> mDataActivityBeanList = new ArrayList<>();
    private MyCenterAdapter mMyCenterAdapter;

    public static MyFragment newInstance(String param1) {
        Bundle args = new Bundle();
        MyFragment newsFragment = new MyFragment();
        args.putString("param1", param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public MyCenterPresenter createPresenter() {
        return new MyCenterPresenter();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mMyCenterAdapter = new MyCenterAdapter(mDataActivityBeanList, getActivity());
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.head_my, null);
        mMyCenterAdapter.addHeaderView(headView);

        mRefreshMylayout.setEnableAutoLoadMore(false);

        mXrvMy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mXrvMy.setAdapter(mMyCenterAdapter);

        initFab();
    }

    private void initFab() {
        mFabMenu.setupWithDimmingView(mDimmingView, Color.parseColor("#42000000"));

    }

    @Override
    public void initData() {
        mPresenter.getDateActivity();
    }


    @Override
    public void loadNullData() {


    }

    @Override
    public void loadData(List<DataActivityBean> dataActivityBeanList) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_drag, R.id.fab_document, R.id.fab_spreadsheet, R.id.fab_presentation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_drag:
                break;
            case R.id.fab_document:

                break;
            case R.id.fab_spreadsheet:
                break;
            case R.id.fab_presentation:
                break;
            default:
                break;
        }
    }
}
