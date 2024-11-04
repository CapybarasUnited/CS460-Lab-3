package com.cs460.poster;

/**
 * Basic interface to implements onPosterAction
 */
public interface PostersListener {

    /**
     * Do this when poster is selected
     * @param isSelected current selectedness
     */
    void onPosterAction(Boolean isSelected);
}
