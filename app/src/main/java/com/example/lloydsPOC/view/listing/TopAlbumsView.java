package com.example.lloydsPOC.view.listing;

import com.example.lloydsPOC.models.Album;

import java.util.List;

public interface TopAlbumsView {
    void showProgress();

    void hideProgress();

    void showError();

    void updateData(List<Album> topAlbums);

    void showEmpty();

    void hidEmpty();
}
