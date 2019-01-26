package com.example.luisg.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String posterPath;
    String title;
    String overview;
    String backDropPath;
    double voteAverage;
    int movieId;
    //empty constructor need by the Parceler library
    public Movie() {
    }

    public Movie(JSONObject jsonObject) throws JSONException {  //whoever calls constructor, has responsibility to handle exception
        posterPath = jsonObject.getString("poster_path");   //be caustious of spelling key name from api
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backDropPath = jsonObject.getString("backdrop_path");
        voteAverage = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");

    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException { // do not want to create an instance of class to use method
        List<Movie> movies = new ArrayList<>();
        for (int i =0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getBackDropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backDropPath);
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    /*public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }*/

    public String getTitle() {
        return title;
    }

    /*public void setTitle(String title) {
        this.title = title;
    }*/

    public String getOverview() {
        return overview;
    }

    /*public void setOverview(String overview) {
        this.overview = overview;
    }*/

    public double getVoteAverage() {
        return voteAverage;
    }

    /*public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }*/

    public int getMovieId() {
        return movieId;
    }

   /* public void setMovieId(int movieId) {
        this.movieId = movieId;
    }*/
}
