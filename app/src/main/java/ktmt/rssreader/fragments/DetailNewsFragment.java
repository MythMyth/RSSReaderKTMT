package ktmt.rssreader.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.Asysntask.GetBodyNewsAsysn;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.Data.NewsItem;
import ktmt.rssreader.GlideModule.GlideApp;
import ktmt.rssreader.R;

public class DetailNewsFragment extends BaseFragment {

    private NewsItem newsItem;
    private int webID;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.btLove)
    ImageView btLove;



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
        setUpButton(view,new int[]{R.id.btShare,R.id.btBack,R.id.btLove}, new int[]{});
        GetBodyNewsAsysn bodyNewsAsysn = new GetBodyNewsAsysn(webView);
        try {
            haveProgressBar(true);
            bodyNewsAsysn.execute(newsItem.link,String.valueOf(webID)).get();
            haveProgressBar(false);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            haveProgressBar(false);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void settingWebView() {
        WebSettings settings = webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setDefaultFontSize(28);
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

    @OnClick(R.id.btLove)
    public void onLoveButtonClick(){
        if(!DataManager.isLoved(newsItem.link)) {
            DataManager.addItem(DataManager.LOVE_LIST, Objects.requireNonNull(getActivity()), newsItem);
            GlideApp.with(this).load(R.drawable.ic_love_selected).into(btLove);
        }
    }

    @OnClick(R.id.btShare)
    public void onBtShareClick(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = newsItem.link;
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}
