package com.bassem.lloydsPOC.view.listing.almubsdi;

import com.bassem.lloydsPOC.view.listing.TopAlbumsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopAlbumsModule.class})
public interface TopAlbumsComponent {
    void inject(TopAlbumsFragment target);
}
