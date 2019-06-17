package com.example.hasee.common.base.app;

import android.content.Context;

/**
 * 全局的application
 */
public class AppContext {
  public static Context mAppContext;


  public static void init(Context context) {
    if (mAppContext == null) {
      mAppContext = context.getApplicationContext();
    } else {
      throw new IllegalStateException("set context duplicate");
    }
  }

  public static Context get() {
    if (mAppContext == null) {
      throw new IllegalStateException("forget init?");
    } else {
      return mAppContext;
    }
  }
}