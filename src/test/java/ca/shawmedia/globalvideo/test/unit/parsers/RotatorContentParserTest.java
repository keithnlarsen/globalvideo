package ca.shawmedia.globalvideo.test.unit.parsers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ca.shawmedia.globalvideo.R;
import ca.shawmedia.globalvideo.infrastructure.IWebClient;
import ca.shawmedia.globalvideo.infrastructure.WebResponse;
import ca.shawmedia.globalvideo.models.RotatorContent;
import ca.shawmedia.globalvideo.parsers.IRotatorContentParser;
import ca.shawmedia.globalvideo.parsers.RotatorContentParser;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;

import java.util.ArrayList;
import java.util.List;

import static ca.shawmedia.globalvideo.test.helpers.BitmapPixelMatcher.theSamePixelsAs;
import static ca.shawmedia.globalvideo.test.helpers.InputStreamHelper.convertToInputStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class RotatorContentParserTest {
    IRotatorContentParser parser;
    IWebClient mockWebClient;
    WebResponse thumbnailWebResponse;

    @Before
    public void setup() throws Exception {
        Bitmap expectedBitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.icon);
        thumbnailWebResponse = new WebResponse(null, 200, convertToInputStream(expectedBitmap), "http:any.ca");

        mockWebClient = mock(IWebClient.class);

        parser = new RotatorContentParser(mockWebClient);
    }

    @Test
    public void should_Call_Gateway_And_Pass_Contents_To_Parser_And_Return_Result() throws Exception {
        // -- Setup --
        String jsonResponse = "{\"context\":\"\",\"listInfo\":{\"itemCount\":2,\"totalCount\":2},\"items\":[{\"contentCustomData\":[{\"title\":\"Subtitles\",\"value\":\"\"},{\"title\":\"ShortDescription\",\"value\":\"Get A Room\"},{\"title\":\"Part\",\"value\":\"Clip\"},{\"title\":\"Subject\",\"value\":\"Watch Now\"},{\"title\":\"RelatedLinks\",\"value\":\"2151290131\"}],\"description\":\"Alicia and Will go toe-to-toe with his ex during a court-ordered mediation. Lisa Edelstein guest stars.\",\"thumbnailURL\":\"http://a123.g.akamai.net/f/123/68811/1d/broadcastent.download.akamai.com/68961/Canwest_Broadcast_Entertainment/TGW_iPad_rotator.jpg\",\"title\":\"The Good Wife\"},{\"contentCustomData\":[{\"title\":\"Subtitles\",\"value\":\"\"},{\"title\":\"ShortDescription\",\"value\":\"Ben Stiller Hosts\"},{\"title\":\"Part\",\"value\":\"Show\"},{\"title\":\"Subject\",\"value\":\"Watch Now\"},{\"title\":\"RelatedLinks\",\"value\":\"Saturday Night Live\"}],\"description\":\"Sketches include Lincoln Financial and Digital Short: V-Necks, plus a visit from Stefon and Zoolander.\",\"thumbnailURL\":\"http://a123.g.akamai.net/f/123/68811/1d/broadcastent.download.akamai.com/68961/Canwest_Broadcast_Entertainment/SNL_ipad_rotator.jpg\",\"title\":\"Saturday Night Live\"}],\"removedIDs\":[]}";
        List<RotatorContent> expectedContentList = new ArrayList<RotatorContent>(2);
        expectedContentList.add(new RotatorContent(
            "The Good Wife",
            "",
            "Get A Room",
            "Clip",
            "Watch Now",
            "2151290131",
            "Alicia and Will go toe-to-toe with his ex during a court-ordered mediation. Lisa Edelstein guest stars.",
            "http://a123.g.akamai.net/f/123/68811/1d/broadcastent.download.akamai.com/68961/Canwest_Broadcast_Entertainment/TGW_iPad_rotator.jpg",
            BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.icon))
        );

        expectedContentList.add(new RotatorContent(
            "Saturday Night Live",
            "",
            "Ben Stiller Hosts",
            "Show",
            "Watch Now",
            "Saturday Night Live",
            "Sketches include Lincoln Financial and Digital Short: V-Necks, plus a visit from Stefon and Zoolander.",
            "http://a123.g.akamai.net/f/123/68811/1d/broadcastent.download.akamai.com/68961/Canwest_Broadcast_Entertainment/SNL_ipad_rotator.jpg",
            BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.icon))
        );
        // -- Expectations --
        when(mockWebClient.get(Matchers.<String>any())).thenReturn(thumbnailWebResponse);

        // -- Test --
        List<RotatorContent> actualContentList = parser.ParseListFrom(jsonResponse);

        // -- Verify --
        assertThat(actualContentList.size(), equalTo(expectedContentList.size()));
        assertThat(actualContentList.get(0).getTitle(), equalTo(expectedContentList.get(0).getTitle()));
        assertThat(actualContentList.get(0).getDescription(), equalTo(expectedContentList.get(0).getDescription()));
        assertThat(actualContentList.get(0).getPart(), equalTo(expectedContentList.get(0).getPart()));
        assertThat(actualContentList.get(0).getRelatedLinks(), equalTo(expectedContentList.get(0).getRelatedLinks()));
        assertThat(actualContentList.get(0).getShortDescription(), equalTo(expectedContentList.get(0).getShortDescription()));
        assertThat(actualContentList.get(0).getSubject(), equalTo(expectedContentList.get(0).getSubject()));
        assertThat(actualContentList.get(0).getSubtitles(), equalTo(expectedContentList.get(0).getSubtitles()));
        assertThat(actualContentList.get(0).getThumbnailURL(), equalTo(expectedContentList.get(0).getThumbnailURL()));

        assertThat(actualContentList.get(0).getThumbnail(), is(theSamePixelsAs(BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.icon))));
//        assertThat(actualBitmap, is(theSamePixelsAs(expectedBitmap)));

    }
}