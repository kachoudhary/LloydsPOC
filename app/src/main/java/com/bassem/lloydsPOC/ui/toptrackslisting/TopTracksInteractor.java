package com.bassem.lloydsPOC.ui.toptrackslisting;

import com.bassem.lloydsPOC.models.TopTracksResponse;

import io.reactivex.Single;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public interface TopTracksInteractor {
    Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey);

}
