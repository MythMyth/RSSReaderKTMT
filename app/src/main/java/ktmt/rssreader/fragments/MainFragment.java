package ktmt.rssreader.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.Objects;

import butterknife.BindView;
import ktmt.rssreader.AppAction;
import ktmt.rssreader.AppConstant;
import ktmt.rssreader.R;
import ktmt.rssreader.adapters.HomePagerAdapter;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;
import static android.support.v4.view.ViewPager.SCROLL_STATE_IDLE;
import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;

public class MainFragment extends BaseFragment implements OnTabSelectListener {

    @BindView(R.id.vpgMain)
    ViewPager viewPager;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    private int currentTab = 2;

    private HomePagerAdapter homePagerAdapter;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.main_fragment;
    }

    @Override
    void initView(View view) {
        setupPager();
    }

    private void setupBottomBar() {
        bottomBar.setOnTabSelectListener(this);
    }

    private void setupPager() {
        if (homePagerAdapter == null) {
            homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        }
        viewPager.setAdapter(homePagerAdapter);
        viewPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setCurrentItem(currentTab);
                setupBottomBar();
            }
        }, 100);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position);
                ((BaseFragment)homePagerAdapter.getItem(position)).refreshView(getActivity());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public void onTabSelected(int tabId) {
        haveProgressBar(true);
        switch (tabId) {
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
        if (viewPager.getCurrentItem() != currentTab) {
            viewPager.setCurrentItem(currentTab, true);
        }
        haveProgressBar(false);
//        updateTitle();
    }

    public void onBtSearchClick() {
        viewPager.setCurrentItem(0);
    }

}
