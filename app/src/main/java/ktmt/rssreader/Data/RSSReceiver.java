package ktmt.rssreader.Data;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Myth on 3/26/2018.
 */

public class RSSReceiver {

    public static InputStream getRssStream(String link)
    {
        InputStream inputStream = null;
        try {
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            inputStream = conn.getInputStream();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return inputStream;
    }
}
