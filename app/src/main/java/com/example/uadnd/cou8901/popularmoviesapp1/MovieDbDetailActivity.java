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

import org.w3c.dom.Text;

public class MovieDbDetailActivity extends AppCompatActivity {
    private static final String POSTER_PREFIX_URL = "http://image.tmdb.org/t/p/w185";
    private TextView tvMovieTitle;
    private TextView tvOverview;
    private ImageView ivImageView;
    private TextView tvRating;
    private TextView tvReleaseDate;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db_detail);

        tvMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        tvOverview = (TextView) findViewById(R.id.tv_overview);
        ivImageView = (ImageView) findViewById(R.id.iv_movie_poster);
        tvRating = (TextView) findViewById(R.id.tv_movie_vote_average);
        tvReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);
        context = this.getApplicationContext();
        Intent intent = getIntent();
        if(intent.hasExtra("MOVIE")) {
            String movieJson = intent.getStringExtra("MOVIE");
            //Toast.makeText(this, movieJson, Toast.LENGTH_SHORT);
            //System.out.println("Detail Activity: "+movieJson);
            MovieDbMovie movie = MovieDbJsonParser.parseMovie(movieJson);
            String url = POSTER_PREFIX_URL + movie.getPosterPath();
            String title = movie.getOriginalTitle();
            Picasso.with(context).load(url).fit().into(ivImageView);
            tvMovieTitle.setText(title);
            tvOverview.setText(movie.getOverview());
            tvRating.setText(String.valueOf(movie.getVoteAverage()));
            tvReleaseDate.setText(movie.getReleaseDate());

            System.out.println("TITLE:"+title);
            System.out.println("Overview:"+movie.getOverview());

        } else {
            String movieJson = "04-19 20:40:16.842 3563-3563/com.example.uadnd.cou8901.popularmoviesapp1 I/System.out: OnClick_Intent:{\"poster_path\":\"\\/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg\",\"adult\":false,\"overview\":\"Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.\",\"release_date\":\"1994-09-23\",\"genre_ids\":[18,80],\"id\":278,\"original_title\":\"The Shawshank Redemption\",\"original_language\":\"en\",\"title\":\"The Shawshank Redemption\",\"backdrop_path\":\"\\/j9XKiZrVeViAixVRzCta7h1VU9W.jpg\",\"popularity\":9.632657,\"vote_count\":6888,\"video\":false,\"vote_average\":8.4}\n" ;
            MovieDbMovie movie = MovieDbJsonParser.parseMovie(movieJson);
            String url = POSTER_PREFIX_URL + movie.getPosterPath();
            String title = movie.getOriginalTitle();
            Picasso.with(context).load(url).fit().into(ivImageView);
            tvMovieTitle.setText(title);
            tvOverview.setText(movie.getOverview());

        }
    }

}
