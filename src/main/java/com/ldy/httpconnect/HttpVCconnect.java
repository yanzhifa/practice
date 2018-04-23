package com.ldy.httpconnect;

import org.apache.http.client.methods.HttpGet;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * Created by yanz3 on 8/11/17.
 */
public class HttpVCconnect {

    private static final int CONNECTION_TIME_OUT = 5000;

    public static int getHttpConnectResponseCode(String ip, String username, String password, String uriPath, String uriQuery) throws URISyntaxException, IOException {
        HttpURLConnection con = null;
        try {
            URI uri = new URI("https", null, ip, 443, uriPath, uriQuery, null);
            URL url = new URL(uri.toASCIIString());

            //TODO Change to HttpClient
            con = (HttpURLConnection) url.openConnection();

            String creds = username + ":" + password;
            String encodedCreds = Base64.getEncoder().encodeToString(creds.getBytes());

            con.setRequestProperty("Content-Type", "application/xml");
            con.setRequestProperty("Authorization", "Basic " + encodedCreds);
            con.setConnectTimeout(CONNECTION_TIME_OUT);
            con.setRequestMethod(HttpGet.METHOD_NAME);

            con.connect();
            return con.getResponseCode();

        } finally {
            System.out.println("disconnect...");
            con.disconnect();
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException, KeyManagementException, NoSuchAlgorithmException {

        javax.net.ssl.HostnameVerifier verifier = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        // Create the trust manager.
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager trustManager = new TrustAllTrustManager();
        trustAllCerts[0] = trustManager;

        // Create the SSL context
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");

        // Create the session context
        javax.net.ssl.SSLSessionContext sslsc = sc.getServerSessionContext();

        // Initialize the contexts; the session context takes the trust
        // manager.
        sslsc.setSessionTimeout(0);
        sc.init(null, trustAllCerts, null);

        // Use the default socket factory to create the socket for the
        // secure connection
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Set the default host name verifier to enable the connection.
        HttpsURLConnection.setDefaultHostnameVerifier(verifier);


        int returnCode = getHttpConnectResponseCode("10.62.91.135",
                "administrator@vsphere.local", "Testvxrail123!","/folder","");
        System.out.println(returnCode);
    }

    /**
     * aid testing on a local box, not for use on production.
     */
    private static class TrustAllTrustManager implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @SuppressWarnings("unused")
        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        @SuppressWarnings("unused")
        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
}
