package com.tech.musicapp.ui.playlist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.tech.musicapp.model.AlbumResponse;
import com.tech.musicapp.network.AlbumTracksApi;
import com.tech.musicapp.ui.Resource;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TrackViewModel extends ViewModel {
    private static final String TAG = TrackViewModel.class.getSimpleName();
    private AlbumTracksApi tracksApi;
    private MediatorLiveData<Resource<AlbumResponse>> albumResponse;

    @Inject
    public TrackViewModel(AlbumTracksApi tracksApi) {
        this.tracksApi = tracksApi;
    }

    public void fetchTracks() {
        callApi();
    }

    private void callApi() {
        if (albumResponse == null) {
            albumResponse = new MediatorLiveData<>();
            albumResponse.setValue(Resource.loading((AlbumResponse) null));
            final LiveData<Resource<AlbumResponse>> source = LiveDataReactiveStreams.fromPublisher(
                    tracksApi.fetchTracksOfAlbum().onErrorReturn(new Function<Throwable, AlbumResponse>() {
                        @Override
                        public AlbumResponse apply(Throwable throwable) throws Exception {
                            AlbumResponse response = new AlbumResponse();
                            response.setErrorId(-1);
                            return response;
                        }
                    }).map(new Function<AlbumResponse, Resource<AlbumResponse>>() {
                        @Override
                        public Resource<AlbumResponse> apply(AlbumResponse albumResponse) throws Exception {
                            if (albumResponse.getErrorId() == -1) {
                                return Resource.error("An error occured", null);
                            }
                            return Resource.success(albumResponse);
                        }
                    }).subscribeOn(Schedulers.io())
            );
            albumResponse.addSource(source, new Observer<Resource<AlbumResponse>>() {
                @Override
                public void onChanged(Resource<AlbumResponse> albumResponseResource) {
                    albumResponse.setValue(albumResponseResource);
                    albumResponse.removeSource(source);
                }
            });
        }
    }

    public MediatorLiveData<Resource<AlbumResponse>> observeAlbum() {
        return albumResponse;
    }
}
