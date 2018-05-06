package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.R;

public class SearchFragment extends BaseFragment {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static SearchFragment newInstance(){
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
        tvTitle.setText("Tìm kiếm");
    }

    @OnClick(R.id.btBack)
    public void onBackPressd(){
        super.onBackPressd();
    }
}
