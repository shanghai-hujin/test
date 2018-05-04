package com.example.hasee.utils;

import android.net.Uri;

/**
 * Created by TT on 2017/3/21.
 */

public interface FrescoBitmapCallback<T> {

    void onSuccess(Uri uri, T result);

    void onFailure(Uri uri, Throwable throwable);

    void onCancel(Uri uri);
}