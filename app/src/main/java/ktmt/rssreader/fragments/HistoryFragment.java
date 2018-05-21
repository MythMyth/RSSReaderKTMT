package ktmt.rssreader.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.MainActivity;
import ktmt.rssreader.R;
import ktmt.rssreader.adapters.ListRssNewsAdapter;

import static ktmt.rssreader.Data.DataManager.HISTORY_LIST;

public class HistoryFragment extends BaseFragment implements ListRssNewsAdapter.onClickItemListener{

    @BindView(R.id.rcvHistory)
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
    @BindView(R.id.btOrderSoonToLate)
    ImageView btOrderSoonToLate;
    @BindView(R.id.btOrderLateToSoon)
    ImageView btOrderLateToSoon;
    private ListRssNewsAdapter listRssNewsAdapter = new ListRssNewsAdapter();
    private List<NewsItem> newsItems = new ArrayList<>();
    private boolean isDeleMode = false;
    private boolean orderSoonToLate;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.history_fragment;
    }

    @Override
    void initView(View view) {
        tvTitle.setText("Lịch sử");
        Log.e("initView: ", "historyFrag" );
        setUpButton(view, new int[]{R.id.btOrderLateToSoon,R.id.btBack, R.id.btSearch, R.id.btRecycleBin}, new int[]{R.id.btOrderSoonToLate,R.id.btCheck,R.id.btClose,R.id.cbCheckAll});
    }

    @Override
    void onViewAppear() {
        Log.e("onViewAppear: ", "historyFrag" );
        newsItems = Objects.requireNonNull(DataManager.getData(DataManager.HISTORY_LIST, Objects.requireNonNull(getActivity()))).getNewsItems();
        if (newsItems == null) {
            return;
        }
        listRssNewsAdapter.setBookmarkable(false);
        rcvBookmark.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvBookmark.setAdapter(listRssNewsAdapter);
        listRssNewsAdapter.setNewsItems(newsItems);
        listRssNewsAdapter.setOnClickItemListener(this);
    }

    @OnClick(R.id.btSearch)
    public void onBtSearchClick() {
        Log.e("onBtSearchClick: ", "sádads");
        super.onBtSearchClick();
    }

    @OnClick(R.id.btBack)
    public void onBackPressd() {
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
    public void refreshView(FragmentActivity activity) {
        Log.e("refreshView: ", "bookmark");
        cbCheckAll.setChecked(false);
        newsItems = DataManager.getData(HISTORY_LIST, activity).getNewsItems();
        listRssNewsAdapter.setNewsItems(newsItems);
        listRssNewsAdapter.setIsDelete(false);
        listRssNewsAdapter.setIsCheckAll(false);
        isDeleMode = false;
    }

    @OnClick(R.id.btRecycleBin)
    public void onBtRecycleBinClick(){
        isDeleMode = true;
        listRssNewsAdapter.setIsDelete(true);
        setUpButton(this.getView(), new int[]{R.id.btCheck,R.id.btClose,R.id.cbCheckAll}, new int[]{R.id.btBack, R.id.btSearch, R.id.btRecycleBin});
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btCheck)
    public void onAcceptDelete(){
        isDeleMode = false;
        DataManager.deleteFromList(HISTORY_LIST,getActivity());
        setUpButton(this.getView(), new int[]{R.id.btBack, R.id.btSearch, R.id.btRecycleBin}, new int[]{R.id.btCheck,R.id.btClose,R.id.cbCheckAll});
        refreshView(getActivity());
        listRssNewsAdapter.setIsDelete(false);
    }

    @OnClick(R.id.btClose)
    public void onCloseClick(){
        isDeleMode = false;
        DataManager.resetDelete();
        listRssNewsAdapter.setIsDelete(false);
        cbCheckAll.setChecked(false);
        setUpButton(this.getView(), new int[]{R.id.btBack, R.id.btSearch, R.id.btRecycleBin}, new int[]{R.id.btCheck,R.id.btClose,R.id.cbCheckAll});
    }

    @BindView(R.id.cbCheckAll)
    CheckBox cbCheckAll;
    @OnCheckedChanged(R.id.cbCheckAll)
    public void onChangeCheckAll(){
        if(cbCheckAll.isChecked()) {
            listRssNewsAdapter.setIsCheckAll(true);
        }
    }

    @OnClick(R.id.btOrderSoonToLate)
    public void onBtOrderSoonToLate() {
        btOrderSoonToLate.setVisibility(View.GONE);
        btOrderLateToSoon.setVisibility(View.VISIBLE);
        reverseListHistory();
    }

    @OnClick(R.id.btOrderLateToSoon)
    public void setBtOrderLateToSoon(){
        btOrderLateToSoon.setVisibility(View.GONE);
        btOrderSoonToLate.setVisibility(View.VISIBLE);
        reverseListHistory();
    }

    public void reverseListHistory() {
        DataManager.reverseListHistory(getActivity());
        newsItems.clear();
        newsItems.addAll(Objects.requireNonNull(DataManager.getData(DataManager.HISTORY_LIST, Objects.requireNonNull(getActivity()))).getNewsItems());
        if (newsItems == null) {
            return;
        }
        listRssNewsAdapter.setNewsItems(newsItems);
    }
}
