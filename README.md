# RxMvp
> 一个简单灵活的MVP框架，一个Activity或Fragment中使用注解的方式，添加多个Presenter。

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[ ![Download](https://api.bintray.com/packages/sourfeng/repositories/wwcore/images/download.svg?version=1.0.2) ](https://bintray.com/sourfeng/repositories/wwcore/1.0.2/link)

## 使用

### 1. 导入

在app目录下的build.gradle中添加依赖

```
implementation 'com.coder:mvp:1.0.2'
```
### 2. 创建View
创建一个View 继承自 BaseView

```java
public interface TestView extends BaseView {
    void showArticle(List<TestBean> entities);
}
```

### 3. 创建Presenter
创建一个Presenter 继承自 BasePresenter

```java
public class TestPresenter extends BasePresenter<TestView> {
    
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
```

### 4. 创建Activity或Fragment

创建一个Activity或Fragment继承自 BaseMvpActivity, 并实现 BaseView或自定义View

```java
public abstract class BaseActivity extends BaseMvpActivity implements BaseView {
    
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
```

```java
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
```
## 另外

内部实现了Retrofit请求，可以直接继承 RetrofitRequest

```java
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
```
但是需提供3个参数：

* baseUrl, 请求Host地址
* Application，应用Application
* header, 没有可以直接返回一个空的`HashMap`

## License

```
Copyright 2017 SY.Tang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```