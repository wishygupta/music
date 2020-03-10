package com.tech.musicapp.di.album;

import androidx.lifecycle.ViewModel;

import com.tech.musicapp.di.ViewModelKey;
import com.tech.musicapp.ui.playlist.TrackViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AlbumtViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(TrackViewModel.class)
    public abstract ViewModel bindTrackViewModel(TrackViewModel viewModel);
}
