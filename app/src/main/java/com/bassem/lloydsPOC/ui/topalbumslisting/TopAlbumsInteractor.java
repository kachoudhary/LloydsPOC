package com.bassem.lloydsPOC.ui.topalbumslisting;

import com.bassem.lloydsPOC.models.TopAlbumsResponse;

import io.reactivex.Single;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public interface TopAlbumsInteractor {
    Single<TopAlbumsResponse> getTopAlbums(String userName, int limit, String apiKey);
}
