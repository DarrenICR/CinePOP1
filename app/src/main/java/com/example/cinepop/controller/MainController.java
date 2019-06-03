package com.example.cinepop.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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


    public static boolean isConnectionAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public void onCreate() {

        if (MainController.isConnectionAvailable(MainActivity.getAppContext())) {

            Call<MovieResponse> call = restApiMovie.getMovies();

            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    MovieResponse restMovieResponse = response.body();
                    List<Movie> movieList = restMovieResponse.getResults();
                    view.showList(movieList);

                    view.SetCache(movieList);
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                }
            });
        }
        else {

            List<Movie> movieList = view.GetCache();
            view.showList(movieList);

            Toast.makeText(MainActivity.getAppContext(), "Aucune connexion internet disponible", Toast.LENGTH_SHORT).show();
        }
    }
}