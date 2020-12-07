package com.gansdroid.promobiassignment.data;


import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private Observable<NetworkResource<ResultType>> result;

    @MainThread
    protected NetworkBoundResource() {
        Observable<NetworkResource<ResultType>> source;
        if (shouldFetch()) {
            source = createCall()
                    .subscribeOn(Schedulers.io())
                    .doOnNext(apiResponse -> saveCallResult(processResponse(apiResponse)))
                    .flatMap(apiResponse -> loadFromDb().toObservable().map(NetworkResource::success))
                    .doOnError(t -> onFetchFailed())
                    .onErrorResumeNext(t -> {
                        return loadFromDb()
                                .toObservable()
                                .map(data -> NetworkResource.error(t.getMessage(), data));

                    })
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            source = loadFromDb()
                    .toObservable()
                    .map(NetworkResource::success);
        }

        result = Observable.concat(
                loadFromDb()
                        .toObservable()
                        .map(NetworkResource::loading)
                        .take(1),
                source
        );
    }

    public Observable<NetworkResource<ResultType>> getAsObservable() {
        return result;
    }

    protected void onFetchFailed() {
    }

    @WorkerThread
    protected RequestType processResponse(NetworkResource<RequestType> response) {
        return response.data;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch();

    @NonNull
    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Observable<NetworkResource<RequestType>> createCall();
}