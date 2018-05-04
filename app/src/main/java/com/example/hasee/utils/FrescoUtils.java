package com.example.hasee.utils;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by TT on 2016/12/29.
 */

public class FrescoUtils {


    public static void setController(String imageurl, SimpleDraweeView simpleDraweeView){
        Uri uri= Uri.parse(imageurl);


        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .setOldController(simpleDraweeView.getController())
//                .setControllerListener(listener)
                .build();


        simpleDraweeView.setController(controller);
    }
}
