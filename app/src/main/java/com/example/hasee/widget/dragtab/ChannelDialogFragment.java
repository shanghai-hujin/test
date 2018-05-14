package com.example.hasee.widget.dragtab;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hasee.R;
import com.example.hasee.bean.Channel;
import com.example.hasee.ui.adpater.NewChannelAdapter;
import com.example.hasee.utils.Event;
import com.example.hasee.utils.RxBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HASEE on 2018/5/10.
 */

public class ChannelDialogFragment extends DialogFragment implements OnChannelListener {

    private RecyclerView recyclerView;
    private ImageView ivClose;
    private List<Channel> mDatas = new ArrayList<>();
    private OnChannelListener onChannelListener;
    private List<Channel> mDataSelected;
    private List<Channel> mDataUnselected;
    private NewChannelAdapter mNewChannelAdapter;
    private boolean isUpdate;

    public void setOnChannelListener(OnChannelListener onChannelListener) {
        this.onChannelListener = onChannelListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }
        return inflater.inflate(R.layout.dialog_channel, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ivClose = (ImageView) view.findViewById(R.id.icon_collapse);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        processLogic();
    }

    private void processLogic() {
        //我的频道标题
        Channel channel = new Channel();
        channel.setItemtype(Channel.TYPE_MY);
        channel.setChannelName("我的频道");
        mDatas.add(channel);
        //我的频道item
        Bundle bundle = getArguments();
        mDataSelected = (List<Channel>) bundle.getSerializable("dataSelected");
        mDataUnselected = (List<Channel>) bundle.getSerializable("dataUnselected");
        setDataType(mDataSelected, Channel.TYPE_MY_CHANNEL);
        setDataType(mDataUnselected, Channel.TYPE_OTHER_CHANNEL);
        mDatas.addAll(mDataSelected);
        //其他频道标题
        Channel moreChannel = new Channel();
        moreChannel.setItemtype(Channel.TYPE_OTHER);
        moreChannel.setChannelName("频道推荐");
        mDatas.add(moreChannel);
        //其他频道item
        mDatas.addAll(mDataUnselected);
        //重写滑动帮助类，并绑定recyleview
        ItemDragHelperCallBack callBack = new ItemDragHelperCallBack(this);
        final ItemTouchHelper helper = new ItemTouchHelper(callBack);
        helper.attachToRecyclerView(recyclerView);

        mNewChannelAdapter = new NewChannelAdapter(mDatas, helper);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mNewChannelAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mNewChannelAdapter.getItemViewType(position);
                return itemViewType == Channel.TYPE_MY_CHANNEL || itemViewType == Channel.TYPE_OTHER_CHANNEL ? 1 : 4;
            }
        });

        mNewChannelAdapter.setOnChannelListener(this);
    }

    /**
     * 设置类型 我的---其他
     *
     * @param datas
     * @param typeOtherChannel
     */
    private void setDataType(List<Channel> datas, int typeOtherChannel) {
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setItemtype(typeOtherChannel);
        }
    }

    public static ChannelDialogFragment newInstance(List<Channel> selectedDatas, List<Channel> unableSelectedDatas) {

        Bundle args = new Bundle();
        args.putSerializable("dataSelected", ((Serializable) selectedDatas));
        args.putSerializable("dataUnselected", ((Serializable) unableSelectedDatas));
        ChannelDialogFragment fragment = new ChannelDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        if(starPos < 0 || endPos < 0){
            return;
        }

        if(mDatas.get(endPos).getChannelName().equals("头条")){
            return;
        }

        if(onChannelListener != null){
            //去除标题所占的一个index
            onChannelListener.onItemMove(starPos-1 ,endPos-1);
        }
        onMove(starPos, endPos, false);
    }

    private String firstAddChannelName = "";

    private void onMove(int starPos, int endPos, boolean isAdd) {
        try {
            isUpdate = true;
            Channel startChannel = mDatas.get(starPos);
            //先删除之前的位置
            mDatas.remove(starPos);
            //添加到现在的位置
            mDatas.add(endPos,startChannel);
            mNewChannelAdapter.notifyItemMoved(starPos, endPos);
            if(isAdd){
                if(TextUtils.isEmpty(firstAddChannelName)){
                    firstAddChannelName = startChannel.getChannelName();
                }
            }else {
                if(startChannel.getChannelName().equals(firstAddChannelName)){
                    firstAddChannelName = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        onMove(starPos, endPos, true);
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        onMove(starPos, endPos, false);
    }

    @Override
    public void onFinish(String selectedChannelName) {
        RxBus.INSTANCE.post(new Event.SelectChannelEvent(selectedChannelName));
        dismiss();
    }

    @Override
    public void onPause() {
        if(isUpdate){
            RxBus.INSTANCE.post(new Event.NewChannelEvent(mNewChannelAdapter.getData(), firstAddChannelName));
        }
        super.onPause();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
