package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.MainActivity;
import ktmt.rssreader.R;

public class BookMarkFragment extends BaseFragment {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
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
        DataManager.getData(DataManager.BOOKMARK_LIST, Objects.requireNonNull(getActivity()));
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
}
