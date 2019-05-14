package com.coder.tasker;

import com.coder.mvp.annotation.CreatePresenter;
import com.coder.mvp.annotation.PresenterVariable;
import com.coder.tasker.base.BaseActivity;
import com.coder.tasker.mvp.model.TestBean;
import com.coder.tasker.mvp.presenter.TestPresenter;
import com.coder.tasker.mvp.vu.TestView;

import java.util.List;


@CreatePresenter(presenter = TestPresenter.class)
public class MainActivity extends BaseActivity implements TestView {

    @PresenterVariable
    TestPresenter mPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        mPresenter.refresh(true);
    }

    @Override
    public void showArticle(List<TestBean> entities) {

    }
}
