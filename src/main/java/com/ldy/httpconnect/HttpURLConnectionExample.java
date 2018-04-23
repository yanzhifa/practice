package com.ldy.httpconnect;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpURLConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";

//    private static final String GET_URL = "http://localhost:8080/springrest/rest/emp/dummy";
    private static final String GET_URL = "https://10.62.92.73/extension/mystic/settings";

//    private static final String POST_URL = "http://localhost:8080/SpringMVCExample/home";
    private static final String POST_URL = "https://10.62.92.73/extension/mystic/settings/simple";

    private static final String POST_PARAMS = "userName=Pankaj";

//    private static String POST_JSON = "{\"vcenterPassword\":\"Testvxrail123!\",\"hostAccounts\":[{\"hostName\":\"app32-01.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}},{\"hostName\":\"app32-02.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}},{\"hostName\":\"app32-03.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}}],\"rsaPublicKeys\":[\"AAAAB3NzaC1yc2EAAAADAQABAAABAQDb/DCfBo75bv7gpAGRq7aQdy3Aw5FTC2A5WR8Lu1upiaPtJSsUiY/S4KFgR/iLSrVCP1fH9WJN8mEKF1icyXkUsA6p6blxfPKHeorztXUuU12GNPkqnjpJu2tV26nwk+8pC9NIm3PL0ZiY0o4pa2yhXPmjA6Py6RXp1ZPN3sVYVFrRBaBdd2rMzbyQOi5Wn3qJR8q25CW6yNUMeQkEDqcEez/j+zKnWn5ZpREUSZ+D5g3uTsu6aK8MRXdiP1DZnoc2QWOZo+7LrSX7datdLIGO6K49QAWIlmsTRpdbYWu9yT88JUlup8LcpGuYMGuHbQLUr2J9NS+ss5K3bMIKlQ/7\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuXcfIJ97r1BDBc4ai0MeYyknGYE2XjvePHISkbSm1nXo6/RA0eMggXq9NnAzgUj+PYsKe/gKDakWgLlY9ies+nJ4K/QZaal88asemi+a871xbx61FS73KP6jl/Vx3rURuFTsOvVf/wEP03e97u6GM5cnpLby9rx2G0wv0sxd8oVN1FFYlKuhp7xDqFiTAry3ioTiqN5ra8lJ54sIqAVG6rvc1c8flwlfSg5dbb69Bsik6MGzXJ4/MhIgrVS7hqKhX6wj7V51JI5ED9JOtJl+LqHN9e7vbVrQX77zKW/XR5cqZIVxa36x6WBOAty56Guq9FA2jhM4IJ1TZ76/hrNxf\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuGRDYa79sjBgvyI3LSlospJUs9DzhoxJTCwEGNBmPHCKbIol9OWYr7bPP7Cd3MqSnVK40oW7c5E+nV5Nmt8f4kYJXbDML7xhtjFMU6PmiEECdGpZ2j/mKjDpPd/NjddzSrc2pmhG0UTs5Ov4cACkDCvUPZCBTqBZV6brY8jsGg2FdCyi5gDmptNQBRzNhDs+UDilGsf0y6MeN3SWpS3HXM3pYwcWl4IiAHTN588VCBJtFqsiRljdYM9Jgd8c9ll9o3wR/MXhgrHo8G/FtjvqywNhM3HRRkaExkQ1LGLlR96iD14+2O4DHdW1zktj+PwvuzQs0/nl1XNrbCfl3ZkcF\"],\"pscMgtUser\":\"management\",\"clusterMorValue\":\"domain-c7\",\"pscMgtPassword\":\"Testvxrail123!\",\"pscIp\":\"10.62.91.137\",\"clusterName\":null,\"ssoTenant\":\"vsphere.local\",\"vcenterIp\":\"10.62.91.135\"}";
//    private static String POST_JSON = "{\"vcenterPassword\":\"Testvxrail123!\",\"rsaPublicKeys\":[\"AAAAB3NzaC1yc2EAAAADAQABAAABAQDb/DCfBo75bv7gpAGRq7aQdy3Aw5FTC2A5WR8Lu1upiaPtJSsUiY/S4KFgR/iLSrVCP1fH9WJN8mEKF1icyXkUsA6p6blxfPKHeorztXUuU12GNPkqnjpJu2tV26nwk+8pC9NIm3PL0ZiY0o4pa2yhXPmjA6Py6RXp1ZPN3sVYVFrRBaBdd2rMzbyQOi5Wn3qJR8q25CW6yNUMeQkEDqcEez/j+zKnWn5ZpREUSZ+D5g3uTsu6aK8MRXdiP1DZnoc2QWOZo+7LrSX7datdLIGO6K49QAWIlmsTRpdbYWu9yT88JUlup8LcpGuYMGuHbQLUr2J9NS+ss5K3bMIKlQ/7\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuXcfIJ97r1BDBc4ai0MeYyknGYE2XjvePHISkbSm1nXo6/RA0eMggXq9NnAzgUj+PYsKe/gKDakWgLlY9ies+nJ4K/QZaal88asemi+a871xbx61FS73KP6jl/Vx3rURuFTsOvVf/wEP03e97u6GM5cnpLby9rx2G0wv0sxd8oVN1FFYlKuhp7xDqFiTAry3ioTiqN5ra8lJ54sIqAVG6rvc1c8flwlfSg5dbb69Bsik6MGzXJ4/MhIgrVS7hqKhX6wj7V51JI5ED9JOtJl+LqHN9e7vbVrQX77zKW/XR5cqZIVxa36x6WBOAty56Guq9FA2jhM4IJ1TZ76/hrNxf\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuGRDYa79sjBgvyI3LSlospJUs9DzhoxJTCwEGNBmPHCKbIol9OWYr7bPP7Cd3MqSnVK40oW7c5E+nV5Nmt8f4kYJXbDML7xhtjFMU6PmiEECdGpZ2j/mKjDpPd/NjddzSrc2pmhG0UTs5Ov4cACkDCvUPZCBTqBZV6brY8jsGg2FdCyi5gDmptNQBRzNhDs+UDilGsf0y6MeN3SWpS3HXM3pYwcWl4IiAHTN588VCBJtFqsiRljdYM9Jgd8c9ll9o3wR/MXhgrHo8G/FtjvqywNhM3HRRkaExkQ1LGLlR96iD14+2O4DHdW1zktj+PwvuzQs0/nl1XNrbCfl3ZkcF\"],\"pscMgtUser\":\"management\",\"clusterMorValue\":\"domain-c7\",\"pscMgtPassword\":\"Testvxrail123!\",\"pscIp\":\"10.62.91.137\",\"clusterName\":null,\"ssoTenant\":\"vsphere.local\",\"vcenterIp\":\"10.62.91.135\"}";
    private static String POST_JSON = "{\n" +
        "  \"vcenterPassword\": \"Testvxrail123!\",\n" +
        "  \"hostAccounts\": [\n" +
        "    {\n" +
        "      \"hostName\": \"app25-01.localdomain.local\",\n" +
        "      \"hostRootAccount\": {\n" +
        "        \"username\": \"root\",\n" +
        "        \"password\": \"blowfish:15df1500dc0a1afdd247ef2062b8c8f1\",\n" +
        "        \"passwordConfirm\": null\n" +
        "      },\n" +
        "      \"hostManagementAccount\": {\n" +
        "        \"username\": \"management\",\n" +
        "        \"password\": \"blowfish:386d88388d3336b1f7273a30a01a353c\",\n" +
        "        \"passwordConfirm\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"hostName\": \"app25-02.localdomain.local\",\n" +
        "      \"hostRootAccount\": {\n" +
        "        \"username\": \"root\",\n" +
        "        \"password\": \"blowfish:15df1500dc0a1afdd247ef2062b8c8f1\",\n" +
        "        \"passwordConfirm\": null\n" +
        "      },\n" +
        "      \"hostManagementAccount\": {\n" +
        "        \"username\": \"management\",\n" +
        "        \"password\": \"blowfish:386d88388d3336b1f7273a30a01a353c\",\n" +
        "        \"passwordConfirm\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"hostName\": \"app25-03.localdomain.local\",\n" +
        "      \"hostRootAccount\": {\n" +
        "        \"username\": \"root\",\n" +
        "        \"password\": \"blowfish:15df1500dc0a1afdd247ef2062b8c8f1\",\n" +
        "        \"passwordConfirm\": null\n" +
        "      },\n" +
        "      \"hostManagementAccount\": {\n" +
        "        \"username\": \"management\",\n" +
        "        \"password\": \"blowfish:386d88388d3336b1f7273a30a01a353c\",\n" +
        "        \"passwordConfirm\": null\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"rsaPublicKeys\": [\n" +
        "    \"AAAAB3NzaC1yc2EAAAADAQABAAABAQD3ThJxi8pJVhlqlfwn8hNVxI/Voc0NfiN1OX5123BlVpCezar3zGvZyXXxwRahHW1/nQtoMFTojwFm9/d2087t3DhC0jLqtiX3KhShz7ph3F0U6S74T6tuJWIt7KwBIPjLsHNzNTBneryDm/9NscWMb8i81C4trTzEikA7JTWJfE6XvYACpeoLa8yeN8vGIJMALqRQHBWXDdUgb1yZYMXLv1wtO56y+dAQCBkHeu50jST/LhXhMQseAraFe5G/0GOk3F8uwFebhVcGt/uyk4kDUlVwsKHfvVsCE2QXj5iaN1Tr8f4nPxlCEiK0FfTBi2Ut2AQXMHrqJQj6CDni9mKb\",\n" +
        "    \"AAAAB3NzaC1yc2EAAAADAQABAAABAQC8LSKxD75USjZTQdrgzaJOxH0Zzl9rHEwnovBm/9qmLupVS9nKACbMyRCoFWEsu7FipDGq963ljh32pRNYX4DXqTwu0w5Qtxz9wRFfPQ9X4cHarP5OMA/CY9XsZdJMxATjzfpACnOILeyQSy4DlxzQJeC8vjZ2vxVyuMiquNmdy+jASh/PGwOkCFo8QOVltL+R0/VmCOCL9zeUjntO5/mAtv3Alna0qesJFxY2LwLMfZcDnAhXR8MVY2g0daCJpjmBP5sWFu2gVa81JfieEiqXbUAvzHqpE/T67milqUzYUex1FUyxJVaFX0AZHr/HRhQiepPwHaZ7VHqqYUD5IhNn\",\n" +
        "    \"AAAAB3NzaC1yc2EAAAADAQABAAABAQCmBrRjy12o7r07r27XEI+KJkSGfZGlp71xuL26Mc6Y5gRbY4SHsnrzOq1QCG0nqRksRnijKMAR4apsFYPLuhLE8YJBgY0Mj1oaiO0av5AbIU5cWLAaC1TFV/PWl/1MGABGXdxNpdb+w0obsyGiaosvQz8RNFtgBD+0K2V/Ojbj6Xcwsq+yjTdOLPik4pQz1lo1WJ7zR5dAUlLp7UU7HW/aq/w8NE4UU2iGLBa30i/kFTvzd1RBYnhEVV8ZHvYgeOI/OEByWcxPbbHtnzF8OKAvMToZPrsuCBTJsjOJaOxjmsaRz0u4w6EtUGn7inPRsmjqr8YNXJjLKKr6hzaK7iXj\"\n" +
        "  ],\n" +
        "  \"pscMgtUser\": \"management\",\n" +
        "  \"clusterMorValue\": \"domain-c7\",\n" +
        "  \"pscMgtPassword\": \"Testvxrail123!\",\n" +
        "  \"pscIp\": \"10.62.92.79\",\n" +
        "  \"clusterName\": null,\n" +
        "  \"ssoTenant\": \"vsphere.local\",\n" +
        "  \"vcenterIp\": \"10.62.92.78\"\n" +
        "}";

    public static void main(String[] args) throws IOException {

//        sendGET();
//        System.out.println("GET DONE");
        sendPOST();
        System.out.println("POST DONE");
    }

    private static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        disableSSLCheck(con);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    private static void sendPOST() throws IOException {
        URL obj = new URL(POST_URL);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        disableSSLCheck(con);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_JSON.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

    private static void disableSSLCheck(HttpsURLConnection connection) {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            connection.setSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            System.out.println("Failed to disable SSL check" + e);
        }

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return "10.62.92.73".equals(hostname);
            }
        };

        // Install the all-trusting host verifier
        connection.setHostnameVerifier(allHostsValid);
    }

}
