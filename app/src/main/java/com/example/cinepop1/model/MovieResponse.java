package com.example.cinepop1.model;

import java.util.List;

public class MovieResponse {

    private int count;
    private String next;
    private List<Movie> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Movie> getResults() {
        return results;
    }
}
