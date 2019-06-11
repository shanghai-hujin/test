package com.example.hasee.common.base.mvp;

import android.os.Bundle;

/**
 * ApplicationComponent具体由子类去实现
 */
public interface ISupportDagger {
    void initInject(Bundle savedInstanceState);
}
