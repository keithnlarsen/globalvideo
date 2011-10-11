package ca.shawmedia.globalvideo.parsers;

import ca.shawmedia.globalvideo.models.RotatorContent;

import java.util.List;

public interface IRotatorContentParser {
    public List<RotatorContent> ParseListFrom(String jsonData);
}
