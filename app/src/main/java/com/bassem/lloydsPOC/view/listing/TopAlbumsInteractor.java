package com.bassem.lloydsPOC.view.listing;

import com.bassem.lloydsPOC.models.TopAlbumsResponse;

import io.reactivex.Single;


public interface TopAlbumsInteractor {
    Single<TopAlbumsResponse> getTopAlbums(String userName, int limit, String apiKey);
}
