package com.example.lloydsPOC.view.listing;

import com.example.lloydsPOC.models.TopAlbumsResponse;

import io.reactivex.Single;


public interface AlbumsInteractor {
    Single<TopAlbumsResponse> getTopAlbums(String userName, int limit, String apiKey);
}
