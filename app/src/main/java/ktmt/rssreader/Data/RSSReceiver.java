package ktmt.rssreader.Data;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Myth on 3/26/2018.
 */

public class RSSReceiver {

    public static InputStream getRssStream(String link)
    {
        InputStream inputStream = null;
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            inputStream = conn.getInputStream();
        }
        catch (Exception e)
        {

        }
        return inputStream;
    }
}
