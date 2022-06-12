package com.example.imdbmovie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imdbmovie.R;
import com.example.imdbmovie.databinding.ActivityMovieBinding;
import com.example.imdbmovie.models.Movie;

public class MovieActivity extends AppCompatActivity {

    public Movie movie;
//    public ImageView imageView;
//
//    private String image;
//    private TextView movieTitle, movieRating, movieReleaseDate, moviePlotsynopsis;

    private ActivityMovieBinding activityMovieBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

//        imageView = (ImageView) findViewById(R.id.IvMovieLarge);
//
//        movieTitle = (TextView) findViewById(R.id.tvTitle);
//        movieRating = (TextView) findViewById(R.id.tvRating);
//        movieReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
//        moviePlotsynopsis = (TextView) findViewById(R.id.tvPlotsynopsis);

        Intent intent = getIntent();
        if(intent.hasExtra("movie")){
            movie = intent.getParcelableExtra("movie");

            activityMovieBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());

            /// Done Via Data Binding
//            String path = "https://image.tmdb.org/t/p/w500"+movie.getPosterPath();
//
//            Glide.with(this)
//                .load(path)
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .into(imageView);
//
//
//            movieTitle.setText(movie.getTitle());
//            movieRating.setText(Double.toString(movie.getVoteAverage()));
//            movieReleaseDate.setText(movie.getReleaseDate());
//            moviePlotsynopsis.setText(movie.getOverview());

        }

    }
}
