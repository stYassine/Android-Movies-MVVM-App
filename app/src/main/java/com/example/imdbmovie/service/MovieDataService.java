package com.example.imdbmovie.service;

import com.example.imdbmovie.models.MovieDbResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("momvie/popular")
    Call<MovieDbResponse> getpopularMovies(@Query("api_key") String apiKey);

}
