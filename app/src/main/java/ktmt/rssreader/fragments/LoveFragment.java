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

public class LoveFragment extends BaseFragment implements ListRssNewsAdapter.onClickItemListener{

    @BindView(R.id.rcvLove)
    RecyclerView rcvLove;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private ListRssNewsAdapter listRssNewsAdapter = new ListRssNewsAdapter();
    private List<NewsItem> newsItems = new ArrayList<>();

    public static LoveFragment newInstance(){
        Bundle args = new Bundle();
        LoveFragment fragment = new LoveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.love_fragment;
    }


    @Override
    void onViewAppear() {
        newsItems = Objects.requireNonNull(DataManager.getData(DataManager.LOVE_LIST, Objects.requireNonNull(getActivity()))).getNewsItems();
        if(newsItems == null){
            return;
        }
        listRssNewsAdapter.setBookmarkable(false);
        listRssNewsAdapter.setIsDelete(true);
        rcvLove.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvLove.setAdapter(listRssNewsAdapter);
        listRssNewsAdapter.setNewsItems(newsItems);
        listRssNewsAdapter.setOnClickItemListener(this);
    }

    @Override
    void initView(View view) {
        tvTitle.setText("Yêu thích");
        setUpButton(view,new int[]{R.id.btBack,R.id.btSearch,R.id.btRecycleBin});
    }

    @OnClick(R.id.btSearch)
    public void onBtSearchClick(){
        Log.e("onBtSearchClick: ", "sádads" );
        super.onBtSearchClick();
    }

    @OnClick(R.id.btBack)
    public void onBackPressd(){
        super.onBackPressd();
    }

    @Override
    public void onClickItem(int position) {
        ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(DetailNewsFragment.newInstance(newsItems.get(position),newsItems.get(position).webId));
        DataManager.addItem(DataManager.HISTORY_LIST, Objects.requireNonNull(getActivity()), newsItems.get(position));
    }
}
