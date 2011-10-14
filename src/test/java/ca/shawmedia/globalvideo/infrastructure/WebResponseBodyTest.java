package ca.shawmedia.globalvideo.infrastructure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ca.shawmedia.globalvideo.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;

import static ca.shawmedia.globalvideo.helpers.BitmapPixelMatcher.theSamePixelsAs;
import static ca.shawmedia.globalvideo.helpers.InputStreamHelper.convertToInputStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class WebResponseBodyTest {

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void should_Return_String_When_Calling_toString() throws Exception {
        // -- Setup --
        String expectResult = "first line of data\nsecond line of data.";
        WebResponseBody responseBody = new WebResponseBody(convertToInputStream(expectResult));

        // -- Test --
        String actualResult = responseBody.toString();

        // -- Verify --
        assertThat(actualResult, is(expectResult));
    }

    @Test
    public void should_Return_Bitmap_When_Calling_toBitmap() throws Exception {
        // -- Setup --
        Bitmap expectedBitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.icon);
        WebResponseBody responseBody = new WebResponseBody(convertToInputStream(expectedBitmap));

        // -- Test --
        Bitmap actualBitmap = responseBody.toBitmap();

        // -- Verify --
        assertThat(actualBitmap, is(theSamePixelsAs(expectedBitmap)));
    }

    @Test
    public void should_Return_InputStream_When_Calling_getResponseStream() throws Exception {
        // -- Setup --
        InputStream expectedInputStream = convertToInputStream("first line of data\nsecond line of data.");
        WebResponseBody responseBody = new WebResponseBody(expectedInputStream);

        // -- Test --
        InputStream actualResult = responseBody.getInputStream();

        // -- Verify --
        assertThat(actualResult, is(expectedInputStream));
    }
}