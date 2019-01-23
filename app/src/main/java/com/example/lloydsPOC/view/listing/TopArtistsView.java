package com.example.lloydsPOC.view.listing;

import com.example.lloydsPOC.models.Artist;

import java.util.List;

public interface TopArtistsView {
    void showProgress();

    void hideProgress();

    void updateData(List<Artist> topArtists);

    void showError();
    void showEmpty();
    void hidEmpty();

}
