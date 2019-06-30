package com.pugerp.androidquiz.ui.activity.detail;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pugerp.androidquiz.R;
import com.pugerp.androidquiz.model.Movie;
import com.pugerp.androidquiz.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends BaseActivity<DetailMoviePresenter> implements DetailMovieContract.View {


    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_genre)
    TextView tvGenre;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_label_information)
    TextView tvLabelInformation;
    @BindView(R.id.tv_actor)
    TextView tvActor;
    @BindView(R.id.tv_director)
    TextView tvDirector;
    @BindView(R.id.tv_released)
    TextView tvReleased;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.tv_label_plot)
    TextView tvLabelPlot;
    @BindView(R.id.tv_plot)
    TextView tvPlot;



    public static void startActivity(BaseActivity activity, String id) {
        Intent i = new Intent(activity, DetailMovieActivity.class);
        i.putExtra("id", id);
        activity.startActivity(i);
    }

    @Override
    protected void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    protected DetailMoviePresenter createPresenter() {
        return new DetailMoviePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.a_detail_movie;
    }

    @Override
    protected void setup(Bundle savedInstanceState) {
        setToolbar();
        loadData();
    }

    void loadData() {
        showLoading().show();
        presenter.getMovie(getIntent().getStringExtra("id"));
    }

    void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Movie");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onSuccess(Movie data) {
        Glide.with(this)
                .load(data.getPoster())
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivPoster);
        tvTitle.setText(data.getTitle());
        tvGenre.setText(data.getGenre());
        tvRating.setText("Rating : " + data.getImdbRating());
        tvActor.setText("Actors : " + data.getActors());
        tvDirector.setText("Director : " + data.getDirector());
        tvReleased.setText("Released : " + data.getReleased());
        tvLanguage.setText("Language : " + data.getLanguage());
        tvCountry.setText("Country : " + data.getCountry());
        tvPlot.setText(data.getPlot());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onFailed(String s) {
        showToastMessage(s);
    }

}
