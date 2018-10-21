package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class ResponseHeaderUtil {

  public static void main(String[] args) {

    try {

	HttpClient client = HttpClientBuilder.create().build();
	HttpGet request = new HttpGet("http://mkyong.com");
	HttpPost requestPost = new HttpPost();
	URI uri = new URI("https://es-staging.cdac.in/esign2.1level1/2.1/signdoc");
	requestPost.setURI(uri);
	
	//Object entity = null;
	String signedDocument = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Esign AuthMode=\"1\" aspId=\"EITH-900\" ekycId=\"\" ekycIdType=\"A\" responseSigType=\"pkcs7\" responseUrl=\"http://164.100.137.211:3030/esign\" sc=\"y\" ts=\"2018-09-18T11:31:31\" txn=\"DOITHRGOVAUTH201809260051448745\" ver=\"2.1\">\n    <Docs>\n        <InputHash docInfo=\"docInfo\" hashAlgorithm=\"SHA256\" id=\"1\">502834971d7e227eb5eb0f9a347bff6a5412014bb2c14a2f183a2fdd1c8aa2b6</InputHash>\n    </Docs>\n<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>qU6x/zJoKZeehDNy6eo0JfwZcb8=</DigestValue></Reference></SignedInfo><SignatureValue>IUBv0D9uL3JmdzjjRc/TCWUw6yQxOtqwx96g+othSSuP5fILolmgFcUuzsJPd9Xgi90AFvsBEPfr\nTWt9mMBVed4sy33/9gw2Qj24Ovx4wmHbPRMAdEbB71e5Kna/9wElXwIQLf8pkjZzhRu4YnmQPw00\naoTMC7MEDjebIefThwwb+UIYyD9BGAdz3+b7DFsQBKmFw74kyi/3e2lMJEu9LXPOJQ0OocDsPgEx\nYHcCsMlZfePEgc27FkQp9n8FxgYqnc00XDkAtmsB0pVHr/3D5ROYINutiD4jsGR6fbDFZE9+pIoc\n0RFY0wkUriZU+9EOg3tHCNJ72Qo0AqDVa8YFGQ==</SignatureValue><KeyInfo><X509Data><X509SubjectName>CN=*.cdac.in,O=Centre For Development Of Advanced Computing,L=Pune,ST=Maharashtra,C=IN</X509SubjectName><X509Certificate>MIIGEjCCBPqgAwIBAgIQBOdhfs2rvRyp34uZzngqcDANBgkqhkiG9w0BAQsFADBeMQswCQYDVQQG\nEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3d3cuZGlnaWNlcnQuY29tMR0w\nGwYDVQQDExRHZW9UcnVzdCBSU0EgQ0EgMjAxODAeFw0xODA2MjgwMDAwMDBaFw0xOTAyMTIxMjAw\nMDBaMH0xCzAJBgNVBAYTAklOMRQwEgYDVQQIEwtNYWhhcmFzaHRyYTENMAsGA1UEBxMEUHVuZTE1\nMDMGA1UEChMsQ2VudHJlIEZvciBEZXZlbG9wbWVudCBPZiBBZHZhbmNlZCBDb21wdXRpbmcxEjAQ\nBgNVBAMMCSouY2RhYy5pbjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAOaTo5YkmeQr\nJqB4D2xXAkrFi916NdfcG/CuczdfpMofZicxo93fWh+n8CWMyeLSL8mr7PLvbUDdy5kvI8yg0L7m\nrdYFCAyX87LxZ0lMWJHgrWk548aQsAIs0d0X5Oj4V/AAu9K/RivpgTXiebhuVlyjS4elh9Eeo1Wp\n2024ukQHGKO/0R+/yye8LMyEWtdnVH2+jpyCS4rWVbximRJ9dfpAN6RhyUW37HrTTopnFlpS+qnM\nnXOwXMgt3dIKsjsPg6GAtFmBk9CBgHSgRw0avkj7nUsV45tteZSjKdSETGsRvYjZKwennfWLpUOR\nQ5bXtyhlqvSKu1zDyWM0WWY9TtcCAwEAAaOCAqswggKnMB8GA1UdIwQYMBaAFJBY/7CcdahRVHex\n7fKjQxY4nmzFMB0GA1UdDgQWBBSTac7QWFhNXqM6cGVmTtLf/3fYETAdBgNVHREEFjAUggkqLmNk\nYWMuaW6CB2NkYWMuaW4wDgYDVR0PAQH/BAQDAgWgMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEF\nBQcDAjA+BgNVHR8ENzA1MDOgMaAvhi1odHRwOi8vY2RwLmdlb3RydXN0LmNvbS9HZW9UcnVzdFJT\nQUNBMjAxOC5jcmwwTAYDVR0gBEUwQzA3BglghkgBhv1sAQEwKjAoBggrBgEFBQcCARYcaHR0cHM6\nLy93d3cuZGlnaWNlcnQuY29tL0NQUzAIBgZngQwBAgIwdQYIKwYBBQUHAQEEaTBnMCYGCCsGAQUF\nBzABhhpodHRwOi8vc3RhdHVzLmdlb3RydXN0LmNvbTA9BggrBgEFBQcwAoYxaHR0cDovL2NhY2Vy\ndHMuZ2VvdHJ1c3QuY29tL0dlb1RydXN0UlNBQ0EyMDE4LmNydDAJBgNVHRMEAjAAMIIBBQYKKwYB\nBAHWeQIEAgSB9gSB8wDxAHcApLkJkLQYWBSHuxOizGdwCjw1mAT5G9+443fNDsgN3BAAAAFkRlNP\n5AAABAMASDBGAiEArQM0HXj5PjVpBsk4ydzrHxObpqMkx8EaN+eLE4u+h1cCIQDVnPvoZuiQ/tFR\nU7ao2c6tGejEJV+Wq7GwRzfWrv7FnAB2AId1v+dZfPiMQ5lfvfNu/1aNR1Y2/0q1YMG06v9eoIMP\nAAABZEZTUKMAAAQDAEcwRQIgSiov4VxGDVcdc5zRCTMwEOTe5Kw2uH/yfbzUSbjIsjYCIQCWAE0Y\nSPYRSX/fujJTHO4grWzXM4ynsx7Fa1pDyuyK6DANBgkqhkiG9w0BAQsFAAOCAQEAKbCnenOaEXQ4\nGmpMO3q0bS8tIGQyp/kr+nnZXKM9WNekzlIlgiE/OOGwBw6aA1HfpIh6eKGHrgtBvcBzdTaf3re5\nvO+GoMdxCLvDRA21Ch5XvUzyUeW2+ViRLBLna+j9NDpF8fTmYYtC3QJWH8GNyg8GMtvbdtIhM1IY\nltSSQmNLglk5WJZS0nwGJ/j2wCsNB6P4kxPZ8PnRBHUtAF54incMvK+ddUtc7ktrl/DlufFGxNTu\nkZbtVR83w1cZ/goNsdT4jNGs2NYyQIKo7TadwjJhyyTbsroUx0OLmiCjufaH954QIkH9ApQmRCwp\n9U+Fwn3mcdnXWUivyFGvT35WPA==</X509Certificate></X509Data></KeyInfo></Signature></Esign>";
	/*if(signedDocument instanceof Object){
		entity = (Object)signedDocument;
	}*/
	
	HttpEntity entity = new ByteArrayEntity(signedDocument.getBytes("UTF-8"));
	requestPost.setEntity(entity);
	HttpResponse response = client.execute(requestPost);
	//HttpResponse response = client.execute(request);
			
	System.out.println("Printing Response Header...\n");

	Header[] headers = response.getAllHeaders();
	for (Header header : headers) {
		System.out.println("Key : " + header.getName() 
                           + " ,Value : " + header.getValue());

	}

	System.out.println("\nGet Response Header By Key ...\n");
	String server = response.getFirstHeader("Server").getValue();

	if (server == null) {
		System.out.println("Key 'Server' is not found!");
	} else {
		System.out.println("Server - " + server);
	}

	System.out.println("\n Done");

    } catch (Exception e) {
	e.printStackTrace();
    }
  }
}
