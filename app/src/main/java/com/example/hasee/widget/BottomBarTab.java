package com.example.hasee.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.utils.ContextUtils;


public class BottomBarTab extends LinearLayout {
    private ImageView mIcon;
    private Context mContext;
    private TextView mTextView;
    private int mTabPosition = -1;
    private int icon;
    private static boolean ifshow = false;

    public BottomBarTab(Context context, @DrawableRes int icon, String title) {
        this(context, null, icon, title);
    }


    public BottomBarTab(Context context, AttributeSet attrs, int icon, String title) {
        this(context, attrs, 0, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, String title) {
        super(context, attrs, defStyleAttr);
        init(context, icon, title);
    }

    private void init(Context context, int icon, String title) {
        mContext = context;
        this.icon = icon;
       /* TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();*/

        setOrientation(LinearLayout.VERTICAL);
        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(size, size);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = ContextUtils.dip2px(context, 3.5f);
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);

        // mIcon.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselect));
        LayoutParams textViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textViewParams.gravity = Gravity.CENTER_HORIZONTAL;
        // textViewParams.addRule(ALIGN_PARENT_BOTTOM);
        textViewParams.topMargin = ContextUtils.dip2px(context, 3.5f);
        textViewParams.bottomMargin = ContextUtils.dip2px(context, 3.5f);
        mTextView = new TextView(context);
        mTextView.setText(title);
        mTextView.setTextSize(ContextUtils.dip2px(context, 3.7f));
        mTextView.setLayoutParams(textViewParams);
        mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.tab_unselect));
        addView(mIcon);
        addView(mTextView);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {

            mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            switch (mTabPosition) {
                case 0:
                    mIcon.setImageResource(R.mipmap.bootom_news_sel);
                    break;
                case 1:
                    mIcon.setImageResource(R.mipmap.bootom_book_sel);
                    break;
                case 2:
                    mIcon.setImageResource(R.mipmap.bootom_movie_sel);
                    break;
                case 3:
                    mIcon.setImageResource(R.mipmap.bootom_my_sel);
                    break;
                default:
                    break;

            }
        } else {
            switch (mTabPosition) {
                case 0:
                    mIcon.setImageResource(R.mipmap.bootom_news);
                    break;
                case 1:
                    mIcon.setImageResource(R.mipmap.bootom_book);
                    break;
                case 2:
                    mIcon.setImageResource(R.mipmap.bootom_movie);
                    break;
                case 3:
                    mIcon.setImageResource(R.mipmap.bootom_my);
                    break;
                default:
                    break;

            }
            mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.tab_unselect));
        }
    }


    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
