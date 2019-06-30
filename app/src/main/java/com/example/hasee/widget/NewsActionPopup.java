//package com.example.hasee.widget;
//
//import android.content.Context;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.chad.library.adapter.base.listener.OnItemClickListener;
//import com.example.hasee.R;
//import com.flyco.dialog.widget.popup.base.BasePopup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * Demo ${CLASS}
// *
// * @author TT
// * @date 2018/5/10 14:59
// */
//
//public class NewsActionPopup extends BasePopup<NewsActionPopup> {
//    @BindView(R.id.iv_top)
//    ImageView mIvTop;
//    @BindView(R.id.recyclerView)
//    RecyclerView mRecyclerView;
//    @BindView(R.id.tv_nolike)
//    TextView mTvNolike;
//    @BindView(R.id.iv_down)
//    ImageView mIvDown;
//
//    private List<String> mBrackAction;
//    private int position;
//    List<Integer> selectid;
//    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
//
//    public NewsActionPopup(Context context) {
//        super(context);
//    }
//
//    /**
//     * 设置参数
//     * @param backAction item集合
//     * @param isTop 是否在上面
//     * @param position 位置
//     * @return
//     */
//    public NewsActionPopup setBackAction(List<String> backAction, boolean isTop, int position){
//        this.mBrackAction = backAction;
//        this.position = position;
//        selectid = new ArrayList<>();
//        selectid.clear();
//        if(mAdapter != null){
//            mAdapter.setNewData(backAction);
//        }
//
//        if(isTop){
//            mIvTop.setVisibility(View.GONE);
//            mIvDown.setVisibility(View.VISIBLE);
//        }else {
//            mIvTop.setVisibility(View.VISIBLE);
//            mIvDown.setVisibility(View.GONE);
//        }
//
//
//        return this;
//    }
//
//    private onClickListener mOnClickListener;
//
//    @OnClick(R.id.tv_nolike)
//    public void onViewClicked() {
//        //传进来，返回去
//        mOnClickListener.onClick(position);
//    }
//
//    public interface onClickListener {
//        void onClick(int position);
//    }
//
//    public void setOnClickListener(onClickListener onClickListener) {
//        mOnClickListener = onClickListener;
//    }
//
//
//
//    @Override
//    public View onCreatePopupView() {
//        View inflate = View.inflate(mContext, R.layout.popup_newsdel, null);
//        ButterKnife.bind(this, inflate);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
//        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_newsdelpop_del,mBrackAction) {
//            @Override
//            protected void convert(BaseViewHolder helper, String item) {
//                helper.setText(R.id.tv_backreason,item);
//                if (selectid.contains(helper.getAdapterPosition())) {
//                    helper.getView(R.id.tv_backreason).setBackground(mContext.getResources().getDrawable(R.drawable.delpop_tv_selected_bg));
//                    helper.setTextColor(R.id.tv_backreason, mContext.getResources().getColor(android.R.color.holo_red_light));
//                } else {
//                    helper.getView(R.id.tv_backreason).setBackground(mContext.getResources().getDrawable(R.drawable.delpop_tv_bg));
//                    helper.setTextColor(R.id.tv_backreason, mContext.getResources().getColor(android.R.color.black));
//                }
//            }
//        };
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (selectid.contains(position)) {
//                    selectid.remove((Object) position);
//                } else {
//                    selectid.add(position);
//                }
//                if (selectid.size()>0){
//                    mTvNolike.setText("确定");
//                }else {
//                    mTvNolike.setText("不感兴趣");
//                }
//                adapter.notifyItemChanged(position);
//            }
//        });
//        return inflate;
//    }
//
//    @Override
//    public void setUiBeforShow() {
//
//    }
//}
