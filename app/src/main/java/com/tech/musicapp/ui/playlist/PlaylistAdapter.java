package com.tech.musicapp.ui.playlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.musicapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wishy.gupta on 24-02-2018.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {


    public PlaylistAdapter() {

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_playlist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.songNumber)
        AppCompatTextView songNumber;
        @BindView(R.id.artistName)
        AppCompatTextView artistName;
        @BindView(R.id.songName)
        AppCompatTextView songName;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


}
