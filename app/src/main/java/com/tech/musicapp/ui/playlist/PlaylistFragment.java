package com.tech.musicapp.ui.playlist;


import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tech.musicapp.R;
import com.tech.musicapp.Utils;
import com.tech.musicapp.model.AlbumResponse;
import com.tech.musicapp.model.Track;
import com.tech.musicapp.player.PlayerManager;
import com.tech.musicapp.ui.Resource;
import com.tech.musicapp.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends DaggerFragment implements AudioManager.OnAudioFocusChangeListener, PlaylistAdapter.SongClick {

    @Inject
    PlayerManager playerManager;
    @BindView(R.id.trakcsView)
    RecyclerView trakcsView;
    @BindView(R.id.songName)
    TextView songName;
    @BindView(R.id.artistName)
    TextView artistName;
    @Inject
    ViewModelProviderFactory providerFactory;
    private TrackViewModel viewModel;
    Dialog progressDialog;
    private static final String TAG = PlaylistFragment.class.getSimpleName();
    private PlaylistAdapter adapter;

    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        progressDialog = Utils.appLoader(getContext());
        viewModel = ViewModelProviders.of(this, providerFactory).get(TrackViewModel.class);
        init();
        observeTracks();

    }

    private void init() {
        trakcsView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new PlaylistAdapter();
        adapter.setSongClick(this);
        trakcsView.setAdapter(adapter);

    }

    private void observeTracks() {
        viewModel.fetchTracks();
        viewModel.observeAlbum().removeObservers(getViewLifecycleOwner());
        viewModel.observeAlbum().observe(getViewLifecycleOwner(), new Observer<Resource<AlbumResponse>>() {
            @Override
            public void onChanged(Resource<AlbumResponse> albumResponseResource) {
                if (albumResponseResource != null) {
                    switch (albumResponseResource.status) {
                        case ERROR:
                            showProgress(false);
                            Log.e(TAG, albumResponseResource.message);
                            break;
                        case LOADING:
                            showProgress(true);
                            break;
                        case SUCCESS:
                            showProgress(false);
                            adapter.setData(albumResponseResource.data.getAlbumTracks());
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void playSong(Track track) {
        songName.setText(track.getTitle());
        artistName.setText(track.getArtistName());
        if (track.getPreview() != null) {
            requestAudioFocus();
            playerManager.setUrl(track.getPreview());
            playerManager.resumePlayer();
        }
    }

    private void showProgress(boolean visible) {
        if (visible)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    private void abandonAudioFocus() {
        AudioManager am = (AudioManager) getActivity().getApplicationContext()
                .getSystemService(Context.AUDIO_SERVICE);
        am.abandonAudioFocus(this);

    }

    private void requestAudioFocus() {
        AudioManager am = (AudioManager) getActivity().getApplicationContext()
                .getSystemService(Context.AUDIO_SERVICE);
        am.requestAudioFocus(this,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                break;

            case AudioManager.AUDIOFOCUS_GAIN:
                break;

            case AudioManager.AUDIOFOCUS_LOSS:
                playerManager.pausePlayer();
                abandonAudioFocus();
                break;

            default:
                break;
        }
    }
}
