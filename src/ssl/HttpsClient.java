package ssl;
/*import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.springframework.http.HttpHeaders;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;*/

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpsClient{
	
   public static void main(String[] args) //throws UnirestException
   {
       //getConnection();
	   //getUnirest();
   }
	
   /*private void testIt(){

      String https_url = "https://100.164.137.211:8080/";
      URL url;
      try {

	     url = new URL(https_url);
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			
	     //dumpl all cert info
	     print_https_cert(con);
			
	     //dump all the content
	     print_content(con);
			
      } catch (MalformedURLException e) {
	     e.printStackTrace();
      } catch (IOException e) {
	     e.printStackTrace();
      }

   }
	
   private void print_https_cert(HttpsURLConnection con){
     
    if(con!=null){
			
      try {
				
	System.out.println("Response Code : " + con.getResponseCode());
	System.out.println("Cipher Suite : " + con.getCipherSuite());
	System.out.println("\n");
				
	Certificate[] certs = con.getServerCertificates();
	for(Certificate cert : certs){
	   System.out.println("Cert Type : " + cert.getType());
	   System.out.println("Cert Hash Code : " + cert.hashCode());
	   System.out.println("Cert Public Key Algorithm : " 
                                    + cert.getPublicKey().getAlgorithm());
	   System.out.println("Cert Public Key Format : " 
                                    + cert.getPublicKey().getFormat());
	   System.out.println("\n");
	}
				
	} catch (SSLPeerUnverifiedException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	}

     }
	
   }
	
   private void print_content(HttpsURLConnection con){
	if(con!=null){
			
	try {
		
	   System.out.println("****** Content of the URL ********");			
	   BufferedReader br = 
		new BufferedReader(
			new InputStreamReader(con.getInputStream()));
				
	   String input;
				
	   while ((input = br.readLine()) != null){
	      System.out.println(input);
	   }
	   br.close();
				
	} catch (IOException e) {
	   e.printStackTrace();
	}
			
       }
		
   }
   
   
   
   public static void getConnection() throws IOException{
	   OkHttpClient client = new OkHttpClient();

	   MediaType mediaType = MediaType.parse("application/xml");
	   
	   RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Esign AuthMode=\"1\" aspId=\"EITH-900\" ekycId=\"\" ekycIdType=\"A\" responseSigType=\"pkcs7\" responseUrl=\"http://164.100.137.211:3030/esign\" sc=\"y\" ts=\"2018-09-18T11:31:31\" txn=\"DOITHRGOVAUTH201809260051448745\" ver=\"2.1\">\r\n    <Docs>\r\n        <InputHash docInfo=\"docInfo\" hashAlgorithm=\"SHA256\" id=\"1\">502834971d7e227eb5eb0f9a347bff6a5412014bb2c14a2f183a2fdd1c8aa2b6</InputHash>\r\n    </Docs>\r\n<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>qU6x/zJoKZeehDNy6eo0JfwZcb8=</DigestValue></Reference></SignedInfo><SignatureValue>IUBv0D9uL3JmdzjjRc/TCWUw6yQxOtqwx96g+othSSuP5fILolmgFcUuzsJPd9Xgi90AFvsBEPfr\r\nTWt9mMBVed4sy33/9gw2Qj24Ovx4wmHbPRMAdEbB71e5Kna/9wElXwIQLf8pkjZzhRu4YnmQPw00\r\naoTMC7MEDjebIefThwwb+UIYyD9BGAdz3+b7DFsQBKmFw74kyi/3e2lMJEu9LXPOJQ0OocDsPgEx\r\nYHcCsMlZfePEgc27FkQp9n8FxgYqnc00XDkAtmsB0pVHr/3D5ROYINutiD4jsGR6fbDFZE9+pIoc\r\n0RFY0wkUriZU+9EOg3tHCNJ72Qo0AqDVa8YFGQ==</SignatureValue><KeyInfo><X509Data><X509SubjectName>CN=*.cdac.in,O=Centre For Development Of Advanced Computing,L=Pune,ST=Maharashtra,C=IN</X509SubjectName><X509Certificate>MIIGEjCCBPqgAwIBAgIQBOdhfs2rvRyp34uZzngqcDANBgkqhkiG9w0BAQsFADBeMQswCQYDVQQG\r\nEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3d3cuZGlnaWNlcnQuY29tMR0w\r\nGwYDVQQDExRHZW9UcnVzdCBSU0EgQ0EgMjAxODAeFw0xODA2MjgwMDAwMDBaFw0xOTAyMTIxMjAw\r\nMDBaMH0xCzAJBgNVBAYTAklOMRQwEgYDVQQIEwtNYWhhcmFzaHRyYTENMAsGA1UEBxMEUHVuZTE1\r\nMDMGA1UEChMsQ2VudHJlIEZvciBEZXZlbG9wbWVudCBPZiBBZHZhbmNlZCBDb21wdXRpbmcxEjAQ\r\nBgNVBAMMCSouY2RhYy5pbjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAOaTo5YkmeQr\r\nJqB4D2xXAkrFi916NdfcG/CuczdfpMofZicxo93fWh+n8CWMyeLSL8mr7PLvbUDdy5kvI8yg0L7m\r\nrdYFCAyX87LxZ0lMWJHgrWk548aQsAIs0d0X5Oj4V/AAu9K/RivpgTXiebhuVlyjS4elh9Eeo1Wp\r\n2024ukQHGKO/0R+/yye8LMyEWtdnVH2+jpyCS4rWVbximRJ9dfpAN6RhyUW37HrTTopnFlpS+qnM\r\nnXOwXMgt3dIKsjsPg6GAtFmBk9CBgHSgRw0avkj7nUsV45tteZSjKdSETGsRvYjZKwennfWLpUOR\r\nQ5bXtyhlqvSKu1zDyWM0WWY9TtcCAwEAAaOCAqswggKnMB8GA1UdIwQYMBaAFJBY/7CcdahRVHex\r\n7fKjQxY4nmzFMB0GA1UdDgQWBBSTac7QWFhNXqM6cGVmTtLf/3fYETAdBgNVHREEFjAUggkqLmNk\r\nYWMuaW6CB2NkYWMuaW4wDgYDVR0PAQH/BAQDAgWgMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEF\r\nBQcDAjA+BgNVHR8ENzA1MDOgMaAvhi1odHRwOi8vY2RwLmdlb3RydXN0LmNvbS9HZW9UcnVzdFJT\r\nQUNBMjAxOC5jcmwwTAYDVR0gBEUwQzA3BglghkgBhv1sAQEwKjAoBggrBgEFBQcCARYcaHR0cHM6\r\nLy93d3cuZGlnaWNlcnQuY29tL0NQUzAIBgZngQwBAgIwdQYIKwYBBQUHAQEEaTBnMCYGCCsGAQUF\r\nBzABhhpodHRwOi8vc3RhdHVzLmdlb3RydXN0LmNvbTA9BggrBgEFBQcwAoYxaHR0cDovL2NhY2Vy\r\ndHMuZ2VvdHJ1c3QuY29tL0dlb1RydXN0UlNBQ0EyMDE4LmNydDAJBgNVHRMEAjAAMIIBBQYKKwYB\r\nBAHWeQIEAgSB9gSB8wDxAHcApLkJkLQYWBSHuxOizGdwCjw1mAT5G9+443fNDsgN3BAAAAFkRlNP\r\n5AAABAMASDBGAiEArQM0HXj5PjVpBsk4ydzrHxObpqMkx8EaN+eLE4u+h1cCIQDVnPvoZuiQ/tFR\r\nU7ao2c6tGejEJV+Wq7GwRzfWrv7FnAB2AId1v+dZfPiMQ5lfvfNu/1aNR1Y2/0q1YMG06v9eoIMP\r\nAAABZEZTUKMAAAQDAEcwRQIgSiov4VxGDVcdc5zRCTMwEOTe5Kw2uH/yfbzUSbjIsjYCIQCWAE0Y\r\nSPYRSX/fujJTHO4grWzXM4ynsx7Fa1pDyuyK6DANBgkqhkiG9w0BAQsFAAOCAQEAKbCnenOaEXQ4\r\nGmpMO3q0bS8tIGQyp/kr+nnZXKM9WNekzlIlgiE/OOGwBw6aA1HfpIh6eKGHrgtBvcBzdTaf3re5\r\nvO+GoMdxCLvDRA21Ch5XvUzyUeW2+ViRLBLna+j9NDpF8fTmYYtC3QJWH8GNyg8GMtvbdtIhM1IY\r\nltSSQmNLglk5WJZS0nwGJ/j2wCsNB6P4kxPZ8PnRBHUtAF54incMvK+ddUtc7ktrl/DlufFGxNTu\r\nkZbtVR83w1cZ/goNsdT4jNGs2NYyQIKo7TadwjJhyyTbsroUx0OLmiCjufaH954QIkH9ApQmRCwp\r\n9U+Fwn3mcdnXWUivyFGvT35WPA==</X509Certificate></X509Data></KeyInfo></Signature></Esign>");
	   Request request = new Request.Builder()
	     .url("https://es-staging.cdac.in/esign2.1level1/2.1/signdoc")
	     .post(body)
	     .addHeader("Content-Type", "application/xml")
	     .addHeader("Cache-Control", "no-cache")
	     .addHeader("Postman-Token", "477ec830-380a-4131-9bf3-dedb85f0deb3")
	     .build();

	   Response response = client.newCall(request).execute();
	   System.out.println(response.body());
   }
   
   public static void getUnirest() throws UnirestException{
	   HttpResponse<String> response = Unirest.post("https://es-staging.cdac.in/esign2.1level1/2.1/signdoc")
			   .header("Content-Type", "application/xml")
			   .header("Cache-Control", "no-cache")
			   .header("Postman-Token", "7ad0a959-3db9-4d46-8cad-fad88b8bf5b7")
			   .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Esign AuthMode=\"1\" aspId=\"EITH-900\" ekycId=\"\" ekycIdType=\"A\" responseSigType=\"pkcs7\" responseUrl=\"http://164.100.137.211:3030/esign\" sc=\"y\" ts=\"2018-09-18T11:31:31\" txn=\"DOITHRGOVAUTH201809260051448745\" ver=\"2.1\">\r\n    <Docs>\r\n        <InputHash docInfo=\"docInfo\" hashAlgorithm=\"SHA256\" id=\"1\">502834971d7e227eb5eb0f9a347bff6a5412014bb2c14a2f183a2fdd1c8aa2b6</InputHash>\r\n    </Docs>\r\n<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>qU6x/zJoKZeehDNy6eo0JfwZcb8=</DigestValue></Reference></SignedInfo><SignatureValue>IUBv0D9uL3JmdzjjRc/TCWUw6yQxOtqwx96g+othSSuP5fILolmgFcUuzsJPd9Xgi90AFvsBEPfr\r\nTWt9mMBVed4sy33/9gw2Qj24Ovx4wmHbPRMAdEbB71e5Kna/9wElXwIQLf8pkjZzhRu4YnmQPw00\r\naoTMC7MEDjebIefThwwb+UIYyD9BGAdz3+b7DFsQBKmFw74kyi/3e2lMJEu9LXPOJQ0OocDsPgEx\r\nYHcCsMlZfePEgc27FkQp9n8FxgYqnc00XDkAtmsB0pVHr/3D5ROYINutiD4jsGR6fbDFZE9+pIoc\r\n0RFY0wkUriZU+9EOg3tHCNJ72Qo0AqDVa8YFGQ==</SignatureValue><KeyInfo><X509Data><X509SubjectName>CN=*.cdac.in,O=Centre For Development Of Advanced Computing,L=Pune,ST=Maharashtra,C=IN</X509SubjectName><X509Certificate>MIIGEjCCBPqgAwIBAgIQBOdhfs2rvRyp34uZzngqcDANBgkqhkiG9w0BAQsFADBeMQswCQYDVQQG\r\nEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3d3cuZGlnaWNlcnQuY29tMR0w\r\nGwYDVQQDExRHZW9UcnVzdCBSU0EgQ0EgMjAxODAeFw0xODA2MjgwMDAwMDBaFw0xOTAyMTIxMjAw\r\nMDBaMH0xCzAJBgNVBAYTAklOMRQwEgYDVQQIEwtNYWhhcmFzaHRyYTENMAsGA1UEBxMEUHVuZTE1\r\nMDMGA1UEChMsQ2VudHJlIEZvciBEZXZlbG9wbWVudCBPZiBBZHZhbmNlZCBDb21wdXRpbmcxEjAQ\r\nBgNVBAMMCSouY2RhYy5pbjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAOaTo5YkmeQr\r\nJqB4D2xXAkrFi916NdfcG/CuczdfpMofZicxo93fWh+n8CWMyeLSL8mr7PLvbUDdy5kvI8yg0L7m\r\nrdYFCAyX87LxZ0lMWJHgrWk548aQsAIs0d0X5Oj4V/AAu9K/RivpgTXiebhuVlyjS4elh9Eeo1Wp\r\n2024ukQHGKO/0R+/yye8LMyEWtdnVH2+jpyCS4rWVbximRJ9dfpAN6RhyUW37HrTTopnFlpS+qnM\r\nnXOwXMgt3dIKsjsPg6GAtFmBk9CBgHSgRw0avkj7nUsV45tteZSjKdSETGsRvYjZKwennfWLpUOR\r\nQ5bXtyhlqvSKu1zDyWM0WWY9TtcCAwEAAaOCAqswggKnMB8GA1UdIwQYMBaAFJBY/7CcdahRVHex\r\n7fKjQxY4nmzFMB0GA1UdDgQWBBSTac7QWFhNXqM6cGVmTtLf/3fYETAdBgNVHREEFjAUggkqLmNk\r\nYWMuaW6CB2NkYWMuaW4wDgYDVR0PAQH/BAQDAgWgMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEF\r\nBQcDAjA+BgNVHR8ENzA1MDOgMaAvhi1odHRwOi8vY2RwLmdlb3RydXN0LmNvbS9HZW9UcnVzdFJT\r\nQUNBMjAxOC5jcmwwTAYDVR0gBEUwQzA3BglghkgBhv1sAQEwKjAoBggrBgEFBQcCARYcaHR0cHM6\r\nLy93d3cuZGlnaWNlcnQuY29tL0NQUzAIBgZngQwBAgIwdQYIKwYBBQUHAQEEaTBnMCYGCCsGAQUF\r\nBzABhhpodHRwOi8vc3RhdHVzLmdlb3RydXN0LmNvbTA9BggrBgEFBQcwAoYxaHR0cDovL2NhY2Vy\r\ndHMuZ2VvdHJ1c3QuY29tL0dlb1RydXN0UlNBQ0EyMDE4LmNydDAJBgNVHRMEAjAAMIIBBQYKKwYB\r\nBAHWeQIEAgSB9gSB8wDxAHcApLkJkLQYWBSHuxOizGdwCjw1mAT5G9+443fNDsgN3BAAAAFkRlNP\r\n5AAABAMASDBGAiEArQM0HXj5PjVpBsk4ydzrHxObpqMkx8EaN+eLE4u+h1cCIQDVnPvoZuiQ/tFR\r\nU7ao2c6tGejEJV+Wq7GwRzfWrv7FnAB2AId1v+dZfPiMQ5lfvfNu/1aNR1Y2/0q1YMG06v9eoIMP\r\nAAABZEZTUKMAAAQDAEcwRQIgSiov4VxGDVcdc5zRCTMwEOTe5Kw2uH/yfbzUSbjIsjYCIQCWAE0Y\r\nSPYRSX/fujJTHO4grWzXM4ynsx7Fa1pDyuyK6DANBgkqhkiG9w0BAQsFAAOCAQEAKbCnenOaEXQ4\r\nGmpMO3q0bS8tIGQyp/kr+nnZXKM9WNekzlIlgiE/OOGwBw6aA1HfpIh6eKGHrgtBvcBzdTaf3re5\r\nvO+GoMdxCLvDRA21Ch5XvUzyUeW2+ViRLBLna+j9NDpF8fTmYYtC3QJWH8GNyg8GMtvbdtIhM1IY\r\nltSSQmNLglk5WJZS0nwGJ/j2wCsNB6P4kxPZ8PnRBHUtAF54incMvK+ddUtc7ktrl/DlufFGxNTu\r\nkZbtVR83w1cZ/goNsdT4jNGs2NYyQIKo7TadwjJhyyTbsroUx0OLmiCjufaH954QIkH9ApQmRCwp\r\n9U+Fwn3mcdnXWUivyFGvT35WPA==</X509Certificate></X509Data></KeyInfo></Signature></Esign>")
			   .asString();
   }*/
   
  
}