package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.Data.DataManager;
import ktmt.rssreader.R;

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static HistoryFragment newInstance(){
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
        setUpButton(view,new int[]{R.id.btBack,R.id.btSearch});
    }

    @Override
    void onViewAppear() {
        DataManager.getData(DataManager.HISTORY_LIST, Objects.requireNonNull(getActivity()));
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
}
