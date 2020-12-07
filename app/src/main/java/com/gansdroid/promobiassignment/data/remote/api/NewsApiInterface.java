package com.gansdroid.promobiassignment.data.remote.api;

import com.gansdroid.promobiassignment.data.remote.model.NewsApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsApiInterface {
    @GET("mostpopular/v2/mostviewed/{section}/{period}.json")
    Observable<NewsApiResponse> getNewsList(@Path("section") String section, @Path("period") String period);
}
