package com.example.apcp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VogellaAPI {

    @GET("daily_utf8.xml")
    Call<RSSFeed> loadRSSFeed();
}
