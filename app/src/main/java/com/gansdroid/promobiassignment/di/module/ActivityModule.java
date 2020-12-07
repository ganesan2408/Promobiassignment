package com.gansdroid.promobiassignment.di.module;

import com.gansdroid.promobiassignment.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
