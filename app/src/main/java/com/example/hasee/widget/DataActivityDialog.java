package com.example.hasee.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.R;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/22 16:18
 */

public class DataActivityDialog extends DialogFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if(dialog != null){
            //添加动画
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }

        return inflater.inflate(R.layout.dialog_data, null);
    }
}
