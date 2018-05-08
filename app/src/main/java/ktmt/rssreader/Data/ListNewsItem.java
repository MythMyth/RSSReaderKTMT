package ktmt.rssreader.Data;

import java.util.ArrayList;

public class ListNewsItem {
    private ArrayList<NewsItem> newsItems = new ArrayList<>();

    public ArrayList<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(ArrayList<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    public void addItem(NewsItem newsItem) {
        newsItems.add(newsItem);
    }

    public void remove(NewsItem newsItem) {
        newsItems.remove(newsItem);
    }
}
