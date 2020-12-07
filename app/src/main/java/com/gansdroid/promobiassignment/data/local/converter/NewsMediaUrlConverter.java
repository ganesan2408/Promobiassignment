package com.gansdroid.promobiassignment.data.local.converter;

import androidx.room.TypeConverter;

import com.gansdroid.promobiassignment.data.local.entity.MediaBean;
import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NewsMediaUrlConverter {
    @TypeConverter
    public List<MediaBean> fromString(String value) {
        Type listType = new TypeToken<List<MediaBean>>() {
        }.getType();
        List<MediaBean> mediaBeanList = new Gson().fromJson(value, listType);
        /*if (mediaBeanList != null && mediaBeanList.size() > 0 && mediaBeanList.get(0).mediaMetaDataBeanList != null
                && mediaBeanList.get(0).mediaMetaDataBeanList.size() > 0) {
            return mediaBeanList.get(0).mediaMetaDataBeanList.get(0).url;
        }*/
        return mediaBeanList;
    }

    @TypeConverter
    public String fromList(List<MediaBean> mediaBeanList) {
        return new Gson().toJson(mediaBeanList);
    }
}
