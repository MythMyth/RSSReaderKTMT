package ktmt.rssreader.fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import ktmt.rssreader.AppAction;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.Data.RSS;
import ktmt.rssreader.MainActivity;
import ktmt.rssreader.R;
import ktmt.rssreader.adapters.ListRssNewsAdapter;

public class ListNewsFragment extends BaseFragment implements ListRssNewsAdapter.onClickItemListener {

    @BindView(R.id.rcvRssList)
    RecyclerView rcvRssList;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;

    private int position;
    private int webId;
    private int scroolX;
    private int scroolY;
    private boolean getDataed = false;
    private ListRssNewsAdapter listRssNewsAdapter = new ListRssNewsAdapter();
    private List<NewsItem> newsItems = new ArrayList<>();

    public static ListNewsFragment newInstance(int position, int webId) {
        Bundle args = new Bundle();
        ListNewsFragment fragment = new ListNewsFragment();
        fragment.setPosition(position);
        fragment.setWebId(webId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.list_new_fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    void onViewAppear() {
        if (!getDataed) {
            getData();
            setUpRecycleView();
            getDataed = true;
        }
    }

    @Override
    void onViewDisappear() {
        getDataed = false;
        super.onViewDisappear();
    }

    private void getData() {
        Log.e("getData: ", String.valueOf(webId) + " " + String.valueOf(position));
        newsItems = RSS.getRSSList(webId, position);
        listRssNewsAdapter = new ListRssNewsAdapter();
        listRssNewsAdapter.setNewsItems(newsItems);
        listRssNewsAdapter.setOnClickItemListener(this);
    }

    @Override
    void initView(View view) {
        setUpSwipeRefreshLayout();
    }

    private void setUpSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {
                refreshGetData();
            }

        });
    }

    private void refreshGetData() {
        getData();
        swipeRefreshLayout.setRefreshing(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setUpRecycleView() {
        rcvRssList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvRssList.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(rcvRssList.computeVerticalScrollOffset()!= 0) {
                    Log.e("onScrollChange: ", rcvRssList.computeVerticalScrollOffset() + "");
                    scroolY = rcvRssList.computeVerticalScrollOffset();
                }
            }
        });
        rcvRssList.setAdapter(listRssNewsAdapter);
        Log.e("setUpRecycleView: ", "fdfd"+scroolY);
        rcvRssList.scrollBy(0,scroolY);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    @Override
    public void onClickItem(int position) {
        Log.e("onClickItem: ", "dfdfd");
        ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(DetailNewsFragment.newInstance(newsItems.get(position), webId));
        DataManager.addItem(DataManager.HISTORY_LIST, Objects.requireNonNull(getActivity()), newsItems.get(position));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Subscribe
    public void refresh(AppAction appAction) {
        if (appAction == AppAction.REFRESH) {
            int currentPosition = appAction.getData(Integer.class);
            if (currentPosition == position) {
                listRssNewsAdapter = new ListRssNewsAdapter();
                listRssNewsAdapter.setNewsItems(newsItems);
                listRssNewsAdapter.setOnClickItemListener(this);
                setUpRecycleView();
                getDataed = true;
            }
        }
    }
}
