package com.gansdroid.promobiassignment.data.repository;

import android.util.Log;

import com.gansdroid.promobiassignment.data.NetworkBoundResource;
import com.gansdroid.promobiassignment.data.NetworkResource;
import com.gansdroid.promobiassignment.data.local.dao.NewsDao;
import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;
import com.gansdroid.promobiassignment.data.remote.api.NewsApiInterface;
import com.gansdroid.promobiassignment.data.remote.model.NewsApiResponse;
import com.gansdroid.promobiassignment.utility.AppConstant;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Singleton
public class NewsRepository {
    private NewsDao newsDao;
    private NewsApiInterface newsApiInterface;

    public NewsRepository(NewsDao newsDao, NewsApiInterface newsApiInterface) {
        this.newsDao = newsDao;
        this.newsApiInterface = newsApiInterface;
    }

    public Observable<NetworkResource<List<NewsEntity>>> getNewsList(String section, String period) {

        return new NetworkBoundResource<List<NewsEntity>, NewsApiResponse>() {
            boolean shouldFetch = true;

            @Override
            protected void saveCallResult(NewsApiResponse item) {
                Log.d("Gansdroid", "inserting rest api data to room database");
                newsDao.insertNews(item.getResults());
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @Override
            protected Flowable<List<NewsEntity>> loadFromDb() {
                List<NewsEntity> newsEntities = newsDao.getNews();
                if (newsEntities == null || newsEntities.isEmpty()) {
                    shouldFetch = true;
                    return Flowable.empty();
                }
                shouldFetch = false;
                Log.d("Gansdroid", "loading offline data");
                return Flowable.just(newsEntities);
            }

            @Override
            protected Observable<NetworkResource<NewsApiResponse>> createCall() {
                return newsApiInterface.getNewsList(section, period)
                        .flatMap(newsApiResponse -> Observable.just(newsApiResponse == null
                                ? NetworkResource.error("Failed", new NewsApiResponse(null, 0, null))
                                : NetworkResource.success(newsApiResponse)));
            }
        }.getAsObservable();
    }
}
