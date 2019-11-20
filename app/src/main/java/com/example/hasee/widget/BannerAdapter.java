package com.example.hasee.widget;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xiexiaolain on 2018/2/6.
 */
public class BannerAdapter extends PagerAdapter {

    private List<View> viewList;
    private int size;
    private final int cacheCount = 3;
    private boolean isScrolled = true;

    public BannerAdapter(List<View> viewList) {
        this.viewList = viewList;
        size = viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            int size = viewList.size();
            if (size > cacheCount && position != -1 && position < size) {
                container.removeView(viewList.get(position % size));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (size == 0) {
            return null;
        }
        ViewGroup parent = (ViewGroup) viewList.get(position % size).getParent();
        if (parent != null) {
            parent.removeView(viewList.get(position % size));
        }
        container.addView(viewList.get(position % size));
        return viewList.get(position % size);
    }

    @Override
    public int getCount() {
        if (viewList != null && viewList.size() == 1) {
            return viewList.size();
        } else if (isScrolled) {

            return Integer.MAX_VALUE;
        } else {
            return viewList.size();
        }
    }

    public void setScrolled(boolean isScroll) {
        isScrolled = isScroll;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
