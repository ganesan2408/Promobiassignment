package com.gansdroid.promobiassignment.di.component;

import android.app.Application;

import com.gansdroid.promobiassignment.NewsApplication;
import com.gansdroid.promobiassignment.di.module.ActivityModule;
import com.gansdroid.promobiassignment.di.module.ApiModule;
import com.gansdroid.promobiassignment.di.module.DbModule;
import com.gansdroid.promobiassignment.di.module.FragmentModule;
import com.gansdroid.promobiassignment.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {ApiModule.class, DbModule.class, ViewModelModule.class, ActivityModule.class, FragmentModule.class, AndroidInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder apiModule(ApiModule apiModule);

        @BindsInstance
        Builder dbModule(DbModule dbModule);

        AppComponent build();
    }

    void inject(NewsApplication application);
}
