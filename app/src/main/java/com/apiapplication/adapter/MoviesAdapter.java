package com.apiapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apiapplication.DetailActivity;
import com.apiapplication.R;
import com.apiapplication.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    Context context;
    ArrayList<Movies.MovieList> movieLists;

    public MoviesAdapter(Context context, ArrayList<Movies.MovieList> moviesArrayList) {
        this.context = context;
        this.movieLists = moviesArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_movies, viewGroup, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String title = movieLists.get(i).title;
        myViewHolder.textViewTitle.setText(title);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+
                movieLists.get(i).image).into(myViewHolder.imageViewMovie);

        myViewHolder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMovie;
        TextView textViewTitle;
        LinearLayout layoutMain;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutMain = itemView.findViewById(R.id.layoutMain);
            imageViewMovie = itemView.findViewById(R.id.imageViewMovie);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
