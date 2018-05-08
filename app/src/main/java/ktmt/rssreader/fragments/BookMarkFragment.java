package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.MainActivity;
import ktmt.rssreader.R;
import ktmt.rssreader.adapters.ListRssNewsAdapter;

import static ktmt.rssreader.Data.DataManager.BOOKMARK_LIST;

public class BookMarkFragment extends BaseFragment implements ListRssNewsAdapter.onClickItemListener {

    @BindView(R.id.rcvBookmark)
    RecyclerView rcvBookmark;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btBack)
    ImageView btBack;
    @BindView(R.id.btRecycleBin)
    ImageView btRecycleBin;
    @BindView(R.id.btSearch)
    ImageView btSearch;
    @BindView(R.id.btCheck)
    ImageView btCheck;
    @BindView(R.id.btClose)
    ImageView btClose;
    private ListRssNewsAdapter listRssNewsAdapter = new ListRssNewsAdapter();
    private List<NewsItem> newsItems = new ArrayList<>();
    private boolean isDeleMode = false;

    public static BookMarkFragment newInstance() {
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
        Log.e("onViewAppear: ", "bookmark");
        newsItems = Objects.requireNonNull(DataManager.getData(BOOKMARK_LIST, Objects.requireNonNull(getActivity()))).getNewsItems();
        if (newsItems == null) {
            return;
        }
        rcvBookmark.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvBookmark.setAdapter(listRssNewsAdapter);
        listRssNewsAdapter.setNewsItems(newsItems);
        listRssNewsAdapter.setOnClickItemListener(this);
    }

    @Override
    void initView(View view) {
        Log.e("initView: ", "bookmark");
        tvTitle.setText("Đánh dấu");
        setUpButton(view, new int[]{R.id.btBack, R.id.btSearch, R.id.btRecycleBin});
    }

    @Override
    public void onResume() {
        Log.e("onResume: ", "Bookmark");
        super.onResume();
    }

    @OnClick(R.id.btSearch)
    public void onBtSearchClick() {
        Log.e("onBtSearchClick: ", "sádads");
        super.onBtSearchClick();
    }

    @OnClick(R.id.btBack)
    public void onBackPressd() {
        Log.e("onBackPressd: ", "sfsfsfs");
        super.onBackPressd();
    }

    @Override
    public void onClickItem(int position) {
        if(!isDeleMode) {
            ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(DetailNewsFragment.newInstance(newsItems.get(position), newsItems.get(position).webId));
            DataManager.addItem(DataManager.HISTORY_LIST, Objects.requireNonNull(getActivity()), newsItems.get(position));
        }
    }

    @Override
    public void refreshView() {
        Log.e("refreshView: ", "bookmark");
        newsItems = Objects.requireNonNull(DataManager.getData(BOOKMARK_LIST, Objects.requireNonNull(getActivity()))).getNewsItems();
        listRssNewsAdapter.setNewsItems(newsItems);
    }

    @OnClick(R.id.btRecycleBin)
    public void onBtRecycleBinClick(){
        btBack.setVisibility(View.GONE);
        listRssNewsAdapter.setIsDelete(true);
        btRecycleBin.setVisibility(View.GONE);
        btSearch.setVisibility(View.GONE);
        btCheck.setVisibility(View.VISIBLE);
        btClose.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btCheck)
    public void onAcceptDelete(){
        DataManager.deleteFromList(BOOKMARK_LIST,getActivity());
        btBack.setVisibility(View.VISIBLE);
        btRecycleBin.setVisibility(View.VISIBLE);
        btSearch.setVisibility(View.VISIBLE);
        btCheck.setVisibility(View.GONE);
        btClose.setVisibility(View.GONE);
    }

    @OnClick(R.id.btClose)
    public void onCloseClick(){
        DataManager.resetDelete();
        btBack.setVisibility(View.VISIBLE);
        btRecycleBin.setVisibility(View.VISIBLE);
        btSearch.setVisibility(View.VISIBLE);
        btCheck.setVisibility(View.GONE);
        btClose.setVisibility(View.GONE);
    }
}
