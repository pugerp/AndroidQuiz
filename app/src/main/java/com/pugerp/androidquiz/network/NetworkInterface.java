package com.pugerp.androidquiz.network;

import com.pugerp.androidquiz.model.Movie;
import com.pugerp.androidquiz.model.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkInterface {
    @GET(NetworkClient.API_KEY + "")
    Observable<Response> getMovie(@Query("s") String title, @Query("t") String type);

    @GET(NetworkClient.API_KEY + "")
    Observable<Movie> getMovieById(@Query("i") String id);
}
