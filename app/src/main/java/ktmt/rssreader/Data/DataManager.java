package ktmt.rssreader.Data;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Myth on 3/27/2018.
 */

public class DataManager {

    public static ListNewsItem listHistorys = new ListNewsItem();
    public static ListNewsItem listLoves = new ListNewsItem();
    public static ListNewsItem listBookmarks = new ListNewsItem();
    public static List<Integer> listDelete = new ArrayList<>();
    public static final String HISTORY_LIST = "historyList";
    public static final String LOVE_LIST = "loveList";
    public static final String BOOKMARK_LIST = "bookmarkList";
    public static SQLiteDatabase db;

    public static void dbInit(Context c) {
        db = c.openOrCreateDatabase("RSSDB", MODE_PRIVATE, null);
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
            db.delete("DB", "link = " + item.link, null);
            ContentValues c = new ContentValues();
            c.put("title", item.title);
            c.put("content", item.des);
            c.put("time", item.time.getTime());
            c.put("link", item.link);
            c.put("imageLink", item.getImageLink());
            db.insert("DB", "", c);
        }
    }

    public static ArrayList<NewsItem> GetNewsData(int webId, int channelId) {
        ArrayList<NewsItem> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM DB WHERE webID = " + webId + " AND channelID = " + channelId +";", null);
        if(c.getCount()==0)
            return list;
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
            item.setImageLink(c.getString(imageLinkIndex));
            list.add(item);
        }
        while (c.moveToNext());
        return list;
    }


    public static ArrayList<NewsItem> SearchData(int webId, int channelId, String searchString)
    {
        ArrayList<NewsItem> list = new ArrayList<>();
        String mutatedSearchString = searchString.trim().replace(" ", "%");
        mutatedSearchString = mutatedSearchString.replace("'", "");
        mutatedSearchString = mutatedSearchString.replace("\"", "");
        Cursor c = db.rawQuery("SELECT * FROM DB WHERE webID = " + webId + " AND channelID = " + channelId +
                "AND (title LIKE '" + mutatedSearchString + "' OR content LIKE '" + mutatedSearchString +"');", null);
        if(c.getCount()==0)
            return list;
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
            item.setImageLink(c.getString(imageLinkIndex));
            list.add(item);
        }
        while(c.moveToNext());

        return list;
    }

    public static void addItem(String typeData, Activity activity, NewsItem newsItem){
        SharedPreferences  mPrefs = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String json = "";
        switch (typeData) {
            case LOVE_LIST:
                json = saveList(newsItem, listLoves);
                break;
            case BOOKMARK_LIST:
                json = saveList(newsItem, listBookmarks);
                break;
            case HISTORY_LIST:
                json = saveList(newsItem, listHistorys);
                break;
        }
        assert json != null;
        if (json.equals("")) {
            return;
        }
        prefsEditor.putString(typeData, json);
        prefsEditor.apply();
    }

    private static String saveList(NewsItem newsItem, ListNewsItem listNewsItem) {
        if (newsItem != null) {
            for (NewsItem item : listNewsItem.getNewsItems()
                    ) {
                if (item == newsItem) {
                    return "";
                }
            }
            listNewsItem.addItem(newsItem, 0);
        }
        Log.e( "saveList: ","fdfdf" );
        return (new Gson()).toJson(listNewsItem);
    }

    public static void deleteData(String typeData, Activity activity, int position) {
        switch (typeData) {
            case LOVE_LIST:
                listLoves.remove(position);
                break;
            case BOOKMARK_LIST:
                listBookmarks.remove(position);
                break;
            case HISTORY_LIST:
                listHistorys.remove(position);
                break;
        }
        addItem(typeData, activity, null);
    }

    public static ListNewsItem getData(String typeData, Activity activity) {
        SharedPreferences mPrefs = activity.getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(typeData, "");
        if (json.equals("")) {
            return new ListNewsItem();
        }
        return (gson.fromJson(json, ListNewsItem.class));
    }

    public static void getData(Activity activity){
        listHistorys = getData(HISTORY_LIST, activity);
        listLoves = getData(LOVE_LIST, activity);
        listBookmarks = getData(BOOKMARK_LIST, activity);
    }

    public static boolean isBookmarked(String link) {
        for (NewsItem item :
                listBookmarks.getNewsItems()) {
            if(item.link.equals(link)){
                return true;
            }
        }
        return false;
    }

    public static boolean isLoved(String link) {
        for (NewsItem item :
                listLoves.getNewsItems()) {
            if(item.link.equals(link)){
                return true;
            }
        }
        return false;
    }


    public static void addItemDelete(int position) {
        listDelete.add(position);
    }

    public static void resetDelete(){
        listDelete.clear();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void deleteFromList(String whatList, Activity activity) {
        Log.e("deleteFromList: ", whatList );
        listDelete.sort(Collections.reverseOrder());
        switch (whatList){
            case LOVE_LIST:
                for (int i = 0; i < listDelete.size(); i++) {
                    Log.e("deleteFromList: ", "fdfd");
                    deleteData(LOVE_LIST,activity,listDelete.get(i));
                }
                break;
            case BOOKMARK_LIST:
                for (int i = 0; i < listDelete.size(); i++) {
                    deleteData(BOOKMARK_LIST,activity,listDelete.get(i));
                }
                break;
            case HISTORY_LIST:
                for (int i = 0; i < listDelete.size(); i++) {
                    deleteData(HISTORY_LIST,activity,listDelete.get(i));
                }
                break;
        }
        resetDelete();
    }
}
