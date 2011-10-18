package ca.shawmedia.globalvideo.parsers;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import ca.shawmedia.globalvideo.R;
import ca.shawmedia.globalvideo.models.RotatorContent;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class RotatorContentParserTest {
    IRotatorContentParser parser;

    @Before
    public void setup() throws Exception {
        parser = new RotatorContentParser();
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
    }
}