package com.bassem.lloydsPOC.ui.topartistslisting;

import com.bassem.lloydsPOC.models.TopArtistsResponse;

import io.reactivex.Single;

public interface TopArtistsInteractor {
    Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey);
}
