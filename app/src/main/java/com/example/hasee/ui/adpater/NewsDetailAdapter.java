package com.example.hasee.ui.adpater;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hasee.R;
import com.example.hasee.bean.NewsDetail;
import com.example.hasee.utils.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/8 15:37
 */

public class NewsDetailAdapter extends BaseMultiItemQuickAdapter<NewsDetail.ItemBean, BaseViewHolder> {
    private Context mContext;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NewsDetailAdapter(List<NewsDetail.ItemBean> data, Context context) {
        super(data);
        this.mContext = context;
        addItemType(NewsDetail.ItemBean.TYPE_DOC_TITLEIMG, R.layout.item_detail_doc);
        addItemType(NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG, R.layout.item_detail_doc_slideimg);
        addItemType(NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG, R.layout.item_detail_advert);
        addItemType(NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG, R.layout.item_detail_advert_slideimg);
        addItemType(NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG, R.layout.item_detail_advert_longimage);
        addItemType(NewsDetail.ItemBean.TYPE_SLIDE, R.layout.item_detail_slide);
        addItemType(NewsDetail.ItemBean.TYPE_PHVIDEO, R.layout.item_detail_phvideo);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsDetail.ItemBean item) {
       /* ViewHelper.setScaleX(baseViewHolder.itemView, 0.6f);
        ViewHelper.setScaleY(baseViewHolder.itemView, 0.6f);
        ViewPropertyAnimator.animate(baseViewHolder.itemView).scaleX(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
        ViewPropertyAnimator.animate(baseViewHolder.itemView).scaleY(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();*/
        Logger.e("TAG","类型="+baseViewHolder.getItemViewType()+"");
        switch (baseViewHolder.getItemViewType()) {

            case NewsDetail.ItemBean.TYPE_DOC_TITLEIMG:
                baseViewHolder.setText(R.id.tv_title,item.getTitle());
                baseViewHolder.setText(R.id.tv_source,item.getSource());
                baseViewHolder.setText(R.id.tv_commnetsize,  String.format(mContext.getResources().getString(R.string.news_commentsize), item.getCommentsall()));
                FrescoUtils.setController(item.getThumbnail(),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_logo)));
                baseViewHolder.addOnClickListener(R.id.iv_logo);
                break;
            case NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG:
                baseViewHolder.setText(R.id.tv_title, item.getTitle());
                baseViewHolder.setText(R.id.tv_source, item.getSource());
                baseViewHolder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_commentsize), item.getCommentsall()));
                try {
                    FrescoUtils.setController(item.getStyle().getImages().get(0),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_1)));
                    FrescoUtils.setController(item.getStyle().getImages().get(1),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_2)));
                    FrescoUtils.setController(item.getStyle().getImages().get(2),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_3)));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG:
                baseViewHolder.setText(R.id.tv_title, item.getTitle());
                FrescoUtils.setController(item.getThumbnail(),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_logo)));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG:
                baseViewHolder.setText(R.id.tv_title, item.getTitle());
                try {
                    FrescoUtils.setController(item.getStyle().getImages().get(0),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_1)));
                    FrescoUtils.setController(item.getStyle().getImages().get(1),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_2)));
                    FrescoUtils.setController(item.getStyle().getImages().get(2),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_3)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG:
                baseViewHolder.setText(R.id.tv_title, item.getTitle());
                FrescoUtils.setController(item.getThumbnail(),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_logo)));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_SLIDE:
                baseViewHolder.setText(R.id.tv_title, item.getTitle());
                baseViewHolder.setText(R.id.tv_source, item.getSource());
                baseViewHolder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_commentsize), item.getCommentsall()));
                FrescoUtils.setController(item.getThumbnail(),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_logo)));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsDetail.ItemBean.TYPE_PHVIDEO:
                baseViewHolder.setText(R.id.tv_title, item.getTitle());
                baseViewHolder.setText(R.id.tv_source, item.getSource());
                baseViewHolder.setText(R.id.tv_commnetsize,
                        String.format(mContext.getResources().getString(R.string.news_commentsize), item.getCommentsall()));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                FrescoUtils.setController(item.getThumbnail(),((SimpleDraweeView)baseViewHolder.getView(R.id.iv_logo)));
                break;
            default:
                break;
        }
    }
}
