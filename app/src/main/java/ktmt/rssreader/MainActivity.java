package ktmt.rssreader;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.adapters.HomePagerAdapter;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener{

    @BindView(R.id.vpgMain)
    ViewPager viewPager;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    private int currentTab = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataManager.dbInit(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupPager();
    }

    private void setupBottomBar() {
        bottomBar.setOnTabSelectListener(this);
    }

    private void setupPager() {
        viewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));
        viewPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setCurrentItem(currentTab);
                setupBottomBar();
            }
        }, 100);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onTabSelected(int tabId) {
        switch (tabId){
            case R.id.tab_search:
                currentTab = 0;
                break;
            case R.id.tab_history:
                currentTab = 1;
                break;
            case R.id.tab_home:
                currentTab = 2;
                break;
            case R.id.tab_love:
                currentTab = 4;
                break;
            case R.id.tab_bookmark:
                currentTab = 3;
                break;
        }
        viewPager.setCurrentItem(currentTab, false);
//        updateTitle();
    }

    public void onBtSearchClick() {
        viewPager.setCurrentItem(0);
    }
}
