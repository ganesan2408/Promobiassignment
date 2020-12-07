package com.gansdroid.promobiassignment.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.gansdroid.promobiassignment.R;
import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;
import com.gansdroid.promobiassignment.ui.fragment.NewsDetailsFragment;
import com.gansdroid.promobiassignment.ui.fragment.NewsListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String CURRENT_FRAGMENT_TAG = "current_fragment";

    @Inject
    NewsListFragment newsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initView();
            getLifecycle().addObserver(new GenericLifecycleObserver() {
                @Override
                public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        if (!isChangingConfigurations()) {
                            getViewModelStore().clear();
                        }
                    }
                }
            });
        } else {
            handleOnConfigurationChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void initView() {
        replaceCurrentFragment(newsListFragment, false);
    }

    private void handleOnConfigurationChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT_TAG);
        replaceCurrentFragment(fragment, false);
    }

    public void replaceCurrentFragment(Bundle data, Fragment fragment) {
        fragment.setArguments(data);
        replaceCurrentFragment(fragment, true);
    }

    private void replaceCurrentFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, CURRENT_FRAGMENT_TAG);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(newsListFragment.getClass().getName());
        fragmentTransaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsItemDetailReceived(NewsEntity newsEntity) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("SELECTED_NEWS", newsEntity);
        replaceCurrentFragment(bundle, new NewsDetailsFragment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
