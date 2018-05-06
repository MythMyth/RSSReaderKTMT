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
import butterknife.OnClick;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.MainActivity;
import ktmt.rssreader.R;
import ktmt.rssreader.adapters.ListRssNewsAdapter;

public class BookMarkFragment extends BaseFragment implements ListRssNewsAdapter.onClickItemListener{

    @BindView(R.id.rcvBookmark)
    RecyclerView rcvBookmark;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private ListRssNewsAdapter listRssNewsAdapter = new ListRssNewsAdapter();
    private List<NewsItem> newsItems = new ArrayList<>();

    public static BookMarkFragment newInstance(){
        Bundle args = new Bundle();
        BookMarkFragment fragment = new BookMarkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.book_mark_fragment;
    }

    @Override
    void onViewAppear() {
        newsItems = DataManager.getData(DataManager.BOOKMARK_LIST, Objects.requireNonNull(getActivity()));
        if(newsItems == null){
            return;
        }
        rcvBookmark.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvBookmark.setAdapter(listRssNewsAdapter);
        listRssNewsAdapter.setNewsItems(newsItems);
        listRssNewsAdapter.setOnClickItemListener(this);
    }

    @Override
    void initView(View view) {
        tvTitle.setText("Đánh dấu");
        setUpButton(view,new int[]{R.id.btBack,R.id.btSearch});
    }

    @OnClick(R.id.btSearch)
    public void onBtSearchClick(){
        Log.e("onBtSearchClick: ", "sádads" );
        super.onBtSearchClick();
    }

    @OnClick(R.id.btBack)
    public void onBackPressd(){
        Log.e("onBackPressd: ", "sfsfsfs" );
        super.onBackPressd();
    }

    @Override
    public void onClickItem(int position) {
        ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(DetailNewsFragment.newInstance(newsItems.get(position),newsItems.get(position).webId));
    }
}