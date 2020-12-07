package com.gansdroid.promobiassignment.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertNews(List<NewsEntity> news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNews(NewsEntity news);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateNews(NewsEntity news);

    @Query("SELECT * FROM NewsEntity")
    List<NewsEntity> getNews();

}
