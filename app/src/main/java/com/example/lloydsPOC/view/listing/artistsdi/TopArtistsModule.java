package com.example.lloydsPOC.view.listing.artistsdi;

import com.example.lloydsPOC.view.listing.TopARtistsPresenterImpl;
import com.example.lloydsPOC.view.listing.ArtistsInteractor;
import com.example.lloydsPOC.view.listing.ArtistsInteractorClass;
import com.example.lloydsPOC.view.listing.TopArtistsPresenter;
import com.example.lloydsPOC.view.listing.TopArtistsView;
import com.example.lloydsPOC.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TopArtistsModule {
    TopArtistsView mView;

    public TopArtistsModule(TopArtistsView view) {
        mView = view;
    }

    // provides the view to create the top artists presenter
    @Singleton
    @Provides
    public TopArtistsView providesTopArtistsView() {
        return this.mView;
    }

    // provides a converter factory to create the retrofit instance
    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory() {
        return GsonConverterFactory.create();
    }

    // provides a call adapter factory needed to integrate rxjava with retrofit
    @Singleton
    @Provides
    public CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    // provides a retrofit instance to create the top artists interactor
    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory adapter) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converter)
                .build();
    }

    // provides top artists interactor to make an instance of the presenter
    @Singleton
    @Provides
    public ArtistsInteractor providesTopArtistsInteractor(Retrofit retrofit) {
        return new ArtistsInteractorClass(retrofit);
    }

    // provides top artists presenter
    @Singleton
    @Provides
    public TopArtistsPresenter providesTopArtistsPresenter(TopArtistsView view, ArtistsInteractor interactor) {
        return new TopARtistsPresenterImpl(view, interactor);

    }
}

