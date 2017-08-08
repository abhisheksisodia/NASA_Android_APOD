package com.abhi.nasaapodfetcher.rest;

import com.abhi.nasaapodfetcher.models.AstroPicture;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abhisheksisodia on 2017-08-08.
 */

public interface ApiInterface {

    @GET("planetary/apod")
    Call<AstroPicture> getAPOD(@Query("api_key") String apiKey);

}

