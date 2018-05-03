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

public class LoveFragment extends BaseFragment {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

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
        DataManager.getData(DataManager.LOVE_LIST, Objects.requireNonNull(getActivity()));
    }

    @Override
    void initView(View view) {
        tvTitle.setText("Yêu thích");
        setUpButton(view,new int[]{R.id.btBack,R.id.btSearch});
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
