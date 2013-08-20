package com.test4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Test4 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader r = null;
        try {
            URL u = new URL("https://login.alibaba-inc.com/ssoLogin.htm");
            HttpURLConnection connection = (HttpURLConnection)u.openConnection();
            connection.setRequestProperty("Connection", "close");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setRequestMethod("GET");
            
            if ((connection instanceof sun.net.www.protocol.https.HttpsURLConnectionImpl)) {
                ((sun.net.www.protocol.https.HttpsURLConnectionImpl)connection).setSSLSocketFactory(createSSLSocketFactory());
                ((sun.net.www.protocol.https.HttpsURLConnectionImpl)connection).setHostnameVerifier( new TrustAnyHostnameVerifier());
             }
            r = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            System.out.println(read(r));
        }
        finally {
            try {
                if (r != null)
                    r.close();
            } catch (IOException ex) {
                // ignore
            }
        }

	}
	private static SSLSocketFactory createSSLSocketFactory() throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException
    {
      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
      sslContext.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new SecureRandom());
      return sslContext.getSocketFactory();
    }
	
	 private static class TrustAnyTrustManager implements X509TrustManager {
	        public void checkClientTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }
	 
	        public void checkServerTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }
	 
	        public X509Certificate[] getAcceptedIssuers() {
	            return new X509Certificate[] {};
	        }
	    }
	 
	 public static String read(Reader reader) {
	        try {
	            final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	            StringWriter writer = new StringWriter();

	            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
	            int n = 0;
	            while (-1 != (n = reader.read(buffer))) {
	                writer.write(buffer, 0, n);
	            }

	            return writer.toString();
	        } catch (IOException ex) {
	            throw new IllegalStateException("read error", ex);
	        }
	    }
	    
	 private static class TrustAnyHostnameVerifier implements HostnameVerifier {

	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    }

}
