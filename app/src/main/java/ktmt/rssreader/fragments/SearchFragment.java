package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.MainActivity;
import ktmt.rssreader.R;
import ktmt.rssreader.adapters.ListRssNewsAdapter;

public class SearchFragment extends BaseFragment implements ListRssNewsAdapter.onClickItemListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.rcvSearch)
    RecyclerView rcvSearch;
    @BindView(R.id.tvNothing)
    TextView tvNothing;

    private ListRssNewsAdapter listRssNewsAdapter = new ListRssNewsAdapter();
    private List<NewsItem> newsItems = new ArrayList<>();

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.search_fragment;
    }

    @Override
    void initView(View view) {
        tvNothing.setVisibility(View.GONE);
        tvTitle.setText("Tìm kiếm");
        setupRecycleView();
    }

    private void setupRecycleView() {
        rcvSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvSearch.setAdapter(listRssNewsAdapter);
        listRssNewsAdapter.setOnClickItemListener(this);
    }

    @OnTextChanged(R.id.edtSearch)
    public void onTextChanged() {
        Log.e("onTextChanged: ", edtSearch.getText().toString());
        search(edtSearch.getText().toString());
    }

    private void search(String key) {
        newsItems = DataManager.SearchData(key);
        if (newsItems.size() == 0) {
            tvNothing.setVisibility(View.VISIBLE);
        }
        listRssNewsAdapter.setNewsItems(newsItems);

    }

    @OnClick(R.id.btBack)
    public void onBackPressd() {
        super.onBackPressd();
    }

    @Override
    public void onClickItem(int position) {
        ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(DetailNewsFragment.newInstance(newsItems.get(position), newsItems.get(position).webId));
        DataManager.addItem(DataManager.HISTORY_LIST, Objects.requireNonNull(getActivity()), newsItems.get(position));
    }
}
