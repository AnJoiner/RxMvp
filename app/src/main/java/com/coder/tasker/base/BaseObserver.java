package com.coder.tasker.base;

import android.content.Context;

import com.coder.mvp.base.BaseView;
import com.coder.mvp.retrofit.ApiException;
import com.coder.tasker.config.Constants;

import io.reactivex.observers.ResourceObserver;

/**
 * @auther: AnJoiner
 * @datetime: 2019/5/14
 */
public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    private final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";
    private final String STATUS_EXCEPTION = "状态异常";
    private final String CONVERT_EXCEPTION = "网络数据转换失败";
    private final String COMPONENT_EXCEPTION = "The object neither activity nor fragment.";


    protected BaseView mBaseView;

    private Context context;

    public BaseObserver(BaseView baseView, Context context) {
        mBaseView = baseView;
        this.context = context;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T responseBean) {
        if (mBaseView == null) {
            return;
        }
       if (responseBean instanceof BaseResponse){
           int errorCode = ((BaseResponse) responseBean).getErrorCode();
           if (errorCode == Constants.CODE_OK) {
               mBaseView.complete();
               resultSuccess(responseBean);
           } else {
               onError(new ApiException(((BaseResponse) responseBean).getErrorCode(), (
                       (BaseResponse) responseBean).getErrorMsg()));
           }
       }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ApiException){
            int errorCode = ((ApiException) e).getCode();
            if (errorCode == 1000 || errorCode == 1001){
                tokenInvalid();
            }else {
                resultError(e.getMessage());
            }
        }else {
            resultError(e.getMessage());
        }
    }

    public void resultError(String msg){
        mBaseView.showProgressUI(false);
        mBaseView.showError(msg);
    }

    public void tokenInvalid(){
        // Token 失效
    }

    protected abstract void resultSuccess(T responseBean);

}
