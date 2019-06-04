package com.example.cinepop.controller;

import android.widget.Toast;

import com.example.cinepop.TMDbApi;
import com.example.cinepop.model.Movie;
import com.example.cinepop.model.MovieResponse;
import com.example.cinepop.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainController {

    public MainActivity view;
    private TMDbApi restApiMovie;


    public MainController(MainActivity view, TMDbApi restApiMovie) {
        this.view = view;
        this.restApiMovie = restApiMovie;
    }

    public void onCreate() {

        if (view.isConnectionAvailable()) {

            Call<MovieResponse> call = restApiMovie.getMovies();

            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    MovieResponse restMovieResponse = response.body();
                    List<Movie> movieList = restMovieResponse.getResults();
                    view.showList(movieList);

                    view.SetStock(movieList);
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                }
            });
        }
        else {

            List<Movie> movieList = view.GetStock();
            view.showList(movieList);

            Toast.makeText(MainActivity.getAppContext(), "Aucune connexion internet disponible. Chargement des données récentes.", Toast.LENGTH_SHORT).show();
        }
    }
}