package com.gansdroid.promobiassignment.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.gansdroid.promobiassignment.data.Status.ERROR;
import static com.gansdroid.promobiassignment.data.Status.LOADING;
import static com.gansdroid.promobiassignment.data.Status.SUCCESS;

public class NetworkResource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private NetworkResource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> NetworkResource<T> success(@NonNull T data) {
        return new NetworkResource<>(SUCCESS, data, null);
    }

    public static <T> NetworkResource<T> error(String msg, @Nullable T data) {
        return new NetworkResource<>(ERROR, data, msg);
    }

    public static <T> NetworkResource<T> loading(@Nullable T data) {
        return new NetworkResource<>(LOADING, data, null);
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS && data != null;
    }

    public boolean isLoading() {
        return status == Status.LOADING;
    }

    public boolean isLoaded() {
        return status != Status.LOADING;
    }
}