package com.coder.tasker.mvp.presenter;

import com.coder.mvp.base.BasePresenter;
import com.coder.mvp.retrofit.RxUtils;
import com.coder.tasker.api.HttpRequest;
import com.coder.tasker.base.BaseObserver;
import com.coder.tasker.base.BaseResponse;
import com.coder.tasker.mvp.model.TestBean;
import com.coder.tasker.mvp.vu.TestVu;

import java.util.List;

/**
 * @auther: AnJoiner
 * @datetime: 2019/5/14
 */
public class TestPresenter extends BasePresenter<TestVu> {


    @Override
    public void refresh(boolean isRefresh) {
        test();
    }

    @Override
    public void loadMore() {

    }

    private void test(){
        disposables.add(HttpRequest.getInstance().getTestApi().mv()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseResponse<List<TestBean>>>(mView,mContext) {
                    @Override
                    protected void resultSuccess(BaseResponse<List<TestBean>> responseBean) {
                        mView.showArticle(responseBean.getData());
                    }
                }));
    }
}
