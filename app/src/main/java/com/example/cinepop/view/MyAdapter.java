package com.example.cinepop.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.cinepop.R;
import com.example.cinepop.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie> values;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView date;
        public TextView rating;
        public ImageView poster;
        public View layout;
        public Toolbar toolbar;
        public TextView overview;

        public ViewHolder(View v) {

            super(v);
            layout = v;
            title = v.findViewById(R.id.movie_title);
            date = v.findViewById(R.id.movie_release_date);
            rating = v.findViewById(R.id.movie_rating);
            poster = v.findViewById(R.id.movie_poster);
            toolbar = v.findViewById(R.id.toolbar);
            overview = v.findViewById(R.id.movie_overview);
        }
    }

    public MyAdapter(List<Movie> values) {
        this.values = values;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.movie_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Movie selectedMovie = values.get(position);
        holder.title.setText(selectedMovie.getTitle());
        holder.date.setText(selectedMovie.getReleaseDate());
        holder.rating.setText("★ " + String.valueOf(selectedMovie.getRating()));

        Context context = holder.poster.getContext();
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w500" + selectedMovie.getPosterPath())
                .into(holder.poster);

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ClickActivity.class);
                String strOverview = selectedMovie.getOverview();
                intent.putExtra("Overview",strOverview);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return values.size(); }
}