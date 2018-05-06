package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.Data.RSS;
import ktmt.rssreader.MainActivity;
import ktmt.rssreader.R;
import ktmt.rssreader.adapters.ListRssNewsAdapter;

public class ListNewsFragment extends BaseFragment implements ListRssNewsAdapter.onClickItemListener {

    @BindView(R.id.rcvRssList)
    RecyclerView rcvRssList;

    private int position;
    private int webId;
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

    @Override
    void onViewAppear() {
        getData();
    }

    private void getData() {
        Log.e("getData: ",String.valueOf(webId) +" "+ String.valueOf(position) );
        newsItems = RSS.getRSSList(webId, position);
        listRssNewsAdapter.setNewsItems(newsItems);
        listRssNewsAdapter.setOnClickItemListener(this);
    }

    @Override
    void initView(View view) {
        setUpRecycleView();
    }

    private void setUpRecycleView() {
        Log.e("setUpRecycleView: ", "fdfd");
        rcvRssList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvRssList.setAdapter(listRssNewsAdapter);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    @Override
    public void onClickItem(int position) {
        Log.e("onClickItem: ","dfdfd" );
        ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(DetailNewsFragment.newInstance(newsItems.get(position),webId));
    }
}
