package com.gansdroid.promobiassignment;

import android.app.Activity;
import android.app.Application;

import com.gansdroid.promobiassignment.di.component.AppComponent;
import com.gansdroid.promobiassignment.di.component.DaggerAppComponent;
import com.gansdroid.promobiassignment.di.module.ApiModule;
import com.gansdroid.promobiassignment.di.module.DbModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class NewsApplication extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .apiModule(new ApiModule())
                .dbModule(new DbModule())
                .build()
                .inject(this);
    }
}
