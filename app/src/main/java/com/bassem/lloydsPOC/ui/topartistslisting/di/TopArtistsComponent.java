package com.bassem.lloydsPOC.ui.topartistslisting.di;

import com.bassem.lloydsPOC.ui.topartistslisting.TopArtistsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

@Singleton
@Component(modules = {TopArtistsModule.class})
public interface TopArtistsComponent {
    void inject(TopArtistsFragment target);
}
