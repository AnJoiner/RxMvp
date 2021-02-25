package com.coder.tasker;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coder.mvp.annotation.CreatePresenter;
import com.coder.mvp.annotation.PresenterVariable;
import com.coder.tasker.base.BaseActivity;
import com.coder.tasker.databinding.ActivityMainBinding;
import com.coder.tasker.mvp.model.TestBean;
import com.coder.tasker.mvp.presenter.TestPresenter;
import com.coder.tasker.mvp.vu.TestVu;
import com.coder.tasker.ui.home.adapter.TestAdapter;

import java.util.List;


@CreatePresenter(presenter = TestPresenter.class)
public class MainActivity extends BaseActivity<ActivityMainBinding> implements TestVu {

    @PresenterVariable
    TestPresenter mPresenter;

    RecyclerView mRecyclerView;

    private TestAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void init() {
        initView();
        initRecycler();
        mPresenter.refresh(true);
    }

    private void initView(){
        mRecyclerView = mViewBinding.rv;
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
