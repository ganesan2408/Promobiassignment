package com.gansdroid.promobiassignment.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gansdroid.promobiassignment.R;
import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;
import com.gansdroid.promobiassignment.databinding.NewsListFragmentBinding;
import com.gansdroid.promobiassignment.ui.activity.MainActivity;
import com.gansdroid.promobiassignment.ui.adapter.NewsListAdapter;
import com.gansdroid.promobiassignment.viewmodel.NewsListViewModel;
import com.gansdroid.promobiassignment.viewmodel.ViewModelFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class NewsListFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    NewsListAdapter newsListAdapter;

    @Inject
    public NewsListViewModel newsListViewModel;

    private NewsListFragmentBinding newsListFragmentBinding;

    @Inject
    public NewsListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialiseViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        newsListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false);
        return newsListFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiselistView();
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    private void initialiseViewModel() {
        newsListViewModel = new ViewModelProvider(this, viewModelFactory).get(NewsListViewModel.class);
        newsListViewModel.getNewsLiveData().observe(this, resource -> {
            if (resource.isLoading()) {
                Log.d("GansDroid", "data size" + resource.data.size());
            } else if (!resource.data.isEmpty()) {
                Log.d("GansDroid", "data size" + resource.data.size());
            } else {
                Log.d("GansDroid", "data empty");
            }
            newsListFragmentBinding.progressBarNewsList.setVisibility(View.INVISIBLE);
            displaydata(resource.data);
        });
        newsListViewModel.loadNewsList("all-sections", "1");

    }

    private void initialiselistView() {
        newsListAdapter.setItemClickListener((view, newsEntity) -> {
            newsListViewModel.onStop();
            EventBus.getDefault().post(newsEntity);
        });
        if(newsListAdapter.getItemCount() > 0) {
            newsListFragmentBinding.progressBarNewsList.setVisibility(View.INVISIBLE);
        }
        newsListFragmentBinding.recyclerViewNewsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsListFragmentBinding.recyclerViewNewsList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        newsListFragmentBinding.recyclerViewNewsList.setAdapter(newsListAdapter);
    }

    private void displaydata(List<NewsEntity> newsEntityList) {
        newsListAdapter.addItems(newsEntityList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        newsListFragmentBinding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsListViewModel = null;
        viewModelFactory = null;
    }
}
