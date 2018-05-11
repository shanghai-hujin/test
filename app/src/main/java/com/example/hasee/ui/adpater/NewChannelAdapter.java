package com.example.hasee.ui.adpater;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.R;
import com.example.hasee.bean.Channel;
import com.example.hasee.widget.dragtab.OnChannelListener;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/11 11:44
 */

public class NewChannelAdapter extends BaseMultiItemQuickAdapter<Channel, BaseViewHolder> {


    private boolean mIsEdit;
    private ItemTouchHelper mItemTouchHelper;
    private RecyclerView mRecylerView;
    private long mStartTime;
    // touch 间隔时间  用于分辨是否是 "点击"
    private static final long SPACE_TIME = 100;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NewChannelAdapter(List<Channel> data, ItemTouchHelper helper) {
        super(data);
        mIsEdit = false;
        this.mItemTouchHelper = helper;
        //分别为：我的频道标题  -->  我的频道item  -->  推荐频道标题  -->  推荐频道item
        addItemType(Channel.TYPE_MY, R.layout.item_channel_title);
        addItemType(Channel.TYPE_MY_CHANNEL, R.layout.channel_rv_item);
        addItemType(Channel.TYPE_OTHER, R.layout.item_channel_title);
        addItemType(Channel.TYPE_OTHER_CHANNEL, R.layout.channel_rv_item);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mRecylerView = (RecyclerView) parent;
        return super.onCreateViewHolder(parent, viewType);
    }

    private OnChannelListener onChannelListener;

    public void setOnChannelListener(OnChannelListener onChannelListener) {
        this.onChannelListener = onChannelListener;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Channel channel) {
        switch (baseViewHolder.getItemViewType()) {
            case Channel.TYPE_MY:
                //给我的频道的标题配置
                baseViewHolder.setText(R.id.tvTitle, channel.getChannelName());
                baseViewHolder.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mIsEdit) {
                            startEditMode(true);
                        } else {
                            startEditMode(false);
                        }
                    }
                });
                baseViewHolder.getView(R.id.tv_sort).setTag(true);
                break;
            case Channel.TYPE_MY_CHANNEL:
                baseViewHolder.setText(R.id.tv_channelname, channel.getChannelName())
                        .setVisible(R.id.img_edit, mIsEdit)
                        .addOnClickListener(R.id.img_edit);

                //第一个不能动
                if (channel.getChannelType() != 1) {
                    baseViewHolder.getView(R.id.img_edit).setTag(true);
                    baseViewHolder.getView(R.id.tv_channelname).setTag(false);
                } else {
                    baseViewHolder.getView(R.id.tv_channelname).setTag(true);
                }

                baseViewHolder.getView(R.id.rl_channel).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (!mIsEdit) {
                            startEditMode(true);
                        }
                        mItemTouchHelper.startDrag(baseViewHolder);
                        return false;
                    }
                });

                baseViewHolder.getView(R.id.rl_channel).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (!mIsEdit) {
                            return false;
                        }
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                mStartTime = System.currentTimeMillis();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                if (System.currentTimeMillis() - mStartTime > SPACE_TIME) {
                                    //move事件与down事件的间隔时间必须大于>100ms,才认定为拖拽
                                    mItemTouchHelper.startDrag(baseViewHolder);
                                }
                                break;
                            case MotionEvent.ACTION_CANCEL:
                            case MotionEvent.ACTION_UP:
                                mStartTime = 0;
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });

                //单点是删除频道，移动到推荐频道
                baseViewHolder.getView(R.id.rl_channel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mIsEdit) {
                            //头条标签不能移除
                            if (channel.getChannelType() == 1) {
                                return;
                            }

                            int otherFirstPosition = getOtherFirstPosition();
                            int currentPosition = baseViewHolder.getAdapterPosition();
                            //获取到目标view
                            View targetView = mRecylerView.getLayoutManager().findViewByPosition(otherFirstPosition);
                            //获取当前需要移动的View
                            View currentView = mRecylerView.getLayoutManager().findViewByPosition(currentPosition);
                            // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                            // 如果在屏幕内,则添加一个位移动画
                            if (mRecylerView.indexOfChild(targetView) >= 0 && otherFirstPosition != -1) {
                                RecyclerView.LayoutManager manager = mRecylerView.getLayoutManager();
                                int spanCount = ((GridLayoutManager) manager).getSpanCount();
                                int targetX = targetView.getLeft();
                                int targetY = targetView.getTop();
                                int myChannelSize = getMyChannelSize();
                                if(myChannelSize % spanCount == 1){
                                    //最一行最移除后，高度变化
                                    targetY -= targetView.getHeight();
                                }

                                //我的频道 移动到 推荐频道第一个,改为推荐频道
                                channel.setItemtype(Channel.TYPE_OTHER_CHANNEL);
                                channel.setChannelSelect(false);

                                //移动到第一个其他频道的前面一个
                                if(onChannelListener != null){
                                    onChannelListener.onMoveToOtherChannel(currentPosition, otherFirstPosition-1);
                                    startAnimation(currentView, targetX, targetY);
                                }

                            }else {
                                channel.setItemtype(Channel.TYPE_OTHER_CHANNEL);//改为推荐频道类型
                                channel.setChannelSelect(false);
                                if (otherFirstPosition == -1) {
                                    otherFirstPosition = mData.size();
                                }
                                if (onChannelListener != null) {
                                    onChannelListener.onMoveToOtherChannel(currentPosition, otherFirstPosition - 1);
                                }
                            }
                        }else {
                            if(onChannelListener != null){
                                onChannelListener.onFinish(channel.getChannelName());
                            }
                        }
                    }
                });
                break;
            case Channel.TYPE_OTHER:
                //其他频道的头部
                baseViewHolder.setText(R.id.tvTitle, channel.getChannelName())
                        .setText(R.id.tv_sort, "点击添加频道")
                        .setVisible(R.id.tv_edit, false);
                baseViewHolder.getView(R.id.tv_sort).setTag(false);
                break;

            case Channel.TYPE_OTHER_CHANNEL:
                baseViewHolder.setText(R.id.tv_channelname, channel.getChannelName())
                        .setVisible(R.id.img_edit, false);
                baseViewHolder.getView(R.id.tv_channelname).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //我的频道，最后一个
                        int myLastPosition = getMyLastPosition();
                        //当前位置
                        int currentPosition = baseViewHolder.getAdapterPosition();
                        //获取目标view
                        View targetView = mRecylerView.getLayoutManager().findViewByPosition(myLastPosition);
                        //获取当前需要移动的view
                        View currentView = mRecylerView.getLayoutManager().findViewByPosition(currentPosition);

                        // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                        // 如果在屏幕内,则添加一个位移动画
                        if(mRecylerView.indexOfChild(targetView) >= 0 && myLastPosition != -1){
                            RecyclerView.LayoutManager manager = mRecylerView.getLayoutManager();
                            int spanCount = ((GridLayoutManager) manager).getSpanCount();
                            int targetX = targetView.getLeft() + targetView.getWidth();
                            int targetY = targetView.getTop();

                            int myChanneSize = getMyChannelSize();
                            if(myChanneSize % spanCount == 0){
                                //添加到我的频道后换行，
                                View lastFourthView = mRecylerView.getLayoutManager().findViewByPosition(getMyLastPosition() - 3);
//                                        View lastFourthView = mRecyclerView.getChildAt(getMyLastPosition() - 3);
                                targetX = lastFourthView.getLeft();
                                targetY = lastFourthView.getTop() + lastFourthView.getHeight();
                            }

                            //将推进频道移动到我的频道的最后一个,改成我的频道
                            channel.setItemtype(Channel.TYPE_MY_CHANNEL);
                            channel.setChannelSelect(true);

                            if(onChannelListener != null){
                                onChannelListener.onMoveToMyChannel(currentPosition, myLastPosition+1);
                            }
                            startAnimation(currentView, targetX, targetY);

                        }else {
                            channel.setItemtype(Channel.TYPE_MY_CHANNEL);
                            channel.setChannelSelect(true);

                            if (myLastPosition == -1) {
                                myLastPosition = 0;//我的频道没有了，改成0
                            }
                            if (onChannelListener != null) {
                                onChannelListener.onMoveToMyChannel(currentPosition, myLastPosition + 1);
                            }
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 添加需要移动的 镜像View
     */
    private ImageView addMirrorView(ViewGroup parent, View view) {
        view.destroyDrawingCache();
        //首先开启Cache图片 ，然后调用view.getDrawingCache()就可以获取Cache图片
        view.setDrawingCacheEnabled(true);
        ImageView mirrorView = new ImageView(view.getContext());
        //获取该view的Cache图片
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        mirrorView.setImageBitmap(bitmap);
        //销毁掉cache图片
        view.setDrawingCacheEnabled(false);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);//获取当前View的坐标
        int[] parenLocations = new int[2];
        mRecylerView.getLocationOnScreen(parenLocations);//获取RecyclerView所在坐标
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0);
        parent.addView(mirrorView, params);//在RecyclerView的Parent添加我们的镜像View，parent要是FrameLayout这样才可以放到那个坐标点
        return mirrorView;
    }

    private void startAnimation(View currentView, int targetX, int targetY) {
        final ViewGroup parent = (ViewGroup) mRecylerView.getParent();
        final ImageView mirrorView = addMirrorView(parent, currentView);
        TranslateAnimation animator = getTranslateAnimator(targetX - currentView.getLeft(), targetY - currentView.getTop());
        currentView.setVisibility(View.INVISIBLE);//暂时隐藏
        mirrorView.startAnimation(animator);
        animator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                parent.removeView(mirrorView);//删除添加的镜像View
                if (currentView.getVisibility() == View.INVISIBLE) {
                    currentView.setVisibility(View.VISIBLE);//显示隐藏的View
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 获取位移动画
     */
    private TranslateAnimation getTranslateAnimator(float targetX, float targetY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetX,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetY);
        // RecyclerView默认移动动画250ms 这里设置360ms 是为了防止在位移动画结束后 remove(view)过早 导致闪烁
        translateAnimation.setDuration(360);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    /**
     * 获取推荐频道列表的第一个position
     *
     * @return
     */
    private int getOtherFirstPosition() {
        //之前找到了第一个pos直接返回
//        if (mOtherFirstPosition != 0) return mOtherFirstPosition;
        for (int i = 0; i < mData.size(); i++) {
            Channel channel = (Channel) mData.get(i);
            if (Channel.TYPE_OTHER_CHANNEL == channel.getItemType()) {
                //找到第一个直接返回
                return i;
            }
        }
        return -1;
    }

    private void startEditMode(boolean isEdit) {
        mIsEdit = isEdit;
        int visibleChildCount = mRecylerView.getChildCount();

        for (int i = 0; i < visibleChildCount; i++) {
            View childAtView = mRecylerView.getChildAt(i);
            ImageView ivEdit = (ImageView) childAtView.findViewById(R.id.img_edit);
            TextView tvName = (TextView) childAtView.findViewById(R.id.tv_channelname);
            TextView tvEdit = (TextView) childAtView.findViewById(R.id.tv_edit);
            TextView tvSort = (TextView) childAtView.findViewById(R.id.tv_sort);

            if (ivEdit != null) {
                ivEdit.setVisibility(ivEdit.getTag() != null && isEdit ? View.VISIBLE : View.INVISIBLE);
            }

            if (tvName != null) {
                if (tvName.getTag() == null) {
                    return;
                }

                if (isEdit && ((boolean) tvName.getTag())) {
                    tvName.setTextColor(Color.GRAY);
                } else {
                    tvName.setTextColor(Color.BLACK);
                }
            }

            if (tvEdit != null) {
                if (isEdit) {
                    tvEdit.setText("完成");
                } else {
                    tvEdit.setText("编辑");
                }
            }

            if (tvSort != null) {
                if (!((boolean) tvSort.getTag())) {
                    return;
                }

                if (isEdit) {
                    tvSort.setText("拖动可以排序");
                } else {
                    tvSort.setText("点击可以进入频道");
                }
            }


        }
    }

    /**
     * 获取当前我的频道size大小
     * @return
     */
    public int getMyChannelSize() {
        int size = 0;
        for (int i = 0; i < mData.size(); i++) {
            Channel channel = (Channel) mData.get(i);
            if (channel.getItemType() == Channel.TYPE_MY_CHANNEL) {
                size++;
            }
        }
        return size;
    }

    /**
     * 我的频道最后一个的position
     *
     * @return
     */
    public int getMyLastPosition() {
        for (int i = mData.size() - 1; i > -1; i--) {
            Channel channel = (Channel) mData.get(i);
            if (Channel.TYPE_MY_CHANNEL == channel.getItemType()) {
                //找到第一个直接返回
                return i;
            }
        }
        return -1;
    }
}
