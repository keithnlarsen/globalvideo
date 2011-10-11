package ca.shawmedia.globalvideo.services;

import ca.shawmedia.globalvideo.gateways.IPlatformFeedsGateway;
import ca.shawmedia.globalvideo.models.RotatorContent;
import ca.shawmedia.globalvideo.parsers.IRotatorContentParser;
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
    IContentRotatorService sut;
    IRotatorContentParser mockParser;
    IPlatformFeedsGateway mockGateway;

    @Before
    public void setup() throws Exception {
        mockGateway = mock(IPlatformFeedsGateway.class);
        mockParser = mock(IRotatorContentParser.class);

        sut = new ContentRotatorService(mockGateway, mockParser);
    }

    @Test
    public void should_Call_Gateway_And_Pass_Contents_To_Parser_And_Return_Result() throws Exception {
        // -- Setup --
        String jsonResponse = "anything";
        List<RotatorContent> contentList = new ArrayList<RotatorContent>();
        when(mockGateway.GetContentRotatorFeedData()).thenReturn(jsonResponse);
        when(mockParser.ParseListFrom(jsonResponse)).thenReturn(contentList);
        InOrder inOrder = inOrder(mockGateway, mockParser);

        // -- Test --
        List<RotatorContent> actualContentList = sut.GetRotatorContentList();

        // -- Verify --
        inOrder.verify(mockGateway).GetContentRotatorFeedData();
        inOrder.verify(mockParser).ParseListFrom(jsonResponse);
        assertThat(actualContentList, equalTo(contentList));

    }
}
