package com.example.hasee.ui.adpater;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.ui.movie.MovieDetailFragment;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/8 16:50
 */

public class MoviePagerAdapter extends FragmentStatePagerAdapter{

    private String[] mStrings;

    public MoviePagerAdapter(FragmentManager fm, String[] list) {
        super(fm);
        this.mStrings = list;
    }

    @Override
    public BaseFragment getItem(int position) {
        return MovieDetailFragment.newInstance(position, mStrings);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings[position];
    }

    @Override
    public int getCount() {
        return mStrings != null ? mStrings.length : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
