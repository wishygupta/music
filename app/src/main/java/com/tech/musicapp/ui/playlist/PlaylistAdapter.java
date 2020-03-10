package com.tech.musicapp.ui.playlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.musicapp.R;
import com.tech.musicapp.model.Track;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wishy.gupta on 24-02-2018.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    List<Track> albumTracks = new ArrayList<>();
    SongClick songClick;

    public void setData(List<Track> albumTracks) {
        this.albumTracks = albumTracks;
        notifyDataSetChanged();
    }

    public void setSongClick(SongClick songClick) {
        this.songClick = songClick;
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
        holder.songName.setText(albumTracks.get(position).getTitle());
        holder.songNumber.setText(albumTracks.get(position).getTrackNum());
        holder.artistName.setText(albumTracks.get(position).getArtistName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songClick != null)
                    songClick.playSong(albumTracks.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return albumTracks.size();
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

    public interface SongClick {
        void playSong(Track track);
    }

}
