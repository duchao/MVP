package com.example.test.mvp.di.module;

import android.app.Activity;

import com.example.test.mvp.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by duchao on 2017/3/12.
 */
@Module
public class ActivityModule {
    private Activity mActivity;
    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}
