package ca.shawmedia.globalvideo.services;

import ca.shawmedia.globalvideo.gateways.IPlatformFeedsGateway;
import ca.shawmedia.globalvideo.models.RotatorContent;
import ca.shawmedia.globalvideo.parsers.IRotatorContentParser;
import com.google.inject.Inject;

import java.util.List;

public class ContentRotatorService implements IContentRotatorService {

    IPlatformFeedsGateway gateway;
    IRotatorContentParser parser;

    @Inject
    public ContentRotatorService(IPlatformFeedsGateway platformFeedsGateway, IRotatorContentParser rotatorContentParser) {
        gateway = platformFeedsGateway;
        parser = rotatorContentParser;
    }

    public List<RotatorContent> GetRotatorContentList() {
        return parser.ParseListFrom(gateway.GetContentRotatorFeedData());
    }
}
