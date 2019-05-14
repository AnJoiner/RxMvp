package com.coder.tasker;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coder.mvp.annotation.CreatePresenter;
import com.coder.mvp.annotation.PresenterVariable;
import com.coder.tasker.base.BaseActivity;
import com.coder.tasker.mvp.model.TestBean;
import com.coder.tasker.mvp.presenter.TestPresenter;
import com.coder.tasker.mvp.vu.TestView;
import com.coder.tasker.ui.home.adapter.TestAdapter;

import java.util.List;


@CreatePresenter(presenter = TestPresenter.class)
public class MainActivity extends BaseActivity implements TestView {


    @PresenterVariable
    TestPresenter mPresenter;

    RecyclerView mRecyclerView;

    private TestAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        initView();
        initRecycler();
        mPresenter.refresh(true);
    }

    private void initView(){
        mRecyclerView = findViewById(R.id.rv);
    }

    private void initRecycler(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TestAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showArticle(List<TestBean> entities) {
        mAdapter.getData().clear();
        mAdapter.addData(entities);
    }
}
