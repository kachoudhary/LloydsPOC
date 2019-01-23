package com.example.lloydsPOC.view.listing;

public interface TopAlbumsPresenter {
    void onDestroy();
    void getTopAlbums(String userName,int limit,String apiKey);
}
