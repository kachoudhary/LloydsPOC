package com.bassem.lloydsPOC.view.toptrackslisting;


public interface TopTracksPresenter  {
    void onDestroy();
    void getTopTracks(String userName,int limit,String apiKey);

}
