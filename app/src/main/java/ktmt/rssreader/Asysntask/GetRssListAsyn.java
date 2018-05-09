package ktmt.rssreader.Asysntask;

import android.os.AsyncTask;
import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.Data.RSS24Parser;
import ktmt.rssreader.Data.RSSReceiver;
import ktmt.rssreader.Data.RSSVNParser;

import static ktmt.rssreader.Data.Link.ID_24H;
import static ktmt.rssreader.Data.Link.ID_VNXPRESS;

public class GetRssListAsyn extends AsyncTask<String, Void , Void> {

    private List<NewsItem> newsItems = new ArrayList<>();
    private int webID;

    public GetRssListAsyn(int webID) {
        this.webID = webID;
    }

    @Override
    protected Void doInBackground(String... url) {
        InputSource stream = RSSReceiver.getRssStream(url[0]);
        try {
            SAXParserFactory fac = SAXParserFactory.newInstance();
            SAXParser parser = fac.newSAXParser();
            if(webID == ID_VNXPRESS) {
                RSSVNParser rssParser = new RSSVNParser();
                parser.parse(stream, rssParser);

                newsItems = rssParser.getNewsList();
            }
            else if(webID == ID_24H)
            {
                RSS24Parser rssParser = new RSS24Parser();
                parser.parse(stream, rssParser);
                newsItems = rssParser.getNewsList();
            }
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
