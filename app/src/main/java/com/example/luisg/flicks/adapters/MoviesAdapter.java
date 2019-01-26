package com.example.luisg.flicks.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luisg.flicks.DetailActivity;
import com.example.luisg.flicks.R;
import com.example.luisg.flicks.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("smile", "OnCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d("smile", "onBindViewHolder: " + i);
        Movie movie = movies.get(i);
        viewHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverView;
        ImageView ivPoster;
        RelativeLayout container;
        public ViewHolder(View itemView){
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverView = itemView.findViewById(R.id.tvOverView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }
        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverView.setText(movie.getOverview());


            String imageUrl = movie.getPosterPath();
            //reference the backdrop_path if phone is in landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackDropPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);

            //add click listener on the whole row
            container.setOnClickListener(new View.OnClickListener() {
               public void onClick(View view){
                   //Navigate to detail activity
                   // Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(context, DetailActivity.class);
                   //i.putExtra("title", movie.getTitle());
                   i.putExtra("movie", Parcels.wrap(movie));
                   context.startActivity(i);
               }
            });

        }
    }
}
