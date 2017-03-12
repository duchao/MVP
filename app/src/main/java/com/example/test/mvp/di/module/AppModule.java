package com.example.test.mvp.di.module;

import com.example.test.mvp.app.App;
import com.example.test.mvp.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by duchao on 2017/3/12.
 */
@Module
public class AppModule {
    private final App mApplication;

    public AppModule(App application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public App provideApplicationContext() {
        return mApplication;
    }
    // 提供retrofit
    @Provides
    @Singleton
    public RetrofitHelper provideRetrofitHelper() {
        return  new RetrofitHelper();
    }
    // 提供数据库


}
