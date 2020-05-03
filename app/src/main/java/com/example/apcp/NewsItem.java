package com.example.apcp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Item", strict=false)
public class NewsItem {

    @Element(name = "Date")
    private String date;

    @Element(name="Url")
    private String url;

    @Element(name = "Title")
    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
