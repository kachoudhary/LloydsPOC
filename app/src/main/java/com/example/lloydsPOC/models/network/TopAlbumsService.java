package com.example.lloydsPOC.models.network;

import com.example.lloydsPOC.models.TopAlbumsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopAlbumsService {
    @GET("?method=user.gettopalbums&format=json")
    Single<TopAlbumsResponse> getTopArtists(@Query("user") String user, @Query("limit") int limit, @Query("api_key") String apiKey);

}
