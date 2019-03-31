package com.example.hasee.ui.person;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.R;

import java.util.List;
import java.util.Random;

/**
 * Created by HASEE on 2019/3/31.
 */

public class TestRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TestRvAdapter(@Nullable List<String> data) {
        super(R.layout.item_test_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_test_item, item);

    }

}
