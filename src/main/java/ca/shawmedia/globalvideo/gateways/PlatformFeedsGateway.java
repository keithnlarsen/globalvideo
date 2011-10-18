package ca.shawmedia.globalvideo.gateways;

import ca.shawmedia.globalvideo.infrastructure.IWebClient;
import ca.shawmedia.globalvideo.infrastructure.WebResponse;
import com.google.inject.Inject;

public class PlatformFeedsGateway implements IPlatformFeedsGateway{

    IWebClient webClient;

    @Inject
    public PlatformFeedsGateway(IWebClient webClient) {
        this.webClient = webClient;
    }

    public String GetContentRotatorFeedData(){
        WebResponse webResponse = webClient.get("http://feedUri");

        if(webResponse.isSuccessful()) {
            return webResponse.getBody().toString();
        }
        else {
            return "";
        }
    }
}
