package com.tech.musicapp.player;

import android.content.Context;
import android.net.Uri;


import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.tech.musicapp.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayerManager {
    private Context context;
    private SimpleExoPlayer player;
    private DataSource.Factory dataSource;
    String currentPlayingUrl="";

    @Inject
    public PlayerManager(Context context) {
        this.context = context;
        initPlayer();
    }

    private void initPlayer() {
        AdaptiveTrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory();
        DefaultTrackSelector trackSelectSelector = new DefaultTrackSelector(context, trackSelectionFactory);
        DefaultLoadControl loadControl = new DefaultLoadControl();
        player = new SimpleExoPlayer.Builder(context)
                .setTrackSelector(trackSelectSelector)
                .setLoadControl(loadControl)
                .build();
        dataSource = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.getString(R.string.app_name)));
    }

    public void setUrl(String audioUrl) {
        if(currentPlayingUrl.equalsIgnoreCase(audioUrl))
            return;
        else
            currentPlayingUrl=audioUrl;
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSource, new DefaultExtractorsFactory())
                        .createMediaSource(Uri.parse(currentPlayingUrl));
        player.prepare(videoSource);
    }

    public void resumePlayer() {
        player.setPlayWhenReady(true);
    }

    public void pausePlayer() {
        player.setPlayWhenReady(false);
    }

    public void releasePlayer() {
        if (player != null) {
            player.release();
        }
    }
}
