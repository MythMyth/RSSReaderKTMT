package ktmt.rssreader.Data;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Myth on 3/27/2018.
 */

public class RSSParser extends DefaultHandler {
    String tabName;
    boolean tabStart;
    boolean startParse;
    ArrayList<NewsItem> newsList = new ArrayList<>();
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tabName = qName;
        tabStart = true;
        if(qName.equalsIgnoreCase("item"))
        {
            startParse = true;
            newsList.add(new NewsItem());
        }
        else if(qName.equalsIgnoreCase("img"))
        {
            String imageLnk = attributes.getValue(attributes.getIndex("src"));
            newsList.get(newsList.size()-1).imageLink = imageLnk;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tabStart = !qName.equalsIgnoreCase(tabName.toLowerCase());
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(!startParse)
            return;
        if(tabName.equalsIgnoreCase("title"))
        {
            String title = new String(ch, start, length);
            newsList.get(newsList.size()-1).title = title;
        }
        else if(tabName.equalsIgnoreCase("br")&&!tabStart)
        {
            String descript = new String(ch, start, length);
            newsList.get(newsList.size()-1).des = descript;
        }
        else if(tabName.equalsIgnoreCase("pubdate"))
        {
            String dateString = new String(ch, start, length);
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZ");
            try {
                newsList.get(newsList.size()-1).time = format.parse(dateString);
            }
            catch (Exception e)
            {

            }
        }
        else if(tabName.equalsIgnoreCase("link"))
        {
            String lnk = new String(ch, start, length);
            newsList.get(newsList.size()-1).link = lnk;
        }
    }
}
