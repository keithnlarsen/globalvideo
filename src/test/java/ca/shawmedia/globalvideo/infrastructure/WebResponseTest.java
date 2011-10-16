package ca.shawmedia.globalvideo.infrastructure;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ArrayList<String> item = new ArrayList<String>();
        item.add("test");
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("id", item);

        WebResponse response = new WebResponse(headers, 200, convertToInputStream(expectBody),expectedUri);

        // -- Test --


        // -- Verify --
        assertThat(response.isSuccessful(), is(true));
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().toString(), is(equalTo(expectBody)));
        assertThat(response.getRequestUri(), is(equalTo(expectedUri)));
        assertThat(response.getRequestHeadersFields().get("id").get(0), is(equalTo("test")));
    }
}