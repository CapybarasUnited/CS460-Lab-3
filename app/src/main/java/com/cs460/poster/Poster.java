package com.cs460.poster;

/**
 * Poster class to represent new movie posters and hold the contents
 */
public class Poster {
    String name, createdBy, story;
    int image;
    Boolean isSelected = false;
    float rating;

    /**
     * non-standard constructor to assign values easily
     * @param image image number
     * @param name name of the movie
     * @param createdBy author of the movie
     * @param story brief story of the movie
     * @param rating float rating of the movie
     */
    public Poster(int image, String name, String createdBy, String story, float rating) {
        this.name = name;
        this.createdBy = createdBy;
        this.story = story;
        this.image = image;
        this.rating = rating;
    }
}
