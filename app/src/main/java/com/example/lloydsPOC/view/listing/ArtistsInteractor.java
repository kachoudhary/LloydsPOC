package com.example.lloydsPOC.view.listing;

import com.example.lloydsPOC.models.TopArtistsResponse;

import io.reactivex.Single;

public interface ArtistsInteractor {
    Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey);
}
