package com.example.hasee.widget;

import android.content.Context;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/9 14:47
 */

public abstract class SimpleImageView implements ImageLoaderInterface {
    @Override
    public ImageView createImageView(Context context) {
        SimpleDraweeView imageView = new SimpleDraweeView(context);
        return imageView;
    }
}
