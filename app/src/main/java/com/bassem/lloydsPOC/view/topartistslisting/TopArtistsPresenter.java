package com.bassem.lloydsPOC.view.topartistslisting;


public interface TopArtistsPresenter {
    void onDestroy();

    void getUserTopArtists(String userName, int limit, String apiKey);
}
