package ktmt.rssreader.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Myth on 3/27/2018.
 */

public class DataManager {
    public static SQLiteDatabase db;
    public static void dbInit(Context c)
    {
        db = c.openOrCreateDatabase("RSSDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS DB(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " title NVARCHAR, " +
                "content NVARCHAR, " +
                "time NVARCHAR, " +
                "link NVARCHAR, " +
                "imageLink NVARCHAR, " +
                "webID INTEGER, " +
                "channelID, INTEGER);");
    }

    public static void UpdateNews(int webId, int channelID, ArrayList<NewsItem> items) {
        db.delete("DB", "webID = " + webId + "AND channelID = " + channelID, null);
        for (NewsItem item : items)
        {
            ContentValues c = new ContentValues();
            c.put("title", item.title);
            c.put("content", item.des);
            c.put("time", item.time.getTime());
            c.put("link", item.link);
            c.put("imageLink", item.imageLink);
            db.insert("DB", "", c);
        }
    }

    public static ArrayList<NewsItem> GetNewsData(int webId, int channelId)
    {
        ArrayList<NewsItem> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM DB WHERE webID = " + webId + " AND channelID = " + channelId +";", null);
        c.moveToFirst();
        int titleIndex = c.getColumnIndex("title");
        int contentIndex = c.getColumnIndex("content");
        int timeIndex = c.getColumnIndex("time");
        int linkIndex = c.getColumnIndex("link");
        int imageLinkIndex = c.getColumnIndex("imageLink");
        do {
            NewsItem item = new NewsItem();
            item.title = c.getString(titleIndex);
            item.des = c.getString(contentIndex);
            item.link = c.getString(linkIndex);
            item.time = new Date(c.getLong(timeIndex));
            item.imageLink = c.getString(imageLinkIndex);
            list.add(item);
        }
        while(c.moveToNext());
        return list;
    }
}
