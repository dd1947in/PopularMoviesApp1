package com.example.uadnd.cou8901.popularmoviesapp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.okhttp.Cache;
//import com.squareup.okhttp.Interceptor;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Response;
//import com.squareup.picasso.LruCache;
//import com.squareup.picasso.NetworkPolicy;
//import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

//import java.io.IOException;
import java.util.List;


import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by dd2568 on 4/15/2017.
 */

public class MovieDbMovieAdapter extends ArrayAdapter<MovieDbMovie>{
    Context context = null;

    private static final String POSTER_PREFIX_URL = "http://image.tmdb.org/t/p/w185";
    private static final String TAG = MovieDbMovieAdapter.class.getName();

    public MovieDbMovieAdapter(Activity acontext, List<MovieDbMovie> movieDbMovies) {
          super(acontext, 0, movieDbMovies);
          context = this.getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get movie object from the ArrayAdapter for the next view
        final MovieDbMovie movieDbMovie = getItem(position);
            if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_poster);
        TextView textView  = (TextView) convertView.findViewById(R.id.original_title);
        //textView.setText(movieDbMovie.getOriginalTitle());
        String url =  POSTER_PREFIX_URL + movieDbMovie.getPosterPath(); // Poster image URL
        Picasso.with(context).load(url).fit().into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent movieDetailIntent = new Intent(context, MovieDbDetailActivity.class );
                movieDetailIntent.putExtra("MOVIE", movieDbMovie.getJson());
                //System.out.println("OnClick_Intent:"+movieDbMovie.getJson());
                Bundle bundle = new Bundle();
                bundle.putString("MOVIE", movieDbMovie.getJson());
                startActivity(context, movieDetailIntent, bundle);  // on click start detail activity
            }
        });

        return convertView;
    }


}
