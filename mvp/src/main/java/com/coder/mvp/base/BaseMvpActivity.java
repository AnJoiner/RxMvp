package com.coder.mvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseMvpActivity<T extends ViewBinding> extends AppCompatActivity implements BaseVu {

    private PresenterProviders mPresenterProviders;
    private PresenterDispatch mPresenterDispatch;

    protected CompositeDisposable disposables;
    protected T mViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateStart(savedInstanceState);

        mViewBinding = buildViewBinding();
        if (mViewBinding != null && mViewBinding.getRoot()!=null){
            setContentView(mViewBinding.getRoot());
        }else {
            setContentView(getLayoutId());
        }
        disposables = new CompositeDisposable();
        onPresenterCreate(savedInstanceState);
        onCreated(savedInstanceState);
        init();
    }

    private T buildViewBinding(){
        T binding = null;
        Type superclass = getClass().getGenericSuperclass();
        Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        try {
            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class);
            binding = (T) method.invoke(null, getLayoutInflater());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return binding;
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

    protected abstract void init();

    protected abstract void onCreated(Bundle savedInstanceState);

    protected abstract void onCreateStart(Bundle savedInstanceState);

    protected PresenterProviders getPresenterProviders() {
        return mPresenterProviders;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenterDispatch.detachView();
        disposables.clear();
    }
}