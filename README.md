[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[ ![Download](https://api.bintray.com/packages/sourfeng/repositories/wwcore/images/download.svg?version=1.0.2) ](https://bintray.com/sourfeng/repositories/wwcore/1.0.2/link)

# RxMvp
> 一个简单灵活的MVP框架，一个Activity或Fragment中使用注解的方式，添加多个Presenter。

## 使用

### 1. 导入

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
        HttpRequest.getInstance().getTestApi().mv()
                .compose(RxUtils.rxSchedulerHelper())
                .safeSubscribe(new BaseObserver<BaseResponse<List<TestBean>>>(mView,mContext) {
                    @Override
                    protected void resultSuccess(BaseResponse<List<TestBean>> responseBean) {
                        mView.showArticle(responseBean.getData());
                    }
                });
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

