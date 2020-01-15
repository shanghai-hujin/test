package com.example.hasee.news.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.common.net.bean.response.WanWenZhangResponse;
import com.example.hasee.news.R;

import java.util.List;
import java.util.Random;

public class NewsListAdapter extends BaseQuickAdapter<WanWenZhangResponse.DatasBean, BaseViewHolder> {


    public NewsListAdapter(@Nullable List<WanWenZhangResponse.DatasBean> data) {
        super(R.layout.item_new_wenzhang, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, WanWenZhangResponse.DatasBean item) {
        int position = baseViewHolder.getAdapterPosition();
        switch (position % 4) {
            case 0:
                baseViewHolder.setBackgroundRes(R.id.card_item, R.drawable.item_bank_orange);
                break;
            case 1:
                baseViewHolder.setBackgroundRes(R.id.card_item, R.drawable.item_bank_red);

                break;
            case 2:
                baseViewHolder.setBackgroundRes(R.id.card_item, R.drawable.item_bank_bule);

                break;
            case 3:
                baseViewHolder.setBackgroundRes(R.id.card_item, R.drawable.item_bank_green);
                break;
            default:
                baseViewHolder.setBackgroundRes(R.id.card_item, R.drawable.item_bank_orange);

                break;
        }

        int nextInt = new Random().nextInt(6);
        switch (nextInt) {
            case 0:
                baseViewHolder.setImageResource(R.id.img_news_head,R.drawable.icon_head_one);
                break;
            case 1:
                baseViewHolder.setImageResource(R.id.img_news_head,R.drawable.icon_head_two);
                break;
            case 2:
                baseViewHolder.setImageResource(R.id.img_news_head,R.drawable.icon_head_three);
                break;
            case 3:
                baseViewHolder.setImageResource(R.id.img_news_head,R.drawable.icon_head_four);
                break;
            case 4:
                baseViewHolder.setImageResource(R.id.img_news_head,R.drawable.icon_head_five);
                break;
            case 5:
                baseViewHolder.setImageResource(R.id.img_news_head,R.drawable.icon_head_six);
                break;
            default:
                baseViewHolder.setImageResource(R.id.img_news_head,R.drawable.icon_head_six);
                break;
        }

        baseViewHolder.setText(R.id.tv_news_chapter, item.getSuperChapterName())
                .setText(R.id.tv_news_share_user, TextUtils.isEmpty(item.getShareUser())?"无面者":item.getShareUser())
                .setText(R.id.tv_news_share_time,item.getNiceShareDate())
                .setText(R.id.tv_news_title, item.getTitle());

    }


}
