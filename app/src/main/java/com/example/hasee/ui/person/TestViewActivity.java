package com.example.hasee.ui.person;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hasee.R;
import com.example.hasee.utils.ContextUtils;

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
    private View head_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        ButterKnife.bind(this);
        inite();
        initeData();
    }

    private int k = 50;

    private void initeData() {
        for (int i = 0; i < k; i++) {
            list.add("这是  " + i + "  条测试的数据");
        }
        testRvAdapter.notifyDataSetChanged();
    }

    private int totalDy = 0;
    private float xishu = 0.8f;

    private void inite() {

        zongchang = ContextUtils.dip2px(TestViewActivity.this, 100);
        neimian = ContextUtils.dip2px(TestViewActivity.this, 50);
        jiexian = (zongchang - neimian);
        Log.e("滑动的总体高度==", zongchang + "");
        Log.e("滑动的自身的高度==", neimian + "");
        Log.e("滑动的可移动间隙==", jiexian + "");
        rvTest.setLayoutManager(new LinearLayoutManager(this));
        testRvAdapter = new TestRvAdapter(list);
        rvTest.setAdapter(testRvAdapter);
        head = LayoutInflater.from(this).inflate(R.layout.head_test, (ViewGroup) rvTest.getParent(), false);
        head_one = LayoutInflater.from(this).inflate(R.layout.head_my, (ViewGroup) rvTest.getParent(), false);
        linearLayout = (LinearLayout) head.findViewById(R.id.ll_change);
        testRvAdapter.addHeaderView(head);

        rvTest.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             *
             * @param recyclerView
             * @param newState  0 停止  1 手指滑动 2 快速滚动中
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.e("滑动的状态==",newState+"");
                if(newState == 0){
                    if(shiji != 0){

                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalDy -= dy;
                dealView(totalDy, dy);
            }
        });


    }

    private int zongchang = 0;
    private int neimian = 0;
    private int jiexian = 0;
    private float shiji = 0.0f;

    /**
     * 整体高度  100dp
     * 内部高度  50dp
     * 到底部需要距离  25dp
     * 卡到一半在外面  25dp + 内部高度/2 = 50
     *
     * @param totalDy
     * @param dy
     */
    private void dealView(float totalDy, int dy) {
        totalDy = Math.abs(totalDy);
        totalDy = totalDy / xishu;
        Log.e("滑动的距离==", totalDy + "");
        if (totalDy == 0) {
            linearLayout.setTranslationY(0.0f);
            shiji = 0;
            return;
        }
        if (dy > 0) {
            Log.e("滑动的方向==", "         向下");
            if (totalDy > 0 && totalDy <= jiexian) {
                linearLayout.setTranslationY(totalDy);
                shiji = totalDy;
            } else if (totalDy > jiexian) {
                linearLayout.setTranslationY(jiexian);
                shiji = jiexian;
            } else {
            }
        } else {
            Log.e("滑动的方向==", "         向上");
            if (totalDy > 0 && totalDy <= jiexian) {
                linearLayout.setTranslationY(totalDy);
            } else if (totalDy > jiexian) {
                linearLayout.setTranslationY(jiexian);
            } else {
            }
        }

    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
