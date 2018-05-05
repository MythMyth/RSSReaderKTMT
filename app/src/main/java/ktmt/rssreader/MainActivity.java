package ktmt.rssreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.RSS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataManager.dbInit(this);
        super.onCreate(savedInstanceState);
        RSS receive = new RSS();
        receive.getRSS(0, 0);
        setContentView(R.layout.activity_main);
    }
}



