package com.puranjan.wallpaper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiUtilities {
    private static Retrofit retrofit=null;
    public static final String API="563492ad6f91700001000001f81f8bcfb309483faf4116066c1b8c67";

    public static apiInterface getApiInterface(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(apiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(apiInterface.class);
    }
}
