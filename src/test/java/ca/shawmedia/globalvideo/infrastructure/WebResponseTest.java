package ca.shawmedia.globalvideo.infrastructure;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ca.shawmedia.globalvideo.helpers.InputStreamHelper.convertToInputStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class WebResponseTest {

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void should_Return_String_When_Calling_toString() throws Exception {
        // -- Setup --
        String expectBody = "first line of data\nsecond line of data.";
        String expectedUri = "http://uri.ca";
        WebResponse response = new WebResponse(null, 200, convertToInputStream(expectBody),expectedUri);

        // -- Test --
//        String actualResult

        // -- Verify --
        assertThat(response.isSuccessful(), is(true));
        assertThat(response.getBody().toString(), is(equalTo(expectBody)));
        assertThat(response.getRequestUri(), is(equalTo(expectedUri)));
    }
}