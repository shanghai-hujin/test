package com.example.hasee.common.di.module;

import android.support.v4.app.Fragment;

import com.example.hasee.common.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    @Provides
    @FragmentScope
    public Fragment provideFragment(){
        return mFragment;
    }
}
