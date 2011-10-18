package ca.shawmedia.globalvideo.models;

import android.graphics.Bitmap;

public class RotatorContent {

    public RotatorContent(String title, String subtitles, String shortDescription, String part, String subject, String relatedLinks, String description, String thumbnailURL, Bitmap thumbnail) {
        this.title = title;
        this.subtitles = subtitles;
        this.shortDescription = shortDescription;
        this.part = part;
        this.subject = subject;
        this.relatedLinks = relatedLinks;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getPart() {
        return part;
    }

    public String getSubject() {
        return subject;
    }

    public String getRelatedLinks() {
        return relatedLinks;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    private String title;
    private String subtitles;
    private String shortDescription;
    private String part;
    private String subject;
    private String relatedLinks;
    private String description;
    private String thumbnailURL;
    private Bitmap thumbnail;
}
