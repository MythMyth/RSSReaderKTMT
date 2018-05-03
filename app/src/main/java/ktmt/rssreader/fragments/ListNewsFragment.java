package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import ktmt.rssreader.Data.RSS;
import ktmt.rssreader.R;
import ktmt.rssreader.adapters.ListRssNewsAdapter;

public class ListNewsFragment extends BaseFragment {

    @BindView(R.id.rcvRssList)
    RecyclerView rcvRssList;

    private int position;
    private int webId;

    public static ListNewsFragment newInstance(int position, int webId){
        Bundle args = new Bundle();
        ListNewsFragment fragment = new ListNewsFragment();
        fragment.setPosition(position);
        fragment.setWebId(webId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayoutResource() {
        return R.layout.list_new_fragment;
    }

    @Override
    void onViewAppear() {
        setUpRecycleView();
    }

    private void setUpRecycleView() {
        ListRssNewsAdapter listRssNewsAdapter = new ListRssNewsAdapter(RSS.getRSSList(webId,position));
        rcvRssList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rcvRssList.setAdapter(listRssNewsAdapter);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

}
