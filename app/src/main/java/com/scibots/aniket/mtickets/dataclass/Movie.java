package com.scibots.aniket.mtickets.dataclass;

/**
 * Created by aniket on 4/6/17.
 */

public class Movie {
    int id;
    String original_title;
    String overview;
    String backdrop_path;
    String release_date;
    String vote_average;

    public Movie(int id, String original_title, String overview, String backdrop_path, String release_date, String vote_average) {
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
