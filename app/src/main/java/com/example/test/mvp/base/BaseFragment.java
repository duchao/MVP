package com.example.test.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.mvp.app.App;
import com.example.test.mvp.di.component.DaggerIFragmentComponent;
import com.example.test.mvp.di.component.IFragmentComponent;
import com.example.test.mvp.di.module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by duchao on 2017/3/12.
 */

public abstract  class BaseFragment <T extends RxPresenter> extends Fragment implements IBaseView{

    @Inject
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mButterKnifeBinder;

    protected abstract void initInject();
    protected abstract int getLayoutId();
    protected abstract void initEventAndData();

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        initInject();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mButterKnifeBinder = ButterKnife.bind(this, view);
        initEventAndData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mButterKnifeBinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    protected IFragmentComponent getFragmentComponent() {
        return DaggerIFragmentComponent.builder()
                .iAppComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }
}
