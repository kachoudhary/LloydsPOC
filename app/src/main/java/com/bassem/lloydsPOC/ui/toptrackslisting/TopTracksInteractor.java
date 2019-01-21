package com.bassem.lloydsPOC.ui.toptrackslisting;

import com.bassem.lloydsPOC.models.TopTracksResponse;

import io.reactivex.Single;


public interface TopTracksInteractor {
    Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey);

}
