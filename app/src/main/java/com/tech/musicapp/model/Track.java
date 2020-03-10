package com.tech.musicapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("preview")
    @Expose
    String preview;
    @SerializedName("track_position")
    @Expose
    int trackNum;
    @SerializedName("artist")
    @Expose
    Artist artist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getTrackNum() {
        return trackNum + "";
    }

    public String getArtistName() {
        if (artist != null && artist.getName() != null)
            return artist.getName();
        else
            return "";
    }
}
