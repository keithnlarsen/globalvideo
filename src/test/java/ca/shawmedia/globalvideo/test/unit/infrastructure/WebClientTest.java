package ca.shawmedia.globalvideo.test.unit.infrastructure;

import ca.shawmedia.globalvideo.infrastructure.IWebClient;
import ca.shawmedia.globalvideo.infrastructure.WebClient;
import ca.shawmedia.globalvideo.infrastructure.WebResponse;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static ca.shawmedia.globalvideo.test.helpers.InputStreamHelper.convertToInputStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(RobolectricTestRunner.class)
public class WebClientTest {

    IWebClient sut;

    @Before
    public void setup() throws Exception {

        sut = new WebClient();
    }

    @Test
    public void should_Call_Gateway_And_Pass_Contents_To_Parser_And_Return_Result() throws Exception {
        // -- Setup --
        String expectedBody = "first line of data\nsecond line of data.";
        Robolectric.addPendingHttpResponse(200, expectedBody);
        String expectedUri = "http://uri.ca";

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("id", "test");

        WebResponse expectedResponse = new WebResponse(headers, 200, convertToInputStream(expectedBody),expectedUri);

        // -- Test --
        WebResponse actualResponse = sut.get(expectedUri);

        // -- Expectations --
        assertThat(actualResponse.getBody().toString(), is(equalTo(expectedBody)));

    }
}
