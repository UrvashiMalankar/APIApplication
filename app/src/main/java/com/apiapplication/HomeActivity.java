package com.apiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.apiapplication.Interface.APIInterface;
import com.apiapplication.adapter.MoviesAdapter;
import com.apiapplication.adapter.TrendingAdapter;
import com.apiapplication.model.Movies;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerViewMovies, recyclerViewTrending;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UIReferences();

        UIEventListeners();

        getNowPlayingMovies();
        getTrendingMovies();
    }

    private void getNowPlayingMovies() {

        Call<Movies> call1 = apiInterface.
                getNowPlayingMovies("def1092048c27281fdcef233de2b9878",
                "en-US",
                "1");
        call1.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();

                ArrayList<Movies.MovieList> movieLists = movies.data;

                recyclerViewMovies.setAdapter(new MoviesAdapter(HomeActivity.this, movieLists));

                Log.i("movies", movies.page+" "+movieLists.size());
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                call.cancel();
                Log.i("movies", t.toString());
            }
        });
    }

    private void getTrendingMovies() {

        Call<Movies> call1 = apiInterface.
                getTrendingMovies("def1092048c27281fdcef233de2b9878");
        call1.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();

                ArrayList<Movies.MovieList> movieLists = movies.data;

                recyclerViewTrending.setAdapter(new TrendingAdapter(HomeActivity.this, movieLists));

                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                call.cancel();
                Log.i("movies", t.toString());
            }
        });
    }

    private void UIReferences() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


        recyclerViewTrending = findViewById(R.id.recyclerViewTrending);
        recyclerViewTrending.setLayoutManager(new LinearLayoutManager(this));


        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    private void UIEventListeners() {

    }
}
