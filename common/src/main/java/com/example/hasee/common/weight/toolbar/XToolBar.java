package com.example.hasee.common.weight.toolbar;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.common.R;

import java.util.HashMap;
import java.util.Map;

public class XToolBar {

    private final static Map<View, XToolBar> TITLE_MAP = new HashMap<>();


    private Builder builder;
    public XToolBar(View view, Builder builder) {
        this.builder = builder;
        XToolBarImpl  xToolBarImpl = new XToolBarImpl(view);
    }
    private static class XToolBarImpl{


        private  ImageView leftImg;
        private  ImageView rightImg;
        private  TextView leftTitle;
        private  TextView mlideTitle;
        private  TextView rightTitle;

        public XToolBarImpl(View view) {
            leftImg = (ImageView) view.findViewById(R.id.img_left_img);
            rightImg = (ImageView) view.findViewById(R.id.img_right_img);

            leftTitle = (TextView) view.findViewById(R.id.tv_left_title);
            mlideTitle = (TextView) view.findViewById(R.id.tv_mlide_title);
            rightTitle = (TextView) view.findViewById(R.id.tv_right_title);



        }
    }

    public static class Builder{

        View.OnClickListener leftClickerListener;
        View.OnClickListener middleClickerListener;
        View.OnClickListener rightClickerListener;

        String leftText;
        String middleText;
        String rightText;

        int leftImgResId;
        int middleImgResId;
        int rightImgResId;


        public XToolBar build(Activity activity){
            return build(activity.getWindow().getDecorView());
        }

        public XToolBar build(Fragment fragment){
            return build(fragment.getView());
        }

        private XToolBar build(View view) {
            // 多个一一对应
            synchronized (TITLE_MAP) {
                if (TITLE_MAP.containsKey(view)) {
                    XToolBar cachedTitleX = TITLE_MAP.get(view);
                    cachedTitleX.builder.updateWith(this);
                    return cachedTitleX;
                } else {
                    XToolBar newTitleX = new XToolBar(view, this);
                    TITLE_MAP.put(view, newTitleX);
                    return newTitleX;
                }
            }
        }

        private void updateWith(Builder builder) {

        }

        public View.OnClickListener getLeftClickerListener() {
            return leftClickerListener;
        }

        public Builder setLeftClickerListener(View.OnClickListener leftClickerListener) {
            this.leftClickerListener = leftClickerListener;
            return this;
        }

        public View.OnClickListener getMiddleClickerListener() {
            return middleClickerListener;
        }

        public Builder setMiddleClickerListener(View.OnClickListener middleClickerListener) {
            this.middleClickerListener = middleClickerListener;
            return this;
        }

        public View.OnClickListener getRightClickerListener() {
            return rightClickerListener;
        }

        public Builder setRightClickerListener(View.OnClickListener rightClickerListener) {
            this.rightClickerListener = rightClickerListener;
            return this;
        }

        public String getLeftText() {
            return leftText;
        }

        public Builder setLeftText(String leftText) {
            this.leftText = leftText;
            return this;
        }

        public String getMiddleText() {
            return middleText;
        }

        public Builder setMiddleText(String middleText) {
            this.middleText = middleText;
            return this;
        }

        public String getRightText() {
            return rightText;
        }

        public Builder setRightText(String rightText) {
            this.rightText = rightText;
            return this;
        }

        public int getLeftImgResId() {
            return leftImgResId;
        }

        public Builder setLeftImgResId(int leftImgResId) {
            this.leftImgResId = leftImgResId;
            return this;
        }

        public int getMiddleImgResId() {
            return middleImgResId;
        }

        public Builder setMiddleImgResId(int middleImgResId) {
            this.middleImgResId = middleImgResId;
            return this;
        }

        public int getRightImgResId() {
            return rightImgResId;
        }

        public Builder setRightImgResId(int rightImgResId) {
            this.rightImgResId = rightImgResId;
            return this;
        }


    }

    public static class XToolBarParame{
        int leftImgResId;
        int middleImgResId;
        int rightImgResId;

        View.OnClickListener leftClickerListener;
        View.OnClickListener middleClickerListener;
        View.OnClickListener rightClickerListener;

        String leftText;
        String middleText;
        String rightText;
    }
}
