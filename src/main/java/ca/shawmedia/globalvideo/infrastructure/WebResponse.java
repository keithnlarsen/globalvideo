package ca.shawmedia.globalvideo.infrastructure;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class WebResponse {

    public WebResponse(Map<String, List<String>> requestHeaderFields, int statusCode, InputStream body, String requestUri) {
        this.statusCode = statusCode;
        this.body = new WebResponseBody(body);
        this.requestHeaderFields = requestHeaderFields;
        this.requestUri = requestUri;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public WebResponseBody getBody() {
        return body;
    }

    public Map<String, List<String>> getRequestHeadersFields() {
        return requestHeaderFields;
    }

    public String getRequestUri(){
        return requestUri;
    }

    public boolean isSuccessful(){
        return (statusCode >= 200 && statusCode < 400);
    }

    private int statusCode;
    private WebResponseBody body;
    private Map<String, List<String>> requestHeaderFields;
    private String requestUri;
}
