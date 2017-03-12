package com.example.test.mvp.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.test.mvp.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by duchao on 2017/3/12.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return  mFragment.getActivity();
    }

}
