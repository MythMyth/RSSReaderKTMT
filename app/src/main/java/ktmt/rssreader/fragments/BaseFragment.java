package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ktmt.rssreader.MainActivity;
import ktmt.rssreader.R;

public class BaseFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        onViewAppear();
    }

    void onViewAppear() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    void initView(View view) {

    }

    int getLayoutResource() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        onViewDisappear();
        super.onDestroyView();
    }

    void onViewDisappear() {

    }

    void setUpButton(View view, int[] buttonIds){
        for (int buttonId : buttonIds) {
            view.findViewById(buttonId).setVisibility(View.VISIBLE);
        }
    }

    public void onBtSearchClick(){
        Log.e("onBtSearchClick: ", "sádads" );
//        ((MainActivity) Objects.requireNonNull(getActivity())).onBtSearchClick();
    }

    public void onBackPressd(){
        Objects.requireNonNull(getActivity()).onBackPressed();
//        Log.e("onBackPre: ", "sfsfsfs" );
//        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportFragmentManager().popBackStack();
    }

    public void refreshView() {

    }
}
