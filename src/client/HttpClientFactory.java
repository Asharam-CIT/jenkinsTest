package client;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.SSLContexts;



public class HttpClientFactory {

    private static CloseableHttpClient client;

    private static CloseableHttpClient httpClient;

    private static PoolingHttpClientConnectionManager connPool = null;

    private static PoolingHttpClientConnectionManager httpconnPool = null;

   // private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientFactory.class);

    static{
        try
        {
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
            sslcontext.init(new KeyManager[0], new TrustManager[] { new DummyTrustManager() }, new SecureRandom());
            sslcontext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
                    new HostnameVerifier() {
                        @Override
                        public boolean verify(final String s, final SSLSession sslSession) {

                            return true;
                        }
                    });

            Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("https", factory).build();
            
          

            connPool = new PoolingHttpClientConnectionManager(r);
            // Increase max total connection to 200
            connPool.setMaxTotal(300);//configurable through app.properties
            // Increase default max connection per route to 50
            connPool.setDefaultMaxPerRoute(300);//configurable through app.properties

          //  httpconnPool = new PoolingHttpClientConnectionManager(r);
            
            httpconnPool = new PoolingHttpClientConnectionManager();
            
            // Increase max total connection to 200
            httpconnPool.setMaxTotal(300);//configurable through app.properties
            // Increase default max connection per route to 50
            httpconnPool.setDefaultMaxPerRoute(300);//configurable through app.properties
            
            client = HttpClients.custom().
                    setConnectionManagerShared(true).
                    setConnectionManager(connPool).
                    setSSLSocketFactory(factory).build();

            httpClient = HttpClients.custom().
                    setConnectionManagerShared(true).
                    setConnectionManager(httpconnPool).
                    build();

        }
        catch(Exception e){
            //LOGGER.error("Error initiliazing HttpClientFactory :: ",e);
        	e.printStackTrace();
        }
    }

    public static CloseableHttpClient getHttpsClient() throws KeyManagementException, NoSuchAlgorithmException  {

        if (client != null) {
            return client;
        }
        throw new RuntimeException("Client is not initiliazed properly");

    }

    public static CloseableHttpClient getHttpClient() {

        if (httpClient != null) {
            return httpClient;
        }
        throw new RuntimeException("Client is not initiliazed properly");

    }
    public static void releaseInstance() {
        client = null;
        httpClient = null ;

    }
    
    
    public static class DummyTrustManager implements X509TrustManager {
		public boolean isClientTrusted(X509Certificate[] cert) {
			return true;
		}

		public boolean isServerTrusted(X509Certificate[] cert) {
			return true;
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}
	}
}