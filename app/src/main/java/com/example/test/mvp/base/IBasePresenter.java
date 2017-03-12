package com.example.test.mvp.base;

/**
 * Created by duchao on 2017/3/11.
 */

public interface IBasePresenter <T extends IBaseView> {
    public void attachView(T view);
    public void detachView();
}
