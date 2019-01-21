package com.bassem.lloydsPOC.ui.topalbumslisting.di;

import com.bassem.lloydsPOC.ui.topalbumslisting.TopAlbumsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bassem Samy on 6/17/2017.
 */
@Singleton
@Component(modules = {TopAlbumsModule.class})
public interface TopAlbumsComponent {
    void inject(TopAlbumsFragment target);
}
