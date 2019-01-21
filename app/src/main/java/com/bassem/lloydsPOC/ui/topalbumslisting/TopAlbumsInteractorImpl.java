package com.bassem.lloydsPOC.ui.topalbumslisting;

import com.bassem.lloydsPOC.models.TopAlbumsResponse;
import com.bassem.lloydsPOC.network.TopAlbumsService;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public class TopAlbumsInteractorImpl implements TopAlbumsInteractor {
    Retrofit mRetrofit;

    public TopAlbumsInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopAlbumsResponse> getTopAlbums(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopAlbumsService.class).getTopArtists(userName, limit, apiKey);
    }
}
