package com.bassem.lloydsPOC.ui.topalbumslisting;

import com.bassem.lloydsPOC.models.Album;

import java.util.List;

public interface TopAlbumsView {
    void showProgress();

    void hideProgress();

    void showError();

    void updateData(List<Album> topAlbums);

    void showEmpty();

    void hidEmpty();
}
