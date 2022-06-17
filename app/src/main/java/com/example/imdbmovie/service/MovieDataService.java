package com.example.imdbmovie.service;

import com.example.imdbmovie.models.MovieDbResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page);

}
