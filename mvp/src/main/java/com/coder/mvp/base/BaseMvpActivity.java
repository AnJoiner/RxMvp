package com.coder.mvp.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseVu {

    private PresenterProviders mPresenterProviders;
    private PresenterDispatch mPresenterDispatch;

    protected CompositeDisposable disposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateStart(savedInstanceState);
        setContentView(getLayoutId());
        disposables = new CompositeDisposable();
        onPresenterCreate(savedInstanceState);
        onCreated(savedInstanceState);
        init();
    }

    private void onPresenterCreate(Bundle savedInstanceState) {

        mPresenterProviders = PresenterProviders.inject(this);
        mPresenterDispatch = new PresenterDispatch(mPresenterProviders, disposables);

        mPresenterDispatch.attachView(this, this);
        mPresenterDispatch.onCreatePresenter(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterDispatch.onSaveInstanceState(outState);
    }

    protected abstract int getLayoutId();


    public abstract void init();

    public abstract void onCreated(Bundle savedInstanceState);

    public abstract void onCreateStart(Bundle savedInstanceState);

    protected P getPresenter() {
        return mPresenterProviders.getPresenter(0);
    }

    public PresenterProviders getPresenterProviders() {
        return mPresenterProviders;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenterDispatch.detachView();
        disposables.clear();
    }
}