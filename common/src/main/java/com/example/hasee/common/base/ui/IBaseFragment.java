package com.example.hasee.common.base.ui;

import android.os.Bundle;

public interface IBaseFragment extends IBase {
    /**
     * 子类的回调
     * @param savedInstanceState
     */
    void initViewF(Bundle savedInstanceState);

}
