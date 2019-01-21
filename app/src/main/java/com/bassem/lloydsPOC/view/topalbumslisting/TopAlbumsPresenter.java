package com.bassem.lloydsPOC.view.topalbumslisting;

public interface TopAlbumsPresenter {
    void onDestroy();
    void getTopAlbums(String userName,int limit,String apiKey);
}
