package ktmt.rssreader.Data;

/**
 * Created by Myth on 5/4/2018.
 */

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Myth on 3/27/2018.
 */

public class RSS24Parser extends DefaultHandler {
    String tabName;
    boolean startParse;
    boolean parseTitle, parseDes, parseDate, parseLink;
    ArrayList<NewsItem> newsList = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tabName = qName;
        if (qName.equalsIgnoreCase("item")) {
            startParse = true;
            newsList.add(new NewsItem());
            parseDate = true;
            parseDes = true;
            parseLink = true;
            parseTitle = true;
        } else if (qName.equalsIgnoreCase("img") && startParse) {
            newsList.get(newsList.size() - 1).imageLink = attributes.getValue(attributes.getIndex("src"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!startParse)
            return;
        if (tabName.equalsIgnoreCase("title")) {
            String title = new String(ch, start, length);
            try {
                newsList.get(newsList.size() - 1).title = newsList.get(newsList.size() - 1).title + title;
            } catch (Exception e) {
                Log.e("Error", "");
            }
        } else if (tabName.equalsIgnoreCase("img")) {
            String des = new String(ch, start, length);
            try {
                newsList.get(newsList.size() - 1).des = newsList.get(newsList.size() - 1).des + des;
            } catch (Exception e) {
                Log.e("Error", "");
            }
        } else if (tabName.equalsIgnoreCase("pubdate") && parseDate) {
            String dateString = new String(ch, start, length);
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            try {
                newsList.get(newsList.size() - 1).time = format.parse(dateString);
            } catch (Exception e) {
                Log.e("", "" + e);
            }
            parseDate = false;
        } else if (tabName.equalsIgnoreCase("link") && parseLink) {
            String lnk = new String(ch, start, length);
            newsList.get(newsList.size() - 1).link = lnk;
            parseLink = false;
        }
    }
}
