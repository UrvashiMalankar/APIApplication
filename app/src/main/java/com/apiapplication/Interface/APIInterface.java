package com.apiapplication.Interface;


import com.apiapplication.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
   /* Documentation: https://developers.themoviedb.org/3
    Now Playing Movies: https://developers.themoviedb.org/3/movies/get-now-playing
    Trending: https://developers.themoviedb.org/3/trending/get-trending
    Movie Detail:
    https://developers.themoviedb.org/3/movies/get-movie-details
    https://developers.themoviedb.org/3/people/get-person-movie-credits */


   //https://api.themoviedb.org/3/movie/now_playing?api_key=def1092048c27281fdcef233de2b9878&language=en-US&page=1
   @GET("/3/movie/now_playing")
   Call<Movies> getNowPlayingMovies(@Query("api_key") String api_key,
                                    @Query("language") String language,
                                    @Query("page") String page);


   //https://api.themoviedb.org/3/trending/all/day?api_key=def1092048c27281fdcef233de2b9878
   @GET("3/trending/all/day")
   Call<Movies> getTrendingMovies(@Query("api_key") String api_key);

   //https://api.themoviedb.org/3/movie/{movie_id}?api_key=def1092048c27281fdcef233de2b9878&language=en-US
   @GET("3/movie/{movie_id}")
   Call<Movies> getMovieDetail(
           @Path("movie_id") String movie_id,
           @Query("api_key") String api_key);
}
