package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.Asysntask.GetBodyNewsAsysn;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.R;

public class DetailNewsFragment extends BaseFragment {

    private NewsItem newsItem;
    private int webID;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.web_view)
    WebView webView;

    public static DetailNewsFragment newInstance(NewsItem newsItem, int webID){
        Bundle args = new Bundle();
        DetailNewsFragment fragment = new DetailNewsFragment();
        fragment.setNewsItem(newsItem);
        fragment.setWebID(webID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.detail_news_fragment;
    }

    @Override
    void initView(View view) {
        tvTitle.setText("Tin tức");
        settingWebView();
        setUpButton(view,new int[]{R.id.btBack,R.id.btLove});
        GetBodyNewsAsysn bodyNewsAsysn = new GetBodyNewsAsysn(webView);
        bodyNewsAsysn.execute(newsItem.link,String.valueOf(webID));
    }

    private void settingWebView() {
        WebSettings settings = webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setMinimumFontSize(24);
    }

    public void setNewsItem(NewsItem newsItem) {
        this.newsItem = newsItem;
    }

    public NewsItem getNewsItem() {
        return newsItem;
    }

    public void setWebID(int webID) {
        this.webID = webID;
    }

    @OnClick(R.id.btBack)
    public void onBackPressd(){
        Log.e("onBackPressd: ", "sfsfsfs" );
        super.onBackPressd();
    }

    @OnClick(R.id.btBookMark)
    public void onBookMarkButtonClick(){
        DataManager.addItem(DataManager.BOOKMARK_LIST, Objects.requireNonNull(getActivity()),newsItem);
    }

    @OnClick(R.id.btLove)
    public void onLoveButtonClick(){
        DataManager.addItem(DataManager.LOVE_LIST, Objects.requireNonNull(getActivity()),newsItem);
    }
}
