package com.example.lloydsPOC.view.listing;

import com.example.lloydsPOC.models.TopAlbumsResponse;
import com.example.lloydsPOC.models.network.TopAlbumsService;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class AlbumsInteractorClass implements AlbumsInteractor {
    Retrofit mRetrofit;

    public AlbumsInteractorClass(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopAlbumsResponse> getTopAlbums(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopAlbumsService.class).getTopArtists(userName, limit, apiKey);
    }
}
