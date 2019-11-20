package com.example.hasee.common.weight.toolbar;

import android.app.Activity;
import android.view.View;

public class XToolBar {
    private XAbstractToolBar xAbstractToolBar;

    public XToolBar(XAbstractToolBar xAbstractToolBar) {
        this.xAbstractToolBar = xAbstractToolBar;
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

        public XToolBar build(Activity mContext, XAbstractToolBar xAbstractToolBar){

            return null;
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
}
