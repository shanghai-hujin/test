package com.example.hasee.news.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.common.net.bean.response.WanWenZhangResponse;
import com.example.hasee.news.R;

import java.util.List;

public class NewsListAdapter extends BaseQuickAdapter<WanWenZhangResponse.DatasBean, BaseViewHolder> {


    public NewsListAdapter(@Nullable List<WanWenZhangResponse.DatasBean> data) {
        super(R.layout.item_new_wenzhang, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, WanWenZhangResponse.DatasBean item) {
        int position = baseViewHolder.getAdapterPosition();
        switch (position % 4) {
            case 0:
                baseViewHolder.setBackgroundRes(R.id.card_item,R.drawable.item_bank_orange);
                break;
            case 1:
                baseViewHolder.setBackgroundRes(R.id.card_item,R.drawable.item_bank_red);

                break;
            case 2:
                baseViewHolder.setBackgroundRes(R.id.card_item,R.drawable.item_bank_bule);

                break;
            case 3:
                baseViewHolder.setBackgroundRes(R.id.card_item,R.drawable.item_bank_green);
                break;
            default:
                baseViewHolder.setBackgroundRes(R.id.card_item,R.drawable.item_bank_orange);

                break;
        }
        baseViewHolder.setText(R.id.tv_news_chapter, item.getSuperChapterName())
                .setText(R.id.tv_news_title, item.getTitle());
    }


}
