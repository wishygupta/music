package com.tech.musicapp.di;


import com.tech.musicapp.ui.album.AlbumFragment;
import com.tech.musicapp.ui.playlist.PlaylistFragment;
import com.tech.musicapp.ui.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract AlbumFragment contributeAlbumFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @ContributesAndroidInjector
    abstract PlaylistFragment contributePlaylistFragment();


}
