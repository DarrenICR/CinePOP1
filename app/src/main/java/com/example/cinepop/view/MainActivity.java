package com.example.cinepop.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.cinepop.Injection;
import com.example.cinepop.R;
import com.example.cinepop.controller.MainController;
import com.example.cinepop.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;
    private static Context appContext;

    private static final String KEY = "Movie_List";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appContext = getApplicationContext();

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movies_list);

        controller = new MainController(this, Injection.getRestApi());
        controller.onCreate();
    }

    public void showList(List<Movie> movieList) {

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(movieList);
        recyclerView.setAdapter(mAdapter);
    }

    public static Context getAppContext() {
        return appContext;
    }

    public boolean isConnectionAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public void SetStock(List<Movie> movieList) {

        Gson gson = new Gson();
        String json = gson.toJson(movieList);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        sharedPreferences
                .edit()
                .putString(KEY, json)
                .apply();
    }

    public List<Movie> GetStock() {

        sharedPreferences = getPreferences(MODE_PRIVATE);

        if (sharedPreferences != null) {
            Gson gson = new Gson();
            List<Movie> movieList;

            String string = sharedPreferences.getString(KEY, null);

            Type type = new TypeToken<List<Movie>>() {
            }.getType();
            movieList = gson.fromJson(string, type);
            return movieList;
        }
        return null;
    }
}
