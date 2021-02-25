package com.coder.tasker.base;

import android.os.Bundle;
import android.widget.Toast;

import com.coder.mvp.base.BaseMvpActivity;
import com.coder.mvp.base.BaseVu;

/**
 * @auther: AnJoiner
 * @datetime: 2019/5/14
 */
public abstract class BaseActivity extends BaseMvpActivity implements BaseVu {


    @Override
    public void onCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onCreateStart(Bundle savedInstanceState) {

    }

    @Override
    public void start() {

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void complete() {

    }

    @Override
    public void showProgressUI(boolean isShow) {

    }

    @Override
    public void loadMoreStatus(boolean isComplete) {

    }

    @Override
    public void showEmpty(int status) {

    }
}
