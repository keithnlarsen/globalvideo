package ca.shawmedia.globalvideo.test.unit.services;

import ca.shawmedia.globalvideo.gateways.IPlatformFeedsGateway;
import ca.shawmedia.globalvideo.models.RotatorContent;
import ca.shawmedia.globalvideo.parsers.IRotatorContentParser;
import ca.shawmedia.globalvideo.services.ContentRotatorService;
import ca.shawmedia.globalvideo.services.IContentRotatorService;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class ContentRotatorServiceTest {
    IContentRotatorService contentRotatorService;
    IRotatorContentParser mockParser;
    IPlatformFeedsGateway mockGateway;

    @Before
    public void setup() throws Exception {
        mockGateway = mock(IPlatformFeedsGateway.class);
        mockParser = mock(IRotatorContentParser.class);

        contentRotatorService = new ContentRotatorService(mockGateway, mockParser);
    }

    @Test
    public void should_Call_Gateway_And_Pass_Contents_To_Parser_And_Return_Result() throws Exception {
        // -- Setup --
        String jsonResponse = "anything";
        List<RotatorContent> contentList = new ArrayList<RotatorContent>();

        // -- Expectations --
        when(mockGateway.GetContentRotatorFeedData()).thenReturn(jsonResponse);
        when(mockParser.ParseListFrom(jsonResponse)).thenReturn(contentList);
        InOrder inOrder = inOrder(mockGateway, mockParser);

        // -- Test --
        List<RotatorContent> actualContentList = contentRotatorService.GetRotatorContentList();

        // -- Verify --
        inOrder.verify(mockGateway).GetContentRotatorFeedData();
        inOrder.verify(mockParser).ParseListFrom(jsonResponse);
        assertThat(actualContentList, equalTo(contentList));
    }
}
