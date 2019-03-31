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

    @SuppressLint("ResourceType")
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_test_item, item);

        helper.setBackgroundRes(R.id.ll_item_test, Color.parseColor("#"+getRandColorCode()));
    }


    public  String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        Log.e("随机颜色==",r+g+b);
        return r + g + b;
    }
}
