package com.pluralsight.exercise3.models;

public class Movies {
    private int filmID, releaseYear, length;
    private String title, rating;

    public Movies(int filmID, String title, int releaseYear, int length, String rating) {
        this.filmID = filmID;
        this.title = title;
        this.releaseYear = releaseYear;
        this.length = length;
        this.rating = rating;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
