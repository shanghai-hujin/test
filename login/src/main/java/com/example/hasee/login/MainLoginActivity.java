package com.example.hasee.login;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.common.base.ui.BaseActivity;

public class MainLoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_login;
    }

    @Override
    public View getView() {
        return super.getView();
    }


    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.tv_finisn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAnim(true);
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
