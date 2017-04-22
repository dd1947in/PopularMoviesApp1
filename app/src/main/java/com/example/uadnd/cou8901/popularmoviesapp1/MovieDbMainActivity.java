/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MovieDbMainActivity extends AppCompatActivity {
    /*
     * Two different movie db queries
     */
    public static  String POPULAR_LIST = "POPULAR";
    public static  String TOP_RATED_LIST = "TOP_RATED";
    public static String currentMovieList = POPULAR_LIST ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        getMenuInflater().inflate(R.menu.menu_movie_db_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu item selected/clicked
        int id = item.getItemId();

        if(id == R.id.action_top) {
            // Show Top Rated Movies Next
            currentMovieList = TOP_RATED_LIST;
            //Toast.makeText(this, currentMovieList, Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_pop) {
            // Show Popular Movies Next
            currentMovieList = POPULAR_LIST;
            //Toast.makeText(this, currentMovieList, Toast.LENGTH_SHORT).show();
            // Show Popular Movies
        } else if (id == R.id.action_switch_list) {
            if(currentMovieList.equals(POPULAR_LIST)) currentMovieList = TOP_RATED_LIST; else currentMovieList = POPULAR_LIST;
            //Toast.makeText(this, currentMovieList, Toast.LENGTH_SHORT).show();
        }

        /*
         * Get the Fragment reference and load movie data with the current movie list
         */
        MovieDbMainActivityFragment movieGridFragment = (MovieDbMainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if(movieGridFragment != null) {
            movieGridFragment.loadMovieDbData(currentMovieList);
        }

        return super.onOptionsItemSelected(item);
    }


}
