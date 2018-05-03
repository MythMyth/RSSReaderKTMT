package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.Asysntask.GetBodyNewsAsysn;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.R;

public class DetailNewsFragment extends BaseFragment {

    private NewsItem newsItem;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.web_view)
    WebView webView;

    public static DetailNewsFragment newInstance(NewsItem newsItem){
        Bundle args = new Bundle();
        DetailNewsFragment fragment = new DetailNewsFragment();
        fragment.setNewsItem(newsItem);
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
        setUpButton(view,new int[]{R.id.btBack,R.id.btLove,R.id.btBookMark});
        GetBodyNewsAsysn bodyNewsAsysn = new GetBodyNewsAsysn(webView);
        bodyNewsAsysn.execute(newsItem.link,"1");
    }

    private void settingWebView() {
        WebSettings settings = webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
    }

    public void setNewsItem(NewsItem newsItem) {
        this.newsItem = newsItem;
    }

    public NewsItem getNewsItem() {
        return newsItem;
    }


}
