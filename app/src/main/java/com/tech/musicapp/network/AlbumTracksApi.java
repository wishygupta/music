package com.tech.musicapp.network;


import com.tech.musicapp.model.AlbumResponse;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlbumTracksApi {
    @GET(NetworkConstant.ALBUM_TRACKS)
    Flowable<AlbumResponse> fetchTracksOfAlbum();
}
