package com.example.cinepop1.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cinepop1.Injection;
import com.example.cinepop1.R;
import com.example.cinepop1.controller.MainController;
import com.example.cinepop1.model.Movie;

import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.movies_list);

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
}
