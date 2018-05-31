package com.example.hasee.ui.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.bean.NewsDetail;
import com.example.hasee.http.Common;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BaseContract;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/31 14:05
 */

public class ImageBrowseActivity extends BaseActivity{
    private static final String AID = "aid";
    private static final String ISCMPP = "isCmpp";

    @Override
    public int getContentLayout() {
        return R.layout.activity_imagebrowse;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public BaseContract.BasePresenter createPresenter() {
        return null;
    }

    public static void launch(Activity activity, NewsDetail.ItemBean itemBean) {
        Intent intent = new Intent(activity, ImageBrowseActivity.class);
        if(itemBean.getId().contains(Common.GetNewsArticleCmppApi) || itemBean.getDocumentId().startsWith("cmpp")){
            intent.putExtra(ISCMPP, true);
        }else {
            intent.putExtra(ISCMPP, false);
        }
        intent.putExtra(AID, itemBean.getDocumentId());
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
