package com.abhi.nasaapodfetcher.activities;

/**
 * Created by asisodia on 2017-08-09.
 */

public interface MainView {

    void showProgressBar();

    void hideProgressBar();

    void displayBadResponseErrorMessage();

    void displayBadNetworkErrorMessage();
}
