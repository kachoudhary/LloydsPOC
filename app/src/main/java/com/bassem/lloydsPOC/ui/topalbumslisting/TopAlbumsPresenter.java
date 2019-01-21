package com.bassem.lloydsPOC.ui.topalbumslisting;

public interface TopAlbumsPresenter {
    void onDestroy();
    void getTopAlbums(String userName,int limit,String apiKey);
}
