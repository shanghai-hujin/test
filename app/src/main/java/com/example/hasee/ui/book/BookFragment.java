package com.example.hasee.ui.book;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.di.component.ApplicationComponent;
import com.example.hasee.ui.base.BaseFragment;

/**
 * Created by HASEE on 2018/4/29.
 */

public class BookFragment extends BaseFragment {
    private View mRootView;

    public static BookFragment newInstance(String param1){
        Bundle args = new Bundle();
        BookFragment newsFragment = new BookFragment();
        args.putString("param1",param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }



    @Override
    public int getContentLayout() {
        return R.layout.activity_book;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {

    }


}
