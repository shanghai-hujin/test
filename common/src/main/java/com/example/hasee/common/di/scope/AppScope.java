package com.example.hasee.common.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Demo ${CLASS}
 *
 * @author hj
 * @date 2018/8/20 14:32
 */
@Scope
@Retention(RUNTIME)
public @interface AppScope {
}
