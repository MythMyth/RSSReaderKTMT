package ktmt.rssreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ktmt.rssreader.Data.DataManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataManager.dbInit(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
