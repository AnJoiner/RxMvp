package com.coder.tasker.api;

import android.app.Application;

import com.coder.mvp.retrofit.RetrofitRequest;
import com.coder.tasker.BaseApplication;

import java.util.HashMap;

/**
 * @auther: AnJoiner
 * @datetime: 2019/5/14
 */
public class HttpRequest extends RetrofitRequest {

    @Override
    protected Application getApplication() {
        return BaseApplication.getInstance();
    }

    @Override
    protected String getBaseUrl() {
        return "https://www.wanandroid.com/";
    }

    @Override
    protected HashMap<String, String> getHeaders() {
        return new HashMap<>();
    }

    public HttpRequest() {
        super();
    }

    public static HttpRequest getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final HttpRequest INSTANCE = new HttpRequest();
    }

    private TestApi mTestApi;

    public TestApi getTestApi(){
        if (mTestApi == null) {
            mTestApi = mRetrofit.create(TestApi.class);
        }
        return mTestApi;
    }


}
