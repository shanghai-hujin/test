package com.example.hasee.news.hook;

import android.view.View;
import android.widget.Toast;

public class MyProxyListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),"hook了点击事件",Toast.LENGTH_LONG).show();
    }
}
