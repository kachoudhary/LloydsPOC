package com.example.lloydsPOC.view.listing;


public interface TopArtistsPresenter {
    void onDestroy();

    void getUserTopArtists(String userName, int limit, String apiKey);
}
