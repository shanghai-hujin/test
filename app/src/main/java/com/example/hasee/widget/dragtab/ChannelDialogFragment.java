package com.example.hasee.widget.dragtab;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hasee.R;
import com.example.hasee.bean.Channel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HASEE on 2018/5/10.
 */

public class ChannelDialogFragment extends DialogFragment implements OnChannelListener  {

    private RecyclerView recyclerView;
    private ImageView ivClose;

    private OnChannelListener onChannelListener;

    public void setOnChannelListener(OnChannelListener onChannelListener) {
        this.onChannelListener = onChannelListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Black_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }
        return inflater.inflate(R.layout.dialog_channel, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ivClose = (ImageView)view.findViewById(R.id.icon_collapse);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        processLogic();
    }

    private void processLogic() {

    }

    public static ChannelDialogFragment newInstance(List<Channel> selectedDatas,List<Channel> unableSelectedDatas) {

        Bundle args = new Bundle();
        args.putSerializable("dataSelected", ((Serializable) selectedDatas));
        args.putSerializable("dataUnselected", ((Serializable) unableSelectedDatas));
        ChannelDialogFragment fragment = new ChannelDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemMove(int starPos, int endPos) {

    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {

    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {

    }

    @Override
    public void onFinish(String selectedChannelName) {

    }
}
