package com.bassem.lloydsPOC.models.network;

import com.bassem.lloydsPOC.models.TopArtistsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopArtistsService {
    @GET("?method=user.gettopartists&format=json")
    Single<TopArtistsResponse> getTopArtists(@Query("user") String user, @Query("limit") int limit, @Query("api_key") String apiKey);
}
