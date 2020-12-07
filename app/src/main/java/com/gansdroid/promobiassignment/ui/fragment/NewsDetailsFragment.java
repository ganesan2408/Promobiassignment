package com.gansdroid.promobiassignment.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.gansdroid.promobiassignment.R;
import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;
import com.gansdroid.promobiassignment.databinding.NewsDetailsFragmentBinding;
import com.gansdroid.promobiassignment.ui.activity.MainActivity;
import com.gansdroid.promobiassignment.utility.GlideUtil;

import dagger.android.support.DaggerFragment;

public class NewsDetailsFragment extends DaggerFragment {

    private NewsDetailsFragmentBinding newsDetailsFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        newsDetailsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false);
        return newsDetailsFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewsEntity newsEntity = (NewsEntity) getArguments().getParcelable("SELECTED_NEWS");
        newsDetailsFragmentBinding.setNews(newsEntity);
        if (newsEntity.mediaBeanList != null && newsEntity.mediaBeanList.size() > 0 &&
                newsEntity.mediaBeanList.get(0) != null && newsEntity.mediaBeanList.get(0).mediaMetaDataBeanList != null &&
                newsEntity.mediaBeanList.get(0).mediaMetaDataBeanList.size() > 2 &&
                newsEntity.mediaBeanList.get(0).mediaMetaDataBeanList.get(2) != null &&
                newsEntity.mediaBeanList.get(0).mediaMetaDataBeanList.get(2).url != null) {
            GlideUtil.loadImage(newsDetailsFragmentBinding.imageNewsDetail, newsEntity.mediaBeanList.get(0).mediaMetaDataBeanList.get(2).url);
        }
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
