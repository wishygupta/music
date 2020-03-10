package com.tech.musicapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {
    @SerializedName("name")
    @Expose
    String name;

    public String getName() {
        return name;
    }
}
