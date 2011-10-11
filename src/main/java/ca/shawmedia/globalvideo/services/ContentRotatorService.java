package ca.shawmedia.globalvideo.services;

import ca.shawmedia.globalvideo.gateways.IPlatformFeedsGateway;
import ca.shawmedia.globalvideo.models.RotatorContent;
import ca.shawmedia.globalvideo.parsers.IRotatorContentParser;

import java.util.List;

public class ContentRotatorService implements IContentRotatorService {
    private IPlatformFeedsGateway gateway;
    private IRotatorContentParser parser;

    public ContentRotatorService(IPlatformFeedsGateway platformFeedsGateway, IRotatorContentParser rotatorContentParser) {
        gateway = platformFeedsGateway;
        parser = rotatorContentParser;
    }

    public List<RotatorContent> GetRotatorContentList() {
        return parser.ParseListFrom(gateway.GetContentRotatorFeedData());
    }
}
