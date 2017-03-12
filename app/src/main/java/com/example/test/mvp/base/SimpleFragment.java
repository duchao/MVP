package com.example.test.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by duchao on 2017/3/12.
 */

public abstract class SimpleFragment extends Fragment{
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mButterKnifeBinder;

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
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    }
}
