package com.example.lloydsPOC.view.listing;

import com.example.lloydsPOC.models.TopArtistsResponse;
import com.example.lloydsPOC.models.network.TopArtistsService;
import io.reactivex.Single;
import retrofit2.Retrofit;


public class ArtistsInteractorClass implements ArtistsInteractor {
    Retrofit mRetrofit;

    public ArtistsInteractorClass(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopArtistsService.class).getTopArtists(userName, limit, apiKey);
    }
}
