package com.pugerp.androidquiz.ui.activity.movies;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pugerp.androidquiz.R;
import com.pugerp.androidquiz.adapter.MovieAdapter;
import com.pugerp.androidquiz.model.Response;
import com.pugerp.androidquiz.model.SearchItem;
import com.pugerp.androidquiz.ui.activity.detail.DetailMovieActivity;
import com.pugerp.androidquiz.ui.base.BaseActivity;
import com.pugerp.androidquiz.utils.GridSpacingItemDecoration;

import java.util.List;

import butterknife.BindView;

public class MoviesActivity extends BaseActivity<MoviesPresenter> implements MoviesContract.View {
    private final String TAG = MoviesActivity.this.getClass().getSimpleName();

    MovieAdapter adapter;
    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;


    List<SearchItem> list;

    @Override
    protected void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    protected MoviesPresenter createPresenter() {
        return new MoviesPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.a_list_movie;
    }

    @Override
    protected void setup(Bundle savedInstanceState) {
        setToolbar();
        initListMovie();
        actionSearch();
    }

    void initListMovie() {
        showLoading().show();
        presenter.getDataMovie();
    }

    void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Movie");
    }

    void actionSearch(){
        SearchView etSearch = findViewById(R.id.et_search);
        etSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });
    }


    @Override
    public void onSuccess(Response data) {
        this.list = data.getSearch();
        adapter = new MovieAdapter(this, list, new MovieAdapter.MovieAdapterInterface<SearchItem>() {
            @Override
            public void onClickListener(SearchItem movie) {
                DetailMovieActivity.startActivity(MoviesActivity.this, movie.getImdbID());
            }
        });
        rvMovie.setLayoutManager(new GridLayoutManager(this, 2));
        rvMovie.addItemDecoration(new GridSpacingItemDecoration(this, 2, 8, true));
        rvMovie.setItemAnimator(new DefaultItemAnimator());
        rvMovie.setAdapter(adapter);
    }

    @Override
    public void onFailed(String s) {
        showToastMessage(s);
    }

}
