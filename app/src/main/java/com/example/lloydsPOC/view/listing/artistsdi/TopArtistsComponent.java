package com.example.lloydsPOC.view.listing.artistsdi;

import com.example.lloydsPOC.view.listing.TopArtistsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopArtistsModule.class})
public interface TopArtistsComponent {
    void inject(TopArtistsFragment target);
}
