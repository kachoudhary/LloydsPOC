package com.bassem.lloydsPOC.view.topalbumslisting.di;

import com.bassem.lloydsPOC.view.topalbumslisting.TopAlbumsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopAlbumsModule.class})
public interface TopAlbumsComponent {
    void inject(TopAlbumsFragment target);
}
