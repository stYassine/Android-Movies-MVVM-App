package com.example.imdbmovie.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imdbmovie.R;
import com.example.imdbmovie.activities.MovieActivity;
import com.example.imdbmovie.databinding.MovieListItemBinding;
import com.example.imdbmovie.models.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private Context context;
    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);

//        String imagePath = "https://image.tmdb.org/t/p/w500"+movie.getPosterPath();
//        movie.setPosterPath(imagePath);

        holder.movieListItemBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private MovieListItemBinding movieListItemBinding;

//        public TextView movieTitle, movieRating;
//        public ImageView movieImage;

        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
//            super(itemView);
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

//            movieTitle = (TextView) itemView.findViewById(R.id.tvTitle);
//            movieTitle = (TextView) itemView.findViewById(R.id.tvRating);
//            movieImage = (ImageView) itemView.findViewById(R.id.ivMovie);

//            itemView.setOnClickListener(new View.OnClickListener() {
            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        Movie selectedMovie = movieArrayList.get(position);

                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("movie", selectedMovie);
                        context.startActivity(intent);
                    }

                }
            });

        }
    }

}
