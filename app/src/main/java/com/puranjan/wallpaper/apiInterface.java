package com.puranjan.wallpaper;

import static com.puranjan.wallpaper.apiUtilities.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface apiInterface {
    String BASE_URL="https://api.pexels.com/v1/";

    //headers for trending
    @Headers("Authorization: "+API)
    @GET("curated")
    //this is for the trending
    Call<searchModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: "+API)
    @GET("search")
    //this is for finding the query
    Call<searchModel> getSearchImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}
