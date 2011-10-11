package ca.shawmedia.globalvideo.models;

public class RotatorContent {

    public RotatorContent(String title, String subtitles, String shortDescription, String part, String subject, String relatedLinks, String description, String thumbnailURL) {
        this.title = title;
        this.subtitles = subtitles;
        this.shortDescription = shortDescription;
        this.part = part;
        this.subject = subject;
        this.relatedLinks = relatedLinks;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
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

    String title;
    String subtitles;
    String shortDescription;
    String part;
    String subject;
    String relatedLinks;
    String description;
    String thumbnailURL;
}
