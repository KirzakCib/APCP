package com.example.apcp;

import java.util.List;

import org.simpleframework.xml.ElementList;

import org.simpleframework.xml.Root;

@Root(name="News", strict = false)
public class NewsParser {

    @ElementList(name="Item", inline=true)
    private List<NewsItem> newsItems;

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
