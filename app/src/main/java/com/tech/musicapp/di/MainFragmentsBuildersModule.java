package com.tech.musicapp.di;


import android.app.Application;

import com.tech.musicapp.di.album.AlbumModule;
import com.tech.musicapp.di.album.AlbumtViewModelsModule;
import com.tech.musicapp.player.PlayerManager;
import com.tech.musicapp.ui.album.AlbumFragment;
import com.tech.musicapp.ui.playlist.PlaylistFragment;
import com.tech.musicapp.ui.search.SearchFragment;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract AlbumFragment contributeAlbumFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @ContributesAndroidInjector(modules = {AlbumModule.class,
            AlbumtViewModelsModule.class})
    abstract PlaylistFragment contributePlaylistFragment();

    @Provides
    static PlayerManager providePlayerManager(Application application) {
        return new PlayerManager(application.getApplicationContext());
    }

}
