package xyz.lovemma.zhihudaily.mvp.presenter;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.lovemma.zhihudaily.mvp.bean.StoryContent;
import xyz.lovemma.zhihudaily.mvp.biz.StoryContentBiz;
import xyz.lovemma.zhihudaily.mvp.view.IStoryContentView;

/**
 * Created by OO on 2017/2/16.
 */

public class StoryContentPresenter extends IBasePresenter {
    private IStoryContentView mView;
    private StoryContentBiz mStoryContentBiz;

    public StoryContentPresenter(IStoryContentView view) {
        mView = view;
        mStoryContentBiz = new StoryContentBiz();
    }

    public void getStoryContent(int id) {
        Subscription subscription = mStoryContentBiz.getStoryContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryContent>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.onRequestError("数据加载失败ヽ(≧Д≦)ノ");
                        }

                    @Override
                    public void onNext(StoryContent storyContent) {
                        mView.loadStoryContent(storyContent);
                    }
                });
        addSubscription(subscription);
    }
}
