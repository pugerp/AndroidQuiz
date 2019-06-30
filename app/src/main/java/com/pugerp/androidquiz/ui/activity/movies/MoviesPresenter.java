package com.pugerp.androidquiz.ui.activity.movies;


import android.util.Log;

import com.pugerp.androidquiz.model.Response;
import com.pugerp.androidquiz.model.SearchItem;
import com.pugerp.androidquiz.network.NetworkCallback;
import com.pugerp.androidquiz.ui.base.BasePresenter;

public class MoviesPresenter extends BasePresenter<MoviesActivity> implements MoviesContract.Presenter {

    public MoviesPresenter() {
        super.onAttach(mView);
    }

    @Override
    public void getDataMovie() {
        addSubscribe(networkInterface.getMovie("Spider", "movie"), new NetworkCallback<Response>() {
            @Override
            public void onSuccess(Response model) {
                mView.onSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                mView.onFailed(message);
            }

            @Override
            public void onFinish() {
                mView.hideLoading();
            }
        });
    }
}
