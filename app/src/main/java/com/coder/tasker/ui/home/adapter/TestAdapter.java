package com.coder.tasker.ui.home.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coder.tasker.R;
import com.coder.tasker.mvp.model.TestBean;

import java.util.ArrayList;

/**
 * @auther: AnJoiner
 * @datetime: 2019/5/14
 */
public class TestAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {

    public TestAdapter() {
        super(R.layout.adapter_test, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
