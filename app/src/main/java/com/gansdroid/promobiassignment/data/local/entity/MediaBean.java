package com.gansdroid.promobiassignment.data.local.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaBean {

    @SerializedName("media-metadata")
    @Expose
    public final List<MediaMetaDataBean> mediaMetaDataBeanList = null;
}
