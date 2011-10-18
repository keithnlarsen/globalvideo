package ca.shawmedia.globalvideo.test.helpers;

import android.graphics.Bitmap;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public class BitmapPixelMatcher extends TypeSafeMatcher<Bitmap> {
    private Bitmap expectedBitmap;

    BitmapPixelMatcher(Bitmap expectedBitmap) {
        this.expectedBitmap = expectedBitmap;
    }

    @Factory
    public static <T> Matcher<Bitmap> theSamePixelsAs(Bitmap expectedBitmap) {
        return new BitmapPixelMatcher(expectedBitmap);
    }

    @Override
    public boolean matchesSafely(Bitmap bitmap) {
        return areBitmapsEqual(expectedBitmap, bitmap);
    }

    public void describeTo(Description description) {
        description.appendText("pixels are not the same");
    }

    private boolean areBitmapsEqual(Bitmap firstBitmap, Bitmap secondBitmap) {
        int height1 = firstBitmap.getHeight();
        int width1 = firstBitmap.getWidth();
        int height2 = secondBitmap.getHeight();
        int width2 = secondBitmap.getWidth();

        // Make sure height and width are the same
        if (height1 != height2) return false;
        if (width1 != width2) return false;

        // Make sure the pixels are the same
        for (int x = 0; x < width1; x++) {
            for (int y = 0; y < height1; y++) {
                int pixel1 = firstBitmap.getPixel(x, y);
                int pixel2 = secondBitmap.getPixel(x, y);
                if (pixel1 != pixel2) {
                    return false;
                }
            }
        }

        return true;
    }
}