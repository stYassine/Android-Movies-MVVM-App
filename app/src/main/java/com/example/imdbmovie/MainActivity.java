package com.example.imdbmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.imdbmovie.adapters.MovieAdapter;
import com.example.imdbmovie.databinding.ActivityMainBinding;
import com.example.imdbmovie.models.Movie;
import com.example.imdbmovie.models.MovieDbResponse;
import com.example.imdbmovie.service.MovieDataService;
import com.example.imdbmovie.service.RetrofitInstance;
import com.example.imdbmovie.viewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("IMDB Popular Movies Today");

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        getPopularMovies();

//        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });

    }


    private void getPopularMovies(){


        mainActivityViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                showOnRecyclerView();
            }
        });


//        MovieDataService movieDataService = RetrofitInstance.getService();
//        String apiKey = this.getString(R.string.api_key);
//
//        Call<MovieDbResponse> call = movieDataService.getpopularMovies(apiKey);
//
//        call.enqueue(new Callback<MovieDbResponse>() {
//            @Override
//            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
//                MovieDbResponse movieDbResponse = response.body();
//
//                if(movieDbResponse != null && movieDbResponse.getMovies() != null){
//                    movies = (ArrayList<Movie>) movieDbResponse.getMovies();
//                    showOnRecyclerView();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieDbResponse> call, Throwable t) {
//
//            }
//        });

    }

    private void showOnRecyclerView(){
//        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView = activityMainBinding.moviesRecyclerView;
        movieAdapter = new MovieAdapter(this, movies);
        int spanCount = 2;

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            spanCount = 2;
        }else{
//            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            spanCount = 4;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }


}
