package com.bassem.lloydsPOC.view.listing;

import com.bassem.lloydsPOC.models.Artist;

import java.util.List;

public interface TopArtistsView {
    void showProgress();

    void hideProgress();

    void updateData(List<Artist> topArtists);

    void showError();
    void showEmpty();
    void hidEmpty();

}
