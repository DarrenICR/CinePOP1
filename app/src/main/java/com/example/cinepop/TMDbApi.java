package com.example.cinepop;

import com.example.cinepop.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TMDbApi {

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=50fa779799df72aea4dd152254a01261&language=en-US&page=1")
    Call<MovieResponse> getMovies();

}
