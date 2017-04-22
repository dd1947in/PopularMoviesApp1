/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uadnd.cou8901.popularmoviesapp1.moviedbutils.MovieDbJsonParser;
import com.squareup.picasso.Picasso;



public class MovieDbDetailActivity extends AppCompatActivity {
    /*
     * Detail Activity View variables and Image URL prefix
     */
    private static final String POSTER_PREFIX_URL = "http://image.tmdb.org/t/p/w185";
    private TextView tvMovieTitle;
    private TextView tvOverview;
    private ImageView ivImageView;
    private TextView tvRating;
    private TextView tvReleaseDate;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
         * Call super class method and set content view
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db_detail);

        /*
         * Intitialize all view variables
         */
        tvMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        tvOverview = (TextView) findViewById(R.id.tv_overview);
        ivImageView = (ImageView) findViewById(R.id.iv_movie_poster);
        tvRating = (TextView) findViewById(R.id.tv_movie_vote_average);
        tvReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);
        context = this.getApplicationContext();

        /*
         * Get the intent and check if it has "MOVIE" json string in Extra
         */
        Intent intent = getIntent();
        if(intent.hasExtra("MOVIE")) {
            /*
             * Get the MOVIE from extra in Intent and parse it
             */
            String movieJson = intent.getStringExtra("MOVIE");
            MovieDbMovie movie = MovieDbJsonParser.parseMovie(movieJson);

            /*
             * Prepare movie attributes for populating views
             */
            String url = POSTER_PREFIX_URL + movie.getPosterPath();
            String title = movie.getOriginalTitle();
            Picasso.with(context).load(url).fit().into(ivImageView);
            tvMovieTitle.setText(title);
            tvOverview.setText(movie.getOverview());
            tvRating.setText(String.valueOf(movie.getVoteAverage()));
            tvReleaseDate.setText(movie.getReleaseDate());
        }
    }

}
