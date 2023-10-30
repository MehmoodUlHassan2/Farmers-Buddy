package com.ayzish.gulfjobs.smartfarmer.adapter;

public class VideoModel {
    String id;
    String title;
    String video;
    String image;
    public VideoModel(String id, String title, String video, String image) {
        this.id = id;
        this.title = title;
        this.video = video;
        this.image = image;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public String getTitle() {
        return title;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
