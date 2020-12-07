package com.gansdroid.promobiassignment.di.module;

import com.gansdroid.promobiassignment.ui.fragment.NewsDetailsFragment;
import com.gansdroid.promobiassignment.ui.fragment.NewsListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract NewsListFragment contributeNewsListFragment();

    @ContributesAndroidInjector
    abstract NewsDetailsFragment contributeNewsDetailsFragment();
}