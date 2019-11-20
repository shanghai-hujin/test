package com.example.hasee.news.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.hasee.common.base.mvp.XDaggerFragment;
import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.weight.banner.BannerView;
import com.example.hasee.common.weight.banner.ShowBanner;
import com.example.hasee.news.R;
import com.example.hasee.news.di.NewsDiHelper;
import com.example.hasee.news.hook.MyProxyListener;
import com.example.hasee.news.hook.ProxyHandler;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/home/NewsFragment")
public class NewsFragment extends XDaggerFragment<NewsPresenter> implements INewsContract.IView {

    private Banner mBannerHomeNews;
    private TextView tvHook;
    private PlayerView playerView;
    private ExoPlayer player;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;
    private ImageView view;
    private BannerView bannerView;

    @Override
    public void initViewF(Bundle savedInstanceState) {
        mBannerHomeNews = (Banner) find(R.id.banner_news);

        mPresenter.getWanHomeBanner();
        //  tvHook = (TextView) find(R.id.tv_hook);
//        tvHook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //
//                Log.d("NewsFragment","原始点击");
//            }
//        });
        //    hookListener(tvHook);
//
        playerView = find(R.id.video_view);

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        Uri uri = Uri.parse("asset:///video.mp4");
//        MediaSource mediaSource = new ExtractorMediaSource.Factory(
//                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
//                createMediaSource(uri);

        MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(getActivity(), "spx")).createMediaSource(uri);

     //   LoopingMediaSource loopingMediaSource = new LoopingMediaSource(mediaSource, 3);

        //无限循环
        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        player.prepare(mediaSource, true, false);

        bannerView = (BannerView) find(R.id.bv_best);


    }

    private void hookListener(View view) {
        View.OnClickListener listener = ((View.OnClickListener) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                new Class[]{View.OnClickListener.class},
                new ProxyHandler(new MyProxyListener())));
        view.setOnClickListener(listener);
    }


    private View.OnClickListener getListener() {
        return ((View.OnClickListener) Proxy.newProxyInstance(
                NewsFragment.this.getClass().getClassLoader(),
                new Class[]{View.OnClickListener.class},
                new ProxyHandler(new MyProxyListener())));
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_news;
    }

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initInject(Bundle savedInstanceState) {
        NewsDiHelper.getFragmentComponent(getFragmentModule()).inject(this);
    }

    @Override
    public void showBannerData(List<WanHomeBannerResponse> response) {
        mBannerHomeNews.setImages(response).setImageLoader(new ImageLoader() {
//            @Override
//            public ImageView createImageView(Context context) {
//                ImageView imageView = new ImageView(context);
//
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                imageView.setAdjustViewBounds(true);
//                return imageView;
//            }

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(_mActivity).load(((WanHomeBannerResponse) path).getImagePath()).into(imageView);
            }
        }).isAutoPlay(true).start();


        List<String> list = new ArrayList<>();
        for (int i = response.size() - 1; i >= 0; i--) {
            list.add(response.get(i).getImagePath());
        }
        list.add("asset:///video.mp4");
        ShowBanner.showWealthBanner(list,getActivity(),bannerView);
    }
}
