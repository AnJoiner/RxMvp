package com.coder.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * create by lzx
 * time:2018/7/26
 */
public abstract class BasePresenter <V>  {

    private WeakReference<Context> mContextWeakReference;
    private WeakReference<V> mVWeakReference;
    protected Context mContext;
    protected V mView;
    protected CompositeDisposable disposables;

    protected void onCleared() {

    }

    public void attachView(Context context, V view, CompositeDisposable disposables) {
        this.mContextWeakReference = new WeakReference<>(context);
        this.mVWeakReference = new WeakReference<>(view);
        this.mView = mVWeakReference.get();
        this.mContext = mContextWeakReference.get();
        this.disposables = disposables;
    }

    public void detachView() {
        this.mView = null;
        this.mVWeakReference = null;
        this.disposables = null;
    }

    public boolean isAttachView() {
        return this.mVWeakReference != null;
    }

    public void onCreatePresenter(@Nullable Bundle savedState) {

    }

    public void onDestroyPresenter() {
        detachView();
    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public abstract void refresh(boolean isRefresh);

    public abstract void loadMore();
}
