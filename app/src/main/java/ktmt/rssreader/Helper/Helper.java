package ktmt.rssreader.Helper;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    @SuppressLint("SimpleDateFormat")
    public static String changeDateToString(Date date){
        return new SimpleDateFormat("HH:kk:mm dd/MM/yyyy").format(date);
    }
}
