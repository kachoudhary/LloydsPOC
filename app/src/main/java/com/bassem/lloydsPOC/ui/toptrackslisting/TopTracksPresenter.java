package com.bassem.lloydsPOC.ui.toptrackslisting;


public interface TopTracksPresenter  {
    void onDestroy();
    void getTopTracks(String userName,int limit,String apiKey);

}
