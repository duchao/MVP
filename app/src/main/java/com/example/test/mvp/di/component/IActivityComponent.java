package com.example.test.mvp.di.component;


import android.app.Activity;

import com.example.test.mvp.MainActivity;
import com.example.test.mvp.di.module.ActivityModule;
import com.example.test.mvp.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by duchao on 2017/3/12.
 */
@ActivityScope
@Component(dependencies = IAppComponent.class, modules = ActivityModule.class)
public interface IActivityComponent {
    public Activity getActivity();

    // inject
    public void inject(MainActivity mainActivity);


}
