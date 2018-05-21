package ktmt.rssreader;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import butterknife.BindView;
import butterknife.ButterKnife;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.Data.RSS;
import ktmt.rssreader.adapters.HomePagerAdapter;
import ktmt.rssreader.fragments.BaseFragment;
import ktmt.rssreader.fragments.DetailNewsFragment;
import ktmt.rssreader.fragments.MainFragment;

public class MainActivity extends AppCompatActivity{
    public static Bus bus;
    @BindView(R.id.progressbar)
    RelativeLayout progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RSS receive = new RSS();
//        receive.getRSS(0, 0);
        bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        haveProgressbar(true);
        DataManager.dbInit(this);
        DataManager.getData(this);
        changeFragment(MainFragment.newInstance());
    }

    public void changeFragment(BaseFragment fragment) {
        if(fragment instanceof DetailNewsFragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, null)
                    .addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, null).commit();
        }

    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }

    public void haveProgressbar(boolean haveProgressBar) {
        if(haveProgressBar){
            progressbar.setVisibility(View.VISIBLE);
        } else {
            progressbar.setVisibility(View.GONE);
        }
    }

    public void onBtSearchClick() {
        ((MainFragment)getSupportFragmentManager().findFragmentById(R.id.container)).onBtSearchClick();
    }
}



