package com.bassem.lloydsPOC.ui.topartistslisting;

import com.bassem.lloydsPOC.models.TopArtistsResponse;

import io.reactivex.Single;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public interface TopArtistsInteractor {
    Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey);
}
