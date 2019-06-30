package com.pugerp.androidquiz.ui.activity.detail;

import com.pugerp.androidquiz.MvpView;
import com.pugerp.androidquiz.model.Movie;

public interface DetailMovieContract {
    interface View extends MvpView{
        void onSuccess(Movie data);
        void onFailed(String s);
    }
    interface Presenter{
        void getMovie(String id);
    }
}
