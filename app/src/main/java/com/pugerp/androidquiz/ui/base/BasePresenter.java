package com.pugerp.androidquiz.ui.base;


import android.util.Log;

import com.pugerp.androidquiz.MvpView;
import com.pugerp.androidquiz.network.NetworkClient;
import com.pugerp.androidquiz.network.NetworkInterface;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends MvpView> {

    protected V mView;
    protected NetworkInterface networkInterface;
    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;

    public void onAttach(V view) {
        this.mView = view;
        networkInterface = NetworkClient.getClient().create(NetworkInterface.class);
    }

    public void onDetach() {
        mView = null;
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            Log.e("TAG", "onUnsubscribe: ");
        }
    }

    protected void addSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void stop() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
