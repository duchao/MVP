package com.example.test.mvp.di.component;

import com.example.test.mvp.app.App;
import com.example.test.mvp.di.module.AppModule;
import com.example.test.mvp.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by duchao on 2017/3/12.
 */
@Singleton
@Component(modules = AppModule.class)
public interface IAppComponent {
    public App getContext();

    // 提供获取数据库对象的方法

    public RetrofitHelper getRetrofitHelper();
    // 提供获取retrofit的方法

}
