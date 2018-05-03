package ktmt.rssreader.Data;

import android.util.Log;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ktmt.rssreader.Asysntask.GetRssListAsyn;

/**
 * Created by Myth on 3/28/2018.
 */

public class RSS {
    int webId, channelId;
    public void getRSS(int webId, int channelId)
    {
        this.channelId = channelId;
        this.webId = webId;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String link = "";
                InputStream stream = RSSReceiver.getRssStream(link);
                try {
                    SAXParserFactory fac = SAXParserFactory.newInstance();
                    SAXParser parser = fac.newSAXParser();
                    RSSParser rssParser = new RSSParser();
                    parser.parse(stream, rssParser);
                    DataManager.UpdateNews(RSS.this.webId, RSS.this.channelId, rssParser.newsList);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static List<NewsItem> getRSSList(final int webId, final int channelId){
        String link = Link.getLink(webId,channelId);
        GetRssListAsyn getRssListAsyn = new GetRssListAsyn();
        try {
            getRssListAsyn.execute(link).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        List<NewsItem> newsItems = getRssListAsyn.getNewsItems();
        return newsItems;
    }
}
