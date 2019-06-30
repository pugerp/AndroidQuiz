package com.pugerp.androidquiz.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;
    protected ProgressDialog dialog;

    protected abstract void onAttachView();

    protected abstract P createPresenter();

    protected abstract int getLayout();

    protected abstract void setup(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter = createPresenter();
        setup(savedInstanceState);
        ButterKnife.bind(this);
        onAttachView();
    }

    public void showToastMessage(String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
    }

    public ProgressDialog showLoading() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please Wait..");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        return dialog;
    }

    public void hideLoading() {
        if (dialog != null)
            dialog.dismiss();
        dialog = null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetach();
        }
        presenter.onUnsubscribe();
    }
}
