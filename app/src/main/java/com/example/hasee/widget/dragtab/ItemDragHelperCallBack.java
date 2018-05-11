package com.example.hasee.widget.dragtab;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/11 10:43
 */

public class ItemDragHelperCallBack extends ItemTouchHelper.Callback {

    private OnChannelListener onChannelListener;

    public ItemDragHelperCallBack(OnChannelListener onChannelListener) {
        this.onChannelListener = onChannelListener;
    }

    public void setOnChannelListener(OnChannelListener onChannelListener) {
        this.onChannelListener = onChannelListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int dragFlags;
        if (layoutManager instanceof GridLayoutManager || layoutManager instanceof StaggeredGridLayoutManager) {
            //监听上下左右拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            //只能上下拖动监听
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //不同type之间不能移动
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        if (onChannelListener != null) {
            onChannelListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }

        return true;
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //不在闲置状态
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            if(viewHolder instanceof OnDragVHListener){
                OnDragVHListener itemViewHolder = ((OnDragVHListener) viewHolder);
                itemViewHolder.onItemSelected();
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder){
        if(viewHolder instanceof OnDragVHListener){
            OnDragVHListener itemViewHolder = (OnDragVHListener) viewHolder;
            itemViewHolder.onItemFinish();
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        //不需要长按拖动，标题，和推荐频道不能拖动
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        //不需要侧滑
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
