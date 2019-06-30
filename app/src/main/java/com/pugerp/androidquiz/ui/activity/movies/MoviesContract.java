package com.pugerp.androidquiz.ui.activity.movies;

import android.app.ProgressDialog;
import android.content.Context;

import com.pugerp.androidquiz.MvpView;
import com.pugerp.androidquiz.model.Response;

import java.util.List;

public interface MoviesContract {
    interface View extends MvpView {
        void onSuccess(Response data);

        void onFailed(String s);
    }

    interface Presenter {
        void getDataMovie();
    }
}
