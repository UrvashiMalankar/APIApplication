package com.apiapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apiapplication.R;
import com.apiapplication.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHolder> {

    Context context;
    ArrayList<Movies.MovieList> movieLists;

    public CastAdapter(Context context, ArrayList<Movies.MovieList> moviesArrayList) {
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
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMovie;
        TextView textViewTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewMovie = itemView.findViewById(R.id.imageViewMovie);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
