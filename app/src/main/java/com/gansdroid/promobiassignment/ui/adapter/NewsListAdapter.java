package com.gansdroid.promobiassignment.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;
import com.gansdroid.promobiassignment.databinding.NewsDataBinding;
import com.gansdroid.promobiassignment.ui.controller.NewsClickHandler;
import com.gansdroid.promobiassignment.utility.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {


    private final List<NewsEntity> newsList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private NewsClickHandler newsClickHandler;

    @Inject
    public NewsListAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        NewsDataBinding dataBinding = NewsDataBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(dataBinding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //SET VIEW DATA
        final NewsEntity news = newsList.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsDataBinding newsDataBinding;

        ViewHolder(NewsDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.newsDataBinding = dataBinding;
            this.newsDataBinding.setHandler(newsClickHandler);
        }

        void bind(NewsEntity news) {
            newsDataBinding.setNews(news);
            //this.newsDataBinding.imageNews//.setText();
            if (news.mediaBeanList != null && news.mediaBeanList.size() > 0 &&
                    news.mediaBeanList.get(0) != null && news.mediaBeanList.get(0).mediaMetaDataBeanList != null &&
                    news.mediaBeanList.get(0).mediaMetaDataBeanList.size() > 0 &&
                    news.mediaBeanList.get(0).mediaMetaDataBeanList.get(0) != null &&
                    news.mediaBeanList.get(0).mediaMetaDataBeanList.get(0).url != null) {
                GlideUtil.loadImage(newsDataBinding.imageNews, news.mediaBeanList.get(0).mediaMetaDataBeanList.get(0).url);
            }
        }
    }

    public void addItems(List<NewsEntity> newsEntityList) {
        this.newsList.clear();
        this.newsList.addAll(newsEntityList);
        notifyDataSetChanged();
    }

    public void setItemClickListener(NewsClickHandler newsClickHandler) {
        this.newsClickHandler = newsClickHandler;
    }
}
