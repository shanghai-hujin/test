package com.example.hasee.common.weight.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.hasee.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * banner方法抽取 公用方法 是否用static？ 不占用资源可以用
 * Created by 马振雷 on 2018/3/23.
 */

public class ShowBanner {


    private static boolean sIsTwoSizeAccount = false;


    @SuppressLint("NewApi")
    public static void showWealthBanner(List<String> bannerUrlList,
            final Context context, BannerView bannerView) {

        sIsTwoSizeAccount = false;
        //banner有数据集合不等于0

        bannerView.setPaddingLeftOrRight(R.dimen.dimen_5dp);
        bannerView.setPaddingBottomDistance(R.dimen.dimen_1_halfdp);

        bannerView.setVisibility(View.VISIBLE);
        List<View> viewList = new ArrayList<>();
        //banner循环轮播在数据为2条数据的时候切换会出现空白页，判断为2条数据时默认加上
        if (bannerUrlList.size() == 2) {
            sIsTwoSizeAccount = true;
            bannerUrlList.add(2, bannerUrlList.get(0));
            bannerUrlList.add(3, bannerUrlList.get(1));

        }
        viewList.clear();
        for (int i = 0; i < bannerUrlList.size(); i++) {
            @SuppressLint("InflateParams")
            View view = LayoutInflater.from(context).inflate(R.layout.banner_vidio, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.img_banner);
         //   PlayerView playerView = (PlayerView) view.findViewById(R.id.pv_banner);

         //   GlideApp.with(context).load(bannerUrlList.get(i)).into(imageView);
            viewList.add(view);
        }
        bannerView.startLoop(true);
        bannerView.setViewList(viewList, sIsTwoSizeAccount);
        bannerView.setVisibility(View.VISIBLE);
    }


}
