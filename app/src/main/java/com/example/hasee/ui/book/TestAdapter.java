package com.example.hasee.ui.book;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author 廖望
 * @date 2018/8/23 17:22
 */
public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ONE_ITEM = 1;
    public static final int TWO_ITEM = 2;
    private Context mContext;
    private List<String> testList = new ArrayList<>();

    public TestAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.testList = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        if(ONE_ITEM == viewType){
            View v = mInflater.inflate(R.layout.item_test,parent,false);
            holder = new TestViewHolder(v);
        }else{
            View v = mInflater.inflate(R.layout.item_test_two,parent,false);
            holder = new TwoViewHolder(v);
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof TestViewHolder){
            ((TestViewHolder) holder).mTextView.setText(testList.get(position));
        }else {

        }
    }



    @Override
    public int getItemViewType(int position) {
        if(position == 1){
            return ONE_ITEM;
        }else {
            return TWO_ITEM;
        }
    }


    @Override
    public int getItemCount() {
        return testList.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public TestViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item_test);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {

        TabLayout mTabLayout;

        public TwoViewHolder(View itemView) {
            super(itemView);
            mTabLayout = (TabLayout) itemView.findViewById(R.id.tl_test);
        }
    }
}
