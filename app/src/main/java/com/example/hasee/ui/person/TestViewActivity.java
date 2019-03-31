package com.example.hasee.ui.person;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hasee.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestViewActivity extends AppCompatActivity {

    @BindView(R.id.rv_test)
    RecyclerView rvTest;

    private List<String> list = new ArrayList<>();
    private TestRvAdapter testRvAdapter;
    private View head;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        ButterKnife.bind(this);
        inite();
        initeData();
    }

    private  int k = 50;
    private void initeData() {
        for (int i = 0; i < k; i++) {
            list.add("这是  "+i+"  条测试的数据");
        }
        testRvAdapter.notifyDataSetChanged();
    }

    private void inite() {
        rvTest.setLayoutManager(new LinearLayoutManager(this));
        testRvAdapter = new TestRvAdapter(list);
        rvTest.setAdapter(testRvAdapter);
        head = LayoutInflater.from(this).inflate(R.layout.head_test, (ViewGroup) rvTest.getParent(), false);
        linearLayout = (LinearLayout) head.findViewById(R.id.ll_change);
        testRvAdapter.addHeaderView(head);

    }
}
