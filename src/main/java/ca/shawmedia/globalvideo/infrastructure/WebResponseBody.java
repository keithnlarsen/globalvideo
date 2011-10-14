package ca.shawmedia.globalvideo.infrastructure;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;

public class WebResponseBody {

    private InputStream inputStream;

    public WebResponseBody(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                if (sb.length() > 0) sb.append("\n");
                sb.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public Bitmap toBitmap(){
        return BitmapFactory.decodeStream(inputStream);
    }

}
