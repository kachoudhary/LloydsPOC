package com.bassem.lloydsPOC.ui.topalbumslisting;

import com.bassem.lloydsPOC.models.TopAlbumsResponse;

import io.reactivex.Single;


public interface TopAlbumsInteractor {
    Single<TopAlbumsResponse> getTopAlbums(String userName, int limit, String apiKey);
}
