package ktmt.rssreader.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import butterknife.ButterKnife;

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

    void setUpButton(View view, int[] buttonIdsVisiable, int[] buttonIdsHide){
        for (int buttonId : buttonIdsVisiable) {
            view.findViewById(buttonId).setVisibility(View.VISIBLE);
        }
        for (int buttonId : buttonIdsHide) {
            view.findViewById(buttonId).setVisibility(View.GONE);
        }
    }

    public void onBackPressd(){
        Objects.requireNonNull(getActivity()).onBackPressed();
//        Log.e("onBackPre: ", "sfsfsfs" );
//        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportFragmentManager().popBackStack();
    }

    public void refreshView() {

    }

}
