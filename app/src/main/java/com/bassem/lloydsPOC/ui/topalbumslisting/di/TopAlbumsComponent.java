package com.bassem.lloydsPOC.ui.topalbumslisting.di;

import com.bassem.lloydsPOC.ui.topalbumslisting.TopAlbumsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopAlbumsModule.class})
public interface TopAlbumsComponent {
    void inject(TopAlbumsFragment target);
}
