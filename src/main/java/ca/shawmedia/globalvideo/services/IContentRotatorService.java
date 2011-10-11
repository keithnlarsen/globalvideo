package ca.shawmedia.globalvideo.services;

import ca.shawmedia.globalvideo.models.RotatorContent;

import java.util.List;

public interface IContentRotatorService {
    List<RotatorContent> GetRotatorContentList();
}
