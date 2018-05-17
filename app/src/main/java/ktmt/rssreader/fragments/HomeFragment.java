package ktmt.rssreader.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import ktmt.rssreader.R;

import static ktmt.rssreader.Data.Link.ID_24H;
import static ktmt.rssreader.Data.Link.ID_VNXPRESS;

public class HomeFragment extends BaseFragment {


    @BindView(R.id.btVnxpress)
    Button btVnxpress;
    @BindView(R.id.bt24h)
    Button bt24h;
    @BindView(R.id.btRenew)
    ImageView btRenew;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.home_fragment;
    }


    @Override
    void initView(View view) {
        onBt24hClick();
    }

    @OnClick(R.id.btVnxpress)
    public void onBtVnxpressClick() {
        if (btVnxpress.isSelected()) {
            return;
        }
        btVnxpress.setSelected(true);
        bt24h.setSelected(false);
        changeFragment(ID_VNXPRESS);
    }

    @OnClick(R.id.bt24h)
    public void onBt24hClick() {
        if (bt24h.isSelected()) {
            return;
        }
        bt24h.setSelected(true);
        btVnxpress.setSelected(false);
        changeFragment(ID_24H);
    }

    @OnClick(R.id.btRenew)
    public void onBtRenewClick() {
        LocalData.initLocalData();
        onBt24hClick();
    }

    @SuppressLint("CommitTransaction")
    private void changeFragment(int webId) {
        Log.e("Home fragment", "---------- change fragment ---------");
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.containerHome, PagersNewsFragment.newInstance(webId), null)
                .commitAllowingStateLoss();
    }
}
