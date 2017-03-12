package com.example.test.mvp.di.component;

import android.app.Activity;

import com.example.test.mvp.di.module.FragmentModule;
import com.example.test.mvp.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by duchao on 2017/3/12.
 */
@FragmentScope
@Component(dependencies = IAppComponent.class, modules = FragmentModule.class)
public interface IFragmentComponent {
    public Activity getActivity();

    //inject
}
