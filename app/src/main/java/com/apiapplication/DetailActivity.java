package com.apiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apiapplication.Interface.APIInterface;
import com.apiapplication.adapter.CastAdapter;
import com.apiapplication.adapter.TrendingAdapter;
import com.apiapplication.model.Movies;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCast;
    APIInterface apiInterface;
    private TextView textViewTitle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        UIReferences();

        UIEventListeners();

        getDetail();
        getCast();
    }

    private void UIReferences() {
        apiInterface = APIClient.getClient().create(APIInterface.class);

        recyclerViewCast = findViewById(R.id.recyclerViewCast);
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        textViewTitle1 = findViewById(R.id.textViewTitle1);
    }

    private void UIEventListeners() {

    }

    private void getDetail() {

        Call<Movies> call1 = apiInterface.
                getMovieDetail("399566",
                        "def1092048c27281fdcef233de2b9878");
        call1.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();

                ArrayList<Movies.MovieDetail> movieLists = movies.genres;
                textViewTitle1.setText(movieLists.get(0).name + " "+movieLists.get(1).name);
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                call.cancel();
                Log.i("movies", t.toString());
            }
        });
    }
    private void getCast() {

        Call<Movies> call1 = apiInterface.
                getTrendingMovies("def1092048c27281fdcef233de2b9878");
        call1.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();

                ArrayList<Movies.MovieList> movieLists = movies.data;

                recyclerViewCast.setAdapter(new CastAdapter(DetailActivity.this, movieLists));

                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                call.cancel();
                Log.i("movies", t.toString());
            }
        });
    }

}
