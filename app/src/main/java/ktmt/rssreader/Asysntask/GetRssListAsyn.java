package ktmt.rssreader.Asysntask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ktmt.rssreader.Data.Link;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.Data.RSSParser;
import ktmt.rssreader.Data.RSSReceiver;

public class GetRssListAsyn extends AsyncTask<String, Void , Void> {

    private List<NewsItem> newsItems = new ArrayList<>();
    @Override
    protected Void doInBackground(String... url) {
        InputStream stream = RSSReceiver.getRssStream(url[0]);
        try {
            SAXParserFactory fac = SAXParserFactory.newInstance();
            SAXParser parser = fac.newSAXParser();
            RSSParser rssParser = new RSSParser();
            parser.parse(stream, rssParser);
            newsItems = rssParser.newsList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }
}
