package com.example.hasee.utils;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by TT on 2016/12/29.
 */

public class FrescoUtils {


    private static ImageRequest sRequest;

    public static void setController(String imageurl, SimpleDraweeView simpleDraweeView) {
        Uri uri = Uri.parse(imageurl);


        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .setOldController(simpleDraweeView.getController())
//                .setControllerListener(listener)
                .build();


        simpleDraweeView.setController(controller);
    }


    public static void setController(String imageurl, SimpleDraweeView simpleDraweeView, int level) {
        Uri uri = Uri.parse(imageurl);


        /**
         *  检查内存缓存，有如，立刻返回。这个操作是实时的。
            检查未解码的图片缓存，如有，解码并返回。
             检查磁盘缓存，如果有加载，解码，返回。
            下载或者加载本地文件
         */
        switch (level) {
            case 1:
                sRequest = ImageRequestBuilder
                        .newBuilderWithSource(uri)
                        .setAutoRotateEnabled(true)
                        .setLocalThumbnailPreviewsEnabled(true)
                        .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                        .setProgressiveRenderingEnabled(false)
                        .build();
                break;
            case 2:
                sRequest = ImageRequestBuilder
                        .newBuilderWithSource(uri)
                        .setAutoRotateEnabled(false)//自动旋转
                        .setLocalThumbnailPreviewsEnabled(true)//设置本地缩略图预览
                        .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.DISK_CACHE)//缓存等级
                        .setProgressiveRenderingEnabled(false)//是否支持渐进式加载.
                        .build();
                break;
            case 3:
                sRequest = ImageRequestBuilder
                        .newBuilderWithSource(uri)
                        .setAutoRotateEnabled(true)
                        .setLocalThumbnailPreviewsEnabled(true)
                        .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE)
                        .setProgressiveRenderingEnabled(false)
                        .build();
                break;
            case 4:
                sRequest = ImageRequestBuilder
                        .newBuilderWithSource(uri)
                        .setAutoRotateEnabled(true)
                        .setLocalThumbnailPreviewsEnabled(true)
                        .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE)
                        .setProgressiveRenderingEnabled(false)
                        .build();
                break;
            default:
                break;

        }



        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(sRequest)
//                .setControllerListener(listener)
                .build();


        simpleDraweeView.setController(controller);
    }
}
