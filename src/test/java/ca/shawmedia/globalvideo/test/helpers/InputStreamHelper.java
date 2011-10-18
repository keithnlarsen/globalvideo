package ca.shawmedia.globalvideo.test.helpers;

import android.graphics.Bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class InputStreamHelper {

    public static InputStream convertToInputStream(String string){
        byte[] bytes = string.getBytes();
        return new ByteArrayInputStream(bytes);
    }

    public static InputStream convertToInputStream(Bitmap bitmap){
        ByteArrayOutputStream expectedOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, expectedOutputStream);
        byte[] expectedBitmapData = expectedOutputStream.toByteArray();
        return new ByteArrayInputStream(expectedBitmapData);
    }
}
