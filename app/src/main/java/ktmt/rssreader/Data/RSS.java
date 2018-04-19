package ktmt.rssreader.Data;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

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

                }
            }
        }).start();
    }
}
