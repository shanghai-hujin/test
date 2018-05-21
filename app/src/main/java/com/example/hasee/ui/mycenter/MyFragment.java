package com.example.hasee.ui.mycenter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.ui.adpater.MyCenterAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by HASEE on 2018/4/29.
 */

public class MyFragment extends BaseFragment<MyCenterPresenter> implements MyCenterContract.MyCenterView {

    @BindView(R.id.xrv_my)
    RecyclerView mXrvMy;
    @BindView(R.id.refresh_mylayout)
    SmartRefreshLayout mRefreshMylayout;
    Unbinder unbinder;

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
        MyCenterAdapter myCenterAdapter = new MyCenterAdapter(null,getActivity());



    }

    @Override
    public void initData() {

    }



}
