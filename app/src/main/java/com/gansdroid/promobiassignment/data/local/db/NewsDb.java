package com.gansdroid.promobiassignment.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.gansdroid.promobiassignment.data.local.converter.NewsMediaUrlConverter;
import com.gansdroid.promobiassignment.data.local.dao.NewsDao;
import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;

@Database(entities = {NewsEntity.class}, version = 1, exportSchema = false)
@TypeConverters({NewsMediaUrlConverter.class})
public abstract class NewsDb extends RoomDatabase {
    public abstract NewsDao newsDao();
}
