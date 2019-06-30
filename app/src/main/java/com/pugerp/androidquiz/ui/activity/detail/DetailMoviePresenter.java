package com.pugerp.androidquiz.ui.activity.detail;

import com.pugerp.androidquiz.model.Movie;
import com.pugerp.androidquiz.network.NetworkCallback;
import com.pugerp.androidquiz.ui.base.BaseActivity;
import com.pugerp.androidquiz.ui.base.BasePresenter;

public class DetailMoviePresenter extends BasePresenter<DetailMovieActivity> implements DetailMovieContract.Presenter {

    public DetailMoviePresenter() {
        super.onAttach(mView);
    }


    @Override
    public void getMovie(String id) {
        addSubscribe(networkInterface.getMovieById(id), new NetworkCallback<Movie>() {
            @Override
            public void onSuccess(Movie data) {
                mView.onSuccess(data);
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
