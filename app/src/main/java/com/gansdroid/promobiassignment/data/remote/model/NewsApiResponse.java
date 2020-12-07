package com.gansdroid.promobiassignment.data.remote.model;

import com.gansdroid.promobiassignment.data.local.entity.NewsEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResponse {

    @SerializedName("status")
    private final String status;

    @SerializedName("num_results")
    private final int num_results;

    @SerializedName("results")
    private final List<NewsEntity> results;

    public NewsApiResponse(String status, int num_results, List<NewsEntity> results) {
        this.status = status;
        this.num_results = num_results;
        this.results = results;
    }

    public List<NewsEntity> getResults() {
        return results;
    }
}
