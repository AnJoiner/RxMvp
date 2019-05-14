package com.coder.tasker.api;

import com.coder.tasker.base.BaseResponse;
import com.coder.tasker.mvp.model.TestBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @auther: AnJoiner
 * @datetime: 2019/5/14
 */
public interface TestApi {

    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<TestBean>>> mv ();
}
