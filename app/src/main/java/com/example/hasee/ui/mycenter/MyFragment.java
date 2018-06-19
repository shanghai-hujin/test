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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.bean.DataActivityBean;
import com.example.hasee.bean.HistoryTodayBean;
import com.example.hasee.ui.adpater.MyCenterAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.utils.Event;
import com.example.hasee.utils.PerfectClickListener;
import com.example.hasee.utils.RxBus;
import com.example.hasee.widget.time.DataActivityDialog;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.animation.ZoomEnter.ZoomInLeftEnter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.MaterialDialog;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tr4android.support.extension.widget.FloatingActionMenu;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

    private List<DataActivityBean> mDataActivityBeanList = new ArrayList<>();
    private MyCenterAdapter mMyCenterAdapter;
    private TextView mTvHistory;
    private TextView mTvHistory2;
    private TextView mTvHistory3;
    private HistoryTodayBean mHistoryTodayBean;
    private List<HistoryTodayBean.ResultBean> mHistoryTodayBeanResult;

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

        mTvHistory = (TextView) headView.findViewById(R.id.id_my_text1);
        mTvHistory2 = (TextView) headView.findViewById(R.id.id_my_text2);
        mTvHistory3 = (TextView) headView.findViewById(R.id.id_my_text3);
        mTvHistory.setOnClickListener(mPerfectClickListener);
        mTvHistory2.setOnClickListener(mPerfectClickListener);
        mTvHistory3.setOnClickListener(mPerfectClickListener);

        mMyCenterAdapter.addHeaderView(headView);

        mRefreshMylayout.setEnableAutoLoadMore(false);

        mXrvMy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mXrvMy.setAdapter(mMyCenterAdapter);

        initFab();
    }

    PerfectClickListener mPerfectClickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.id_my_text1:
                    showLoadingDialog();
                    mPresenter.getHistoruOfToday();

                    break;
                case R.id.id_my_text2:
                    break;
                case R.id.id_my_text3:
                    break;
                default:
                    break;

            }
        }
    };

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
    public void onResume() {
        super.onResume();
        RxBus.INSTANCE.toFlowable(Event.DataActivityChange.class)
                .compose(bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Event.DataActivityChange>() {
                    @Override
                    public void accept(Event.DataActivityChange dataActivityChange) throws Exception {
                        if (dataActivityChange.isAddActivity) {
                            //更新data
                            mPresenter.getDateActivity();
                        }
                    }
                });
    }

    @Override
    public void loadData(List<DataActivityBean> dataActivityBeanList) {
        mMyCenterAdapter.setNewData(dataActivityBeanList);
    }

    @Override
    public void loadHittoryData(HistoryTodayBean historyTodayBean) {
        hideLoadingDialog();
        Logger.e(historyTodayBean.getMsg());
        mHistoryTodayBean = historyTodayBean;
        if (!historyTodayBean.getRetCode().equals("200")) {
            return;
        }
        mHistoryTodayBeanResult = historyTodayBean.getResult();

        historyListDialog();

    }

    private void historyListDialog() {
        ArrayList<String> itemList = new ArrayList<>();

        //玩点逼格高的...
        Flowable.just(mHistoryTodayBeanResult)
                .flatMap(new Function<List<HistoryTodayBean.ResultBean>, Publisher<HistoryTodayBean.ResultBean>>() {
                    @Override
                    public Publisher<HistoryTodayBean.ResultBean> apply(List<HistoryTodayBean.ResultBean> resultBeans) throws Exception {
                        return Flowable.fromIterable(resultBeans);
                    }
                }).subscribe(new Consumer<HistoryTodayBean.ResultBean>() {
            @Override
            public void accept(HistoryTodayBean.ResultBean h) throws Exception {
                itemList.add(h.getTitle());
            }
        });

        final ActionSheetDialog dialog = new ActionSheetDialog(getActivity(),
                itemList.toArray(new String[itemList.size()]), null);
        dialog.title(mHistoryTodayBeanResult.get(0).getMonth()+"月"+
                mHistoryTodayBeanResult.get(0).getDay()+"号,历史上有"+mHistoryTodayBeanResult.size()+"大事")
                .titleTextSize_SP(14.5f)
                .layoutAnimation(null)//
                .showAnim(new ZoomInLeftEnter())
                .cancelText("朕,不看了")
                .show();
    }

    private void historySingleDialog(String title, String leftBtn,
                                     String rightBtn, String content, int pos) {
        MaterialDialog materialDialog = new MaterialDialog(getActivity());
        materialDialog.content(content)
                .title(title)
                .btnText(leftBtn, rightBtn)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideBottomExit())
                .show();
        materialDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                if (pos == 0) {
                    //第一个
                    return;
                }
                materialDialog.dismiss();
                historySingleDialog(mHistoryTodayBeanResult.get(pos - 1).getTitle(),
                        "", "",
                        mHistoryTodayBeanResult.get(pos - 1).getEvent(), pos - 1);
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                if (pos == mHistoryTodayBeanResult.size() - 1) {
                    return;
                }
                materialDialog.dismiss();
                historySingleDialog(mHistoryTodayBeanResult.get(pos + 1).getTitle(),
                        "", "",
                        mHistoryTodayBeanResult.get(pos + 1).getEvent(), pos + 1);
            }
        });
    }


    @OnClick({R.id.iv_drag, R.id.fab_document, R.id.fab_spreadsheet, R.id.fab_presentation})
    public void onViewClicked(View view) {
        DataActivityDialog dataActivityDialog;
        switch (view.getId()) {
            case R.id.iv_drag:
                break;
            case R.id.fab_document:
                dataActivityDialog = DataActivityDialog.newInstance(0, "qita");
                dataActivityDialog.show(getChildFragmentManager(), "data");
                mFabMenu.collapse();
                break;
            case R.id.fab_spreadsheet:
                dataActivityDialog = DataActivityDialog.newInstance(1, "qita");
                dataActivityDialog.show(getChildFragmentManager(), "data");
                mFabMenu.collapse();
                break;
            case R.id.fab_presentation:
                dataActivityDialog = DataActivityDialog.newInstance(2, "qita");
                dataActivityDialog.show(getChildFragmentManager(), "data");
                mFabMenu.collapse();
                break;
            default:
                break;
        }
    }
}
