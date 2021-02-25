package com.coder.tasker.mvp.vu;

import com.coder.mvp.base.BaseVu;
import com.coder.tasker.mvp.model.TestBean;

import java.util.List;

/**
 * @auther: AnJoiner
 * @datetime: 2019/5/14
 */
public interface TestVu extends BaseVu {
    void showArticle(List<TestBean> entities);
}
