package com.example.hasee.ui.book;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.ui.adpater.MainViewHolder;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TestXidingActivity extends Activity {

    private RecyclerView recyclerView;
    private List<String> testList = new ArrayList<>();


   /* recyclerView.setOnTouchListener(new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                isRecycleTouch = true;

            }
            return false;
        }
    });

        headView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (!isRecycleTouch) {
                RecycleViewUtils.smoothMoveToPosition(recyclerView, tab.getPosition());


            }
            scrollView.scrollBy(0, CommonUtil.dp2px(SolutionActivity.this, 350));

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == 0) {
                isRecycleTouch = false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = layoutManager.findFirstVisibleItemPosition();
            if (isRecycleTouch) {
                headView.getTabAt(position).select();
                headView.setScrollPosition(position, 0f, true);
            }
        }
    });*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_xiding);

        recyclerView = (RecyclerView) findViewById(R.id.rv_test);


        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        recyclerView.setRecycledViewPool(viewPool);

        viewPool.setMaxRecycledViews(0, 30);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        recyclerView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        adapters.add(new SubAdapter(this,new LinearLayoutHelper(), 30,
                R.layout.item_test_three){

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {

            }
        });

        adapters.add(new SubAdapter(this,new StickyLayoutHelper(), 1,
                R.layout.item_tab){

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                TabLayout tabLayout = (TabLayout) holder.getView(R.id.tb_rv);
                List<String> titleList = new ArrayList<>();
                for(int i =0; i<30; i++){
                    titleList.add("tab"+i);
                }
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                for (String title : titleList) {
                    tabLayout.addTab(tabLayout.newTab().setText(title));
                }

                /*headView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        if (!isRecycleTouch) {
                            RecycleViewUtils.smoothMoveToPosition(recyclerView, tab.getPosition());


                        }
                        scrollView.scrollBy(0, CommonUtil.dp2px(SolutionActivity.this, 350));

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });*/
            }
        });

        adapters.add(new SubAdapter(this,new LinearLayoutHelper(), 30,
                R.layout.item_test_three){

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {

            }
        });



        delegateAdapter.setAdapters(adapters);
    }
}
