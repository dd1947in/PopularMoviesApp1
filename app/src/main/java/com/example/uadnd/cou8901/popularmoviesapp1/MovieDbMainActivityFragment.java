/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp1;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.uadnd.cou8901.popularmoviesapp1.moviedbutils.MovieDbAPI;
import com.example.uadnd.cou8901.popularmoviesapp1.moviedbutils.MovieDbJsonParser;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDbMainActivityFragment extends Fragment {

    /*
     * Required Views and Movie DB variables
     */
    private View rootView;
    private GridView gridView;
    private MovieDbMovieAdapter movieDbMovieAdapter;
    public static MovieDbMovie [] movieDbMovies ;
    String currentMovieList = MovieDbMainActivity.POPULAR_LIST;


     public MovieDbMainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void loadMovieDbData(String movieList) {
        /*
         * Use Async Task to load Movie DB data.
         */
        new MovieDbQueryTask().execute(movieList);    //Async Task

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
         * onCreateView load data and initialize views
         */
        loadMovieDbData(currentMovieList);

        rootView = inflater.inflate(R.layout.fragment_movie_db_main, container, false);
        gridView = (GridView) rootView.findViewById(R.id.movies_grid);

        return rootView;
    }
    public class MovieDbQueryTask extends AsyncTask<String, Void, String> {
        /*
         * Async task for loading data from Movie DB
         */
        @Override
        protected String  doInBackground(String... params) {
            /*
             * Get the results in background thread
             */
            String currentMovieList = params[0];

            String moviesJSON;

            if(currentMovieList.equals(MovieDbMainActivity.POPULAR_LIST)) {
                moviesJSON = MovieDbAPI.getPopularMoviesJSON(getString(R.string.movie_db_api_key));
            } else {
                moviesJSON = MovieDbAPI.getTopRatedMoviesJSON(getString(R.string.movie_db_api_key));
            }
            return  moviesJSON ;
        }

        @Override
        protected void onPostExecute(String moviesJSON) {
            /*
             * onPostExecute , if results are available, parse the json result, create and set array
             * adapter to movie grid view of main activity
             */
            super.onPostExecute(moviesJSON);
            if(moviesJSON != null){
                movieDbMovies = MovieDbJsonParser.parseMovieList(moviesJSON);
                //movieDbMovies = null; //
                if(movieDbMovies == null) {
                    movieDbMovies = new MovieDbMovie[0];
                }
                movieDbMovieAdapter = new MovieDbMovieAdapter(getActivity(), Arrays.asList(movieDbMovies));
                gridView.setAdapter(movieDbMovieAdapter);
            }
        }
    }

}
