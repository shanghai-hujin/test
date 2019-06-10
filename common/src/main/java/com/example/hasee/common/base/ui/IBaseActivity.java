package com.example.hasee.common.base.ui;

import android.os.Bundle;
import android.view.View;

public interface IBaseActivity extends IBase {
    void finishAnim();

    void finishAnim(boolean isAnim);

    /**
     * 子类的回调
     * @param view
     * @param savedInstanceState
     */
    void bindView(View view, Bundle savedInstanceState);
}
