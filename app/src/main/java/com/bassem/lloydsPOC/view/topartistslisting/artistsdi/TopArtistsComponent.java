package com.bassem.lloydsPOC.view.topartistslisting.artistsdi;

import com.bassem.lloydsPOC.view.topartistslisting.TopArtistsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopArtistsModule.class})
public interface TopArtistsComponent {
    void inject(TopArtistsFragment target);
}
