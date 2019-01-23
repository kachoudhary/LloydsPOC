package com.example.lloydsPOC.view.listing.almubsdi;

import com.example.lloydsPOC.view.listing.TopAlbumsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopAlbumsModule.class})
public interface TopAlbumsComponent {
    void inject(TopAlbumsFragment target);
}
