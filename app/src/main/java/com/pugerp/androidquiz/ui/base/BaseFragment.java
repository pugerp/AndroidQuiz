package com.pugerp.androidquiz.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    public Activity mActivity;
    protected P presenter;
    protected abstract P createPresenter();
    protected abstract int getLayout();
    protected abstract void setup(View view);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        presenter = createPresenter();
        setup(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.onDetach();
    }
}
