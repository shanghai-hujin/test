package com.example.hasee.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.utils.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.dialog.widget.popup.base.BaseBubblePopup;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/20 14:46
 */

public class ImgAndTextPopup extends BaseBubblePopup<ImgAndTextPopup> {

    private TextView mTvCon;
    private TextView mTvTitel;
    private SimpleDraweeView mSimpleDraweeView;

    public ImgAndTextPopup(Context context) {
        super(context);
    }

    public void setText(String con, String title, String url){
        mTvCon.setText(con);
        mTvTitel.setText(title);
        FrescoUtils.setController(url, mSimpleDraweeView);
    }

    @Override
    public View onCreateBubbleView() {
        View view = View.inflate(mContext, R.layout.popup_textandimg, null);
        mSimpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.sd_nasaimg);
        mTvCon = (TextView) view.findViewById(R.id.tv_nasa_con);
        mTvTitel = (TextView) view.findViewById(R.id.tv_nasa_title);
        return view;
    }
}
