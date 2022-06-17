package com.example.imdbmovie.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.imdbmovie.dataSources.MovieDataSource;
import com.example.imdbmovie.dataSourcesFactories.MovieDataSourceFactory;
import com.example.imdbmovie.models.Movie;
import com.example.imdbmovie.repositories.MovieRepository;
import com.example.imdbmovie.service.MovieDataService;
import com.example.imdbmovie.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    public LiveData<MovieDataSource> movieDataSourceLiveData;

    private Executor executor;
    private LiveData<PagedList<Movie>> moviesPagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);



        MovieDataService movieDataService = RetrofitInstance.getService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService, application);
        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        moviesPagedList = new LivePagedListBuilder<>(factory, config)
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<List<Movie>> getMovies(){
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
