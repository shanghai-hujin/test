package com.example.hasee.ui.async;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author zoutao
 */
public class AsyncInflateItem {
    String inflateKey;
    int layoutResId;
    ViewGroup parent;
    OnInflateFinishedCallback callback;
    View inflatedView;

    private boolean cancelled;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isInflating() {
        return inflating;
    }

    public void setInflating(boolean inflating) {
        this.inflating = inflating;
    }

    private boolean inflating;

    //还有一些set get方法
}