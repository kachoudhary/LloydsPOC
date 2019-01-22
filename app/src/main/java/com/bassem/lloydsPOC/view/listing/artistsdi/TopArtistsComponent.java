package com.bassem.lloydsPOC.view.listing.artistsdi;

import com.bassem.lloydsPOC.view.listing.TopArtistsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopArtistsModule.class})
public interface TopArtistsComponent {
    void inject(TopArtistsFragment target);
}
