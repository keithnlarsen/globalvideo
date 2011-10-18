package ca.shawmedia.globalvideo.parsers;

import android.graphics.Bitmap;
import ca.shawmedia.globalvideo.infrastructure.IWebClient;
import ca.shawmedia.globalvideo.infrastructure.WebResponse;
import ca.shawmedia.globalvideo.models.RotatorContent;
import com.google.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RotatorContentParser implements IRotatorContentParser{

    IWebClient webClient;

    @Inject
    public RotatorContentParser(IWebClient webClient) {
        this.webClient = webClient;
    }

    public List<RotatorContent> ParseListFrom(String jsonData) {
        List<RotatorContent> rotatorContentList = new ArrayList<RotatorContent>();
        try {
            JSONArray contentItems = new JSONObject(jsonData).getJSONArray("items");

            for (int i = 0; i < contentItems.length(); i ++) {
                rotatorContentList.add(ParseContentItem(contentItems.getJSONObject(i)));
            }
        }
        catch (JSONException ex){
            // Log exception
        }

        return rotatorContentList;
    }

    public RotatorContent ParseContentItem(JSONObject content) throws JSONException {
        Map customContent = ParseCustomContent(content.getJSONArray("contentCustomData"));

        return new RotatorContent(
            content.getString("title"),
            customContent.get("Subtitles").toString(),
            customContent.get("ShortDescription").toString(),
            customContent.get("Part").toString(),
            customContent.get("Subject").toString(),
            customContent.get("RelatedLinks").toString(),
            content.getString("description"),
            content.getString("thumbnailURL"),
            GetThumbnail(content.getString("thumbnailURL"))
        );
    }

    public Map<String, String> ParseCustomContent(JSONArray customContentItems) throws JSONException {
        Map<String, String> customContentMap = new HashMap<String, String>();

        for (int i = 0; i < customContentItems.length(); i ++) {
            JSONObject customContentItem = customContentItems.getJSONObject(i);
            customContentMap.put(customContentItem.getString("title"), customContentItem.getString("value"));
        }

        return customContentMap;
    }

    public Bitmap GetThumbnail(String thumbnailUri) {
        Bitmap thumbnail = null;
        WebResponse response = webClient.get(thumbnailUri);

        if (response.isSuccessful()) {
            thumbnail = response.getBody().toBitmap();
        }
        else {
            // TODO: set the thumbnail to some default here...
            thumbnail = null;
        }
        return thumbnail;
    }
}
