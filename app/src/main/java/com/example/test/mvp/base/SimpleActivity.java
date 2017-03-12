package com.example.test.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.test.mvp.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by duchao on 2017/3/12.
 */

public abstract class SimpleActivity extends AppCompatActivity{
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mButterKnifeBinder;

    protected abstract int getLayout();
    protected abstract void initEventAndData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mButterKnifeBinder = ButterKnife.bind(this);
        mActivity = this;
        mContext = this;
        App.getInstance().addActivity(this);
        initEventAndData();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mButterKnifeBinder.unbind();
    }
}
