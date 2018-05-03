package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.Data.Link;
import ktmt.rssreader.adapters.ListTitlePagerAdapter;
import ktmt.rssreader.R;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.vpgHome)
    ViewPager viewPager;
    @BindView(R.id.btVnxpress)
    Button btVnxpress;
    @BindView(R.id.bt24h)
    Button bt24h;
    @BindView(R.id.tabNewsPaper)
    TabLayout tableLayout;

    private ListTitlePagerAdapter mAdapter;

    public static HomeFragment newInstance(){
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.home_fragment;
    }

    @Override
    void initView(View view) {
        setupPager();
        btVnxpress.callOnClick();
    }

    private void setupPager() {
        if(mAdapter == null){
            mAdapter = new ListTitlePagerAdapter(getChildFragmentManager());
        }
        viewPager.setAdapter(mAdapter);
    }

    public void getTitle(int webId) {
        mAdapter.setTitles(Link.getTitles(webId));
        mAdapter.setWhatPaper(webId);
    }

    @OnClick(R.id.btVnxpress)
    public void onBtVnxpressClick(){
        btVnxpress.setTextColor(getActivity().getResources().getColor(R.color.bottom_selected));
        bt24h.setTextColor(getActivity().getResources().getColor(R.color.bottom_unselected));
        getTitle(Link.ID_VNXPRESS);
        tableLayout.scrollTo(0,0);
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.bt24h)
    public void onBt24hClick(){
        bt24h.setTextColor(getActivity().getResources().getColor(R.color.bottom_selected));
        btVnxpress.setTextColor(getActivity().getResources().getColor(R.color.bottom_unselected));
        getTitle(Link.ID_24H);
        tableLayout.scrollTo(0,0);
        viewPager.setCurrentItem(0);
    }
}
