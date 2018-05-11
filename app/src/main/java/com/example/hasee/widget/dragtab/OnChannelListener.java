package com.example.hasee.widget.dragtab;

/**
 * Created by HASEE on 2018/5/10.
 */

public interface OnChannelListener {
    void onItemMove (int starPos,int endPos);
    void onMoveToMyChannel(int starPos, int endPos);
    void onMoveToOtherChannel(int starPos, int endPos);
    void onFinish(String selectedChannelName);

}
