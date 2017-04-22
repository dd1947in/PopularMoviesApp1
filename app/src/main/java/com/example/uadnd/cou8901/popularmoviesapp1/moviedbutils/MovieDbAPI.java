/*
 * Copyright (C) 2017 dd2568 : Project submitted for Udacity Android Developer Nanodegree.
 */
package com.example.uadnd.cou8901.popularmoviesapp1.moviedbutils;

import android.support.annotation.Nullable;
import android.util.Log;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/*
 * Created by dd2568 on 4/16/2017.
 */

public class MovieDbAPI {

    /*
     * Populr & Top Rated URL prefixes
     * Movie DB V3 API Key should be populated in res/values/strings.xml
     */
    private static final String MOVIE_DB_POPULAR_URL="https://api.themoviedb.org/3/movie/popular?api_key="  ; // Ensure V3 API_KEY in res/string.xml;
    private static final String MOVIE_DB_TOP_RATED_URL="https://api.themoviedb.org/3/movie/top_rated?api_key=" ; // Ensure V3 API_KEY in res/string.xml;

    private static final String TAG = MovieDbAPI.class.getName();

    /*
     * Invoke Movie DB API with fully formed URL
     */
    public  static String getMoviesJSON(URL movieDbApiUrl)  {
        String jsonResponse = null;
        HttpURLConnection httpUrlCon = null;

        try {
            httpUrlCon = (HttpURLConnection) movieDbApiUrl.openConnection();
            InputStream inputStream = httpUrlCon.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) jsonResponse = scanner.next();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            httpUrlCon.disconnect();
        }
        //Log.i(TAG, jsonResponse);
        return jsonResponse;
    }
    /*
     * Popular Movies local API
     */
    @Nullable
    public static String getPopularMoviesJSON(String apiKey) {
        try {

            return getMoviesJSON(new URL(MOVIE_DB_POPULAR_URL+apiKey));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  null;
    }
    /*
     * Top Rated local API
     */
    @Nullable
    public static String getTopRatedMoviesJSON(String apiKey) {
        try {
            return getMoviesJSON(new URL(MOVIE_DB_TOP_RATED_URL+apiKey));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
