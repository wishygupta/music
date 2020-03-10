package com.tech.musicapp.di.album;

import com.tech.musicapp.network.AlbumTracksApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AlbumModule {
    @Provides
    static AlbumTracksApi provideAppointmentApi(Retrofit retrofit) {
        return retrofit.create(AlbumTracksApi.class);
    }
}
