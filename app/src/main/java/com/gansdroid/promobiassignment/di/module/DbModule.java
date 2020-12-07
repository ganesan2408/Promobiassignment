package com.gansdroid.promobiassignment.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.gansdroid.promobiassignment.data.local.dao.NewsDao;
import com.gansdroid.promobiassignment.data.local.db.NewsDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    @Provides
    @Singleton
    NewsDb provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                NewsDb.class, "News.db")
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    NewsDao provideNewsDao(NewsDb newsDb) {
        return newsDb.newsDao();
    }

}
