package com.example.hasee.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hasee.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class BannerView extends FrameLayout {

    private static final int MSG_LOOP = 1000;
    private static long LOOP_INTERVAL = 3000;
    private LinearLayout mLinearPosition = null;
    private ViewPager mViewPager = null;
    private BannerHandler mBannerHandler = null;

    private List<View> viewList = new ArrayList<>();
    private int viewSize;
    //是否需要循环滚动
    private boolean isScrolled = true;
    //没有缓存的数据，类似的viewpager加载第一张会缓存第二张，第二张加载完，
    // 由于只有2张，相邻缓存的只有前边第一张，没有第三张图可以显示了，就会出现背景图。而闪烁一下又有图片出来了，是因为重新加载了新的数据，所以闪烁一下又能看到图片了
    private boolean isTwoSize = false;
    //购物页修改banner指示器图片
    private int imgSelectedId = R.mipmap.banner_point_selected;
    private int imgUnSelectedId = R.mipmap.banner_point;
    //修改指示器距离底部间距
    private int mBottomMarginID = R.dimen.dimen_dp;
    //修改指示器左右间距
    private int mLeftOrRightMarginId = R.dimen.dimen_9dp;


    private boolean isDealMeasure;

    public void setDealMeasure(boolean dealMeasure) {
        isDealMeasure = dealMeasure;
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // Children are just made to fill our space.
//
////        int width = MeasureSpec.getSize(widthMeasureSpec);
////        if(isDealMeasure) {
////            //设置高度与宽度一样
////            float height = (int) (1.0 * width * 69 / 345);
////            L.i("ji")
////            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height, MeasureSpec.EXACTLY);
////        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
    private static class BannerHandler extends Handler {
        private WeakReference<BannerView> weakReference = null;

        public BannerHandler(BannerView bannerView) {
            super(Looper.getMainLooper());
            this.weakReference = new WeakReference<>(bannerView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.weakReference == null) {
                return;
            }
            BannerView bannerView = this.weakReference.get();
            if (bannerView == null || bannerView.mViewPager == null || bannerView.mViewPager.getAdapter() == null || bannerView.mViewPager.getAdapter().getCount() <= 0) {
//                sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                return;
            }
            int curPos = bannerView.mViewPager.getCurrentItem();
            curPos = (curPos + 1) % bannerView.mViewPager.getAdapter().getCount();
            bannerView.mViewPager.setCurrentItem(curPos);
            if (hasMessages(MSG_LOOP)) {
                removeMessages(MSG_LOOP);
            }
            sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
        }
    }

    public BannerView(Context context) {
        super(context);
        init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void startLoop(boolean flag) {
        if (flag) {
            if (mBannerHandler == null) {
                mBannerHandler = new BannerHandler(this);
            }
            mBannerHandler.sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
        } else {
            if (mBannerHandler != null) {
                if (mBannerHandler.hasMessages(MSG_LOOP)) {
                    mBannerHandler.removeMessages(MSG_LOOP);
                    mBannerHandler = null;
                }
            }
        }
    }

    private void init() {
        initViewPager();
        initLinearPosition();
        this.addView(mViewPager);
        this.addView(mLinearPosition);
    }

    private void initViewPager() {
        mViewPager = new ViewPager(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        mViewPager.setLayoutParams(layoutParams);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            int currentPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateLinearPosition();
//                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // ViewPager.SCROLL_STATE_IDLE 标识的状态是当前页面完全展现，并且没有动画正在进行中，如果不
                // 是此状态下执行 setCurrentItem 方法回在首位替换的时候会出现跳动！
//                if (state != ViewPager.SCROLL_STATE_IDLE) return;
//
//                if (currentPosition == viewList.size() - 1) {
//                    // 当视图在最后一个是,将页面号设置为图片的第一张。
//                    mViewPager.setCurrentItem(0, false);
//                }
            }
        });
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //down事件被onclick消费掉了
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_DOWN:
                        if (mBannerHandler != null) {
                            if (mBannerHandler.hasMessages(MSG_LOOP)) {
                                mBannerHandler.removeMessages(MSG_LOOP);
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mBannerHandler != null) {
                            mBannerHandler.sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initLinearPosition() {
        mLinearPosition = new LinearLayout(getContext());
        mLinearPosition.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER | Gravity.BOTTOM;
        layoutParams.bottomMargin = getResources().getDimensionPixelOffset(mBottomMarginID);
        mLinearPosition.setPadding(getResources().getDimensionPixelOffset(mLeftOrRightMarginId),
                getResources().getDimensionPixelOffset(R.dimen.dimen_1dp), 0, 0);
        mLinearPosition.setLayoutParams(layoutParams);
    }

    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        //注册一个观察者
        adapter.registerDataSetObserver(mDataObserver);
        updateLinearPosition();//更新下面的线
    }

    private DataSetObserver mDataObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateLinearPosition();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    };

    //更新底部线的位置
    private void updateLinearPosition() {
        if (viewList != null && viewList.size() != 0) {
            //viewSize有数量了就开始走这个if
            if (mLinearPosition.getChildCount() != viewSize) {
                int diffCnt = mLinearPosition.getChildCount() - viewSize;
                boolean needAdd = diffCnt < 0;//是否小于0
                diffCnt = Math.abs(diffCnt);
                for (int i = 0; i < diffCnt; i++) {
                    if (needAdd) {
                        if (viewSize > 1) {
                            ImageView img = new ImageView(getContext());
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.gravity = Gravity.CENTER;
                            layoutParams.setMargins(0, getResources().getDimensionPixelOffset(R.dimen.dimen_1dp), 0, getResources().getDimensionPixelOffset(mBottomMarginID));
                            layoutParams.rightMargin = getResources().getDimensionPixelOffset(mLeftOrRightMarginId);
                            img.setLayoutParams(layoutParams);
                            img.setBackgroundResource(imgUnSelectedId);
                            mLinearPosition.addView(img);//添加view
                            mLinearPosition.setVisibility(VISIBLE);
                        }
                    } else {
                        mLinearPosition.removeViewAt(0);//移除view
                        if (mLinearPosition.getChildCount() <= 1) {
                            mLinearPosition.setVisibility(INVISIBLE);
                        }
                    }
                }
            }
            int curPos = mViewPager.getCurrentItem();//那个被选中
            for (int i = 0; i < mLinearPosition.getChildCount(); i++) {
                if (i == (curPos % viewSize)) {
                    mLinearPosition.getChildAt(i).setBackgroundResource(imgSelectedId);
                } else {
                    mLinearPosition.getChildAt(i).setBackgroundResource(imgUnSelectedId);
                }
            }
        }
    }

    public void setViewList(List<View> list, boolean twoSizeTag) {
        if (list == null || list.size() < 1) return;
        if (viewList != null && viewList.size() > 0) {
            viewList.clear();
        }
        isTwoSize = twoSizeTag;
        viewList.addAll(list);
        if (viewList.size() != 0) {
            if (isTwoSize) {
                viewSize = 2;
            } else {
                viewSize = viewList.size();
            }
            BannerAdapter bannerAdapter = new BannerAdapter(viewList);
            bannerAdapter.setScrolled(isScrolled);
            setAdapter(bannerAdapter);
        }
    }

    public void setTransformAnim(boolean flag) {
        if (flag) {
            mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
                private static final float MIN_SCALE = 0.75f;

                @Override
                public void transformPage(View view, float position) {
                    int pageWidth = view.getWidth();
                    if (position < -1) { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        view.setRotation(0);

                    } else if (position <= 1) { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        if (position < 0) {

                            float mRot = (20f * position);
                            view.setPivotX(view.getMeasuredWidth() * 0.5f);
                            view.setPivotY(view.getMeasuredHeight());
                            view.setRotation(mRot);
                        } else {

                            float mRot = (20f * position);
                            view.setPivotX(view.getMeasuredWidth() * 0.5f);
                            view.setPivotY(view.getMeasuredHeight());
                            view.setRotation(mRot);
                        }

                        // Scale the page down (between MIN_SCALE and 1)

                        // Fade the page relative to its size.

                    } else { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        view.setRotation(0);
                    }
                }
            });
        }
    }

    public void setLoopInterval(long loopInterval) {
        LOOP_INTERVAL = loopInterval;
    }

    public void setViewPagerScrolled(boolean isScroll) {
        isScrolled = isScroll;
    }

    public void stopLoop() {
        if (mBannerHandler != null) {
            if (mBannerHandler.hasMessages(MSG_LOOP)) {
                mBannerHandler.removeMessages(MSG_LOOP);
                mBannerHandler = null;
            }
        }
    }


    //banner指示器图片显示
    public void setPositionImgResId(int unSelectedId, int selectedId) {
        this.imgUnSelectedId = unSelectedId;
        this.imgSelectedId = selectedId;
    }

    //相邻指示器左右间距
    public void setPaddingLeftOrRight(int paddingId) {
        mLeftOrRightMarginId = paddingId;
    }


    //指示器距离底部间距
    public void setPaddingBottomDistance(int paddingId) {
        mBottomMarginID = paddingId;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mBannerHandler != null) {
            mBannerHandler.removeMessages(MSG_LOOP);
            mBannerHandler = null;
        }
    }

}
