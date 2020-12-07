package com.gansdroid.promobiassignment.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gansdroid.promobiassignment.viewmodel.NewsListViewModel;
import com.gansdroid.promobiassignment.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel.class)
    protected abstract ViewModel movieListViewModel(NewsListViewModel newsListViewModel);


}