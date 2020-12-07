package com.gansdroid.promobiassignment.data.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.gansdroid.promobiassignment.data.local.converter.NewsMediaUrlConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(primaryKeys = ("id"))
public class NewsEntity implements Parcelable {

    //@PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    public Long id;

    @SerializedName("title")
    @Expose
    public final String title;

    @SerializedName("byline")
    @Expose
    public final String byline;

    @SerializedName("published_date")
    @Expose
    public final String published_date;

    @SerializedName("media")
    @Expose
    @TypeConverters(NewsMediaUrlConverter.class)
    public List<MediaBean> mediaBeanList;

    public NewsEntity(String byline, String title, String published_date) {
        this.title = title;
        this.byline = byline;
        this.published_date = published_date;
    }

    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel in) {
            return new NewsEntity(in);
        }

        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    protected NewsEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        byline = in.readString();
        published_date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(title);
        dest.writeString(byline);
        dest.writeString(published_date);
    }
}
