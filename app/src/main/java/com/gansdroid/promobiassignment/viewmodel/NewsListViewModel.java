package com.gansdroid.promobiassignment.viewmodel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.gansdroid.promobiassignment.data.NetworkResource;
import com.gansdroid.promobiassignment.data.local.dao.NewsDao;
import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;
import com.gansdroid.promobiassignment.data.remote.api.NewsApiInterface;
import com.gansdroid.promobiassignment.data.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

public class NewsListViewModel extends BaseViewModel {
    private NewsRepository newsRepository;
    private MutableLiveData<NetworkResource<List<NewsEntity>>> newsLiveData = new MutableLiveData<>();

    @Inject
    public NewsListViewModel(Application application, NewsDao newsDao, NewsApiInterface newsApiInterface) {
        super(application);
        newsRepository = new NewsRepository(newsDao, newsApiInterface);
    }

    public void loadNewsList(String section, String period) {
        newsRepository.getNewsList(section, period)
                .doOnSubscribe(disposable -> addToDisposable(disposable))
                .subscribe(resource -> getNewsLiveData().postValue(resource));
    }

    public MutableLiveData<NetworkResource<List<NewsEntity>>> getNewsLiveData() {
        return newsLiveData;
    }
}
