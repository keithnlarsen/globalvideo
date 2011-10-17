package ca.shawmedia.globalvideo.infrastructure;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;

public class WebClient implements IWebClient{

    public WebResponse get(String Uri) {
        InputStream inputStream = null;
        int responseCode = 0;
        DefaultHttpClient client;
        HttpGet method = new HttpGet(Uri);

        try {
//            for (Map.Entry<String, String> entry : headers.entrySet()) {
//                method.setHeader(entry.getKey(), entry.getValue());
//            }
            client = getPromiscuousDefaultClient();
            HttpResponse response = client.execute(method);

            responseCode = response.getStatusLine().getStatusCode();
            inputStream = response.getEntity().getContent();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new WebResponse(null, responseCode, inputStream, Uri);
    }

    private DefaultHttpClient getPromiscuousDefaultClient() {
        HttpParams parameters = new BasicHttpParams();
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("https", new CertificateIgnoringSSLSocketFactory(), 443));
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        ClientConnectionManager manager = new ThreadSafeClientConnManager(parameters, schemeRegistry);
        return new DefaultHttpClient(manager, parameters);
    }

}
