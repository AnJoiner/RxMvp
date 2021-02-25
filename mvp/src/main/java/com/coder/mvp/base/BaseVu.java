package com.coder.mvp.base;

/**
 * create by lzx
 * time:2018/7/27
 */
public interface BaseVu {

    void start();

    void showError(String msg);

    void complete();

    void showProgressUI(boolean isShow);

    void loadMoreStatus(boolean isComplete);

    void showEmpty(int status);
}
