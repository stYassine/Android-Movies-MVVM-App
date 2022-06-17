package com.example.imdbmovie.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.imdbmovie.R;
import com.example.imdbmovie.models.Movie;
import com.example.imdbmovie.models.MovieDbResponse;
import com.example.imdbmovie.service.MovieDataService;
import com.example.imdbmovie.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        final MovieDataService movieDataService = RetrofitInstance.getService();

        String api_key = application.getApplicationContext().getString(R.string.api_key);
        Call<MovieDbResponse> call = movieDataService.getPopularMovies(api_key);
        call.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                MovieDbResponse movieDbResponse = response.body();

                if(movieDbResponse != null && movieDbResponse.getMovies() != null){
                    movies = (ArrayList<Movie>) movieDbResponse.getMovies();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }
}
