package com.example.test.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.test.mvp.app.App;
import com.example.test.mvp.di.component.DaggerIActivityComponent;
import com.example.test.mvp.di.component.IActivityComponent;
import com.example.test.mvp.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by duchao on 2017/3/11.
 */

public abstract class BaseActivity<T extends RxPresenter> extends AppCompatActivity implements IBaseView{


    @Inject
    protected T mPresenter;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mButtefKnifeBinder;

    protected abstract int getLayout();
    protected abstract void initInject();
    protected abstract void initEventAndData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mButtefKnifeBinder = ButterKnife.bind(this);
        mContext = this;
        mActivity = this;
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);//因为this 是IBaseView的实现类,这样View 和 Presenter就建立了关联
        }
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mButtefKnifeBinder.unbind();
        App.getInstance().removeActivity(this);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);//toolbar取代actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//在左边加一个返回的图标
        getSupportActionBar().setDisplayShowHomeEnabled(true);//左上角图标可以点击
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });//左上角图标设置监听
    }

    public IActivityComponent getActivityComponent() {
        return DaggerIActivityComponent.builder()
                .iAppComponent(App.getAppComponent())
                .activityModule(getActivityMoudle())
                .build();
    }


    public ActivityModule getActivityMoudle() {
        return new ActivityModule(this);
    }





}
