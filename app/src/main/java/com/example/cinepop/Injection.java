package com.example.cinepop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injection {
    private static TMDbApi restApiMovie;

    //Singleton
    public static TMDbApi getRestApi(){
        if(restApiMovie == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            restApiMovie = retrofit.create(TMDbApi.class);
        }
        return restApiMovie;
    }
}