package ca.shawmedia.globalvideo.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public Bitmap getThumbnail() {
        if (thumbnail == null)
            thumbnail = downloadImage(thumbnailURL);

        return thumbnail;
    }

    String title;
    String subtitles;
    String shortDescription;
    String part;
    String subject;
    String relatedLinks;
    String description;
    String thumbnailURL;
    Bitmap thumbnail;

    private Bitmap downloadImage(String fileUrl){
        Bitmap bitmap = null;

        try {
            URL url;
            url= new URL(fileUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream is = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(is);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bitmap;
    }
}
