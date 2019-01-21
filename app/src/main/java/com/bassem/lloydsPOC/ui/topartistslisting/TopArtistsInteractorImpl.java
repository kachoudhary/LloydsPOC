package com.bassem.lloydsPOC.ui.topartistslisting;

import com.bassem.lloydsPOC.models.TopArtistsResponse;
import com.bassem.lloydsPOC.network.TopArtistsService;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public class TopArtistsInteractorImpl implements TopArtistsInteractor {
    Retrofit mRetrofit;

    public TopArtistsInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopArtistsService.class).getTopArtists(userName, limit, apiKey);
    }
}
