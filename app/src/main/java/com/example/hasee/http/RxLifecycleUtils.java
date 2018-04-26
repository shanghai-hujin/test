package com.example.hasee.http;

import android.arch.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 15:01
 */

public class RxLifecycleUtils {

    private RxLifecycleUtils()  {
        throw new IllegalStateException("不能构造该类-RxLifecycleUtils");
    }

    public static <T>AutoDisposeConverter<T> bindLifecycle(LifecycleOwner lifecycleOwner){
        return AutoDispose.autoDisposable(
                AndroidLifecycleScopeProvider.from(lifecycleOwner)
        );
    }

}
