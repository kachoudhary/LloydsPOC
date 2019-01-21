package com.bassem.lloydsPOC.ui.toptrackslisting.di;

import com.bassem.lloydsPOC.ui.toptrackslisting.TopTracksFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bassem Samy on 6/17/2017.
 */
@Singleton
@Component(modules = {TopTracksModule.class})
public interface TopTracksComponent {
    void inject(TopTracksFragment target);
}
