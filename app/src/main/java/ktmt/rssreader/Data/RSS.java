package ktmt.rssreader.Data;

import android.util.Log;

import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.io.InputStreamReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ktmt.rssreader.Asysntask.GetRssListAsyn;

/**
 * Created by Myth on 3/28/2018.
 */

public class RSS {
    int webId, channelId;

    public void getRSS(final int webId, final int channelId) {
        this.channelId = channelId;
        this.webId = webId;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String link = Link.getLink(RSS.this.webId, RSS.this.channelId);
                InputSource stream = RSSReceiver.getRssStream(link);
                try {
                    SAXParserFactory fac = SAXParserFactory.newInstance();
                    SAXParser parser = fac.newSAXParser();
                    if(RSS.this.webId == 0) {
                        RSSVNParser rssParser = new RSSVNParser();
                        parser.parse(stream, rssParser);

                        DataManager.UpdateNews(RSS.this.webId, RSS.this.channelId, rssParser.newsList);
                    }
                    else
                    {
                        RSS24Parser rssParser = new RSS24Parser();
                        parser.parse(stream, rssParser);
                        DataManager.UpdateNews(RSS.this.webId, RSS.this.channelId, rssParser.newsList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static List<NewsItem> getRSSList(final int webId, final int channelId) {
        String link = Link.getLink(webId, channelId);
        GetRssListAsyn getRssListAsyn = new GetRssListAsyn();
        try {
            getRssListAsyn.execute(link).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        List<NewsItem> newsItems = getRssListAsyn.getNewsItems();
        for (NewsItem newsItem : newsItems
                ) {
            newsItem.webId = webId;
        }
        return newsItems;
    }
}
