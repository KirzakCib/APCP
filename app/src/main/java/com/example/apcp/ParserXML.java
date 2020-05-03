package com.example.apcp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ParserXML {

     @GET("XML_daily.asp")
     Call<Parser> loadParser(@Query("date_req") String ddmm);

     @GET("XML_News.asp")
     Call<NewsParser> newsParser();

}
