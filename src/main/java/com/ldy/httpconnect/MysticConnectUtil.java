package com.ldy.httpconnect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;


public class MysticConnectUtil {

    private static final Logger logger = LoggerFactory.getLogger(MysticConnectUtil.class);

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

    public enum RequestType {
        INIT("initialize"),
        EXPAND_NODE("expand node");

        private String val;

        RequestType(String val) {
            this.val = val;
        }

        public String value() {
            return val;
        }
    }

    public static String getHttpResponse(HttpsURLConnection connection) throws IOException {
        InputStream in = null;
        if (connection.getResponseCode() > 299) {
            in = connection.getErrorStream();
            if (in == null) {
                in = connection.getInputStream();
            }
        } else {
            in = connection.getInputStream();
        }
        if (in == null) {
            return "";
        }
        try {
            int contentLength = connection.getContentLength();
            // If we know the content length, read it directly into a buffer.
            if (contentLength != -1) {
                byte[] output = new byte[connection.getContentLength()];

                int c = 0;
                while (c < contentLength) {
                    int read = in.read(output, c, contentLength - c);
                    if (read == -1) {
                        // EOF!
                        throw new EOFException(
                                "EOF reading response at position " + c + " size " + (contentLength - c));
                    }
                    c += read;
                }

                return new String(output);
            } else {
                // Content length is indeterminate.
                // logger.debug("Content length is unknown. Buffering output.");
                // use a ByteArrayOutputStream to collect the response.
                byte[] buffer = new byte[4096];
                ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
                int c = 0;
                while ((c = in.read(buffer)) != -1) {
                    byteOutputStream.write(buffer, 0, c);
                }
                byteOutputStream.close();

                return byteOutputStream.toString();
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void sendPostRequest(String path, String body, String requestType)
            throws ConfigurationException {
        HttpsURLConnection connection = null;
        try {
            URL mysticPostUrl = new URL(path);

            connection = getHttpsURLConnection(mysticPostUrl);
            disableSSLCheck(connection);
            connection.connect();
            OutputStream out = connection.getOutputStream();
            out.write(body.getBytes());
            out.flush();
            System.out.println("Get status response code from mystic backend: " + connection.getResponseCode()
                    + ". Message is: " + connection.getResponseMessage());
            if (connection.getResponseCode() != 200) {
                System.out.println("Fail to " + requestType + " in mystic");
                System.out.println("Post entity is: " + hidePasswordField(body));
                System.out.println("Get status response code from mystic backend: " + connection.getResponseCode());
                System.out.println("Get response body from mystic backend: " + getHttpResponse(connection));
                throw new ConfigurationException("Fail to " + requestType + " in mystic");
            }
        } catch (MalformedURLException e) {
            logger.error("Fail to malformed URL " + path + ".", e);
            System.out.println(e);
            throw new ConfigurationException("Fail to " + requestType + " in mystic");
        } catch (IOException e) {
            System.out.println(e);
            logger.error("Fail to connect or communicate with mystic", e);
            throw new ConfigurationException("Fail to " + requestType + " in mystic");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String hidePasswordField(String body) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(body.split(",")).forEach(pair -> {
            String[] keyValue = pair.split(":");
            if (keyValue != null && keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                if (key.contains("Password") || key.contains("password")) {
                    String hideText = "\"******\"";
                    if (value.endsWith("}")) {
                        hideText += "}";
                    }
                    stringBuilder.append(key).append(":").append(hideText);
                } else {
                    stringBuilder.append(pair);
                }
            } else {
                stringBuilder.append(pair);
            }
            stringBuilder.append(",");
        });
        if (stringBuilder.lastIndexOf(",") > -1) {
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }
        return stringBuilder.toString();
    }

    private static HttpsURLConnection getHttpsURLConnection(URL mysticInitUrl) throws IOException, ProtocolException {
        HttpsURLConnection conn;
        conn = (HttpsURLConnection) mysticInitUrl.openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(300000);
        conn.setReadTimeout(900000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        return conn;
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


    public static void postTest(RequestType type) {

    }

    public static void main(String[] args) {
        String value = "{\"vcenterPassword\":\"Testvxrail123!\",\"hostAccounts\":[{\"hostName\":\"app32-01.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}},{\"hostName\":\"app32-02.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}},{\"hostName\":\"app32-03.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}}],\"rsaPublicKeys\":[\"AAAAB3NzaC1yc2EAAAADAQABAAABAQDb/DCfBo75bv7gpAGRq7aQdy3Aw5FTC2A5WR8Lu1upiaPtJSsUiY/S4KFgR/iLSrVCP1fH9WJN8mEKF1icyXkUsA6p6blxfPKHeorztXUuU12GNPkqnjpJu2tV26nwk+8pC9NIm3PL0ZiY0o4pa2yhXPmjA6Py6RXp1ZPN3sVYVFrRBaBdd2rMzbyQOi5Wn3qJR8q25CW6yNUMeQkEDqcEez/j+zKnWn5ZpREUSZ+D5g3uTsu6aK8MRXdiP1DZnoc2QWOZo+7LrSX7datdLIGO6K49QAWIlmsTRpdbYWu9yT88JUlup8LcpGuYMGuHbQLUr2J9NS+ss5K3bMIKlQ/7\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuXcfIJ97r1BDBc4ai0MeYyknGYE2XjvePHISkbSm1nXo6/RA0eMggXq9NnAzgUj+PYsKe/gKDakWgLlY9ies+nJ4K/QZaal88asemi+a871xbx61FS73KP6jl/Vx3rURuFTsOvVf/wEP03e97u6GM5cnpLby9rx2G0wv0sxd8oVN1FFYlKuhp7xDqFiTAry3ioTiqN5ra8lJ54sIqAVG6rvc1c8flwlfSg5dbb69Bsik6MGzXJ4/MhIgrVS7hqKhX6wj7V51JI5ED9JOtJl+LqHN9e7vbVrQX77zKW/XR5cqZIVxa36x6WBOAty56Guq9FA2jhM4IJ1TZ76/hrNxf\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuGRDYa79sjBgvyI3LSlospJUs9DzhoxJTCwEGNBmPHCKbIol9OWYr7bPP7Cd3MqSnVK40oW7c5E+nV5Nmt8f4kYJXbDML7xhtjFMU6PmiEECdGpZ2j/mKjDpPd/NjddzSrc2pmhG0UTs5Ov4cACkDCvUPZCBTqBZV6brY8jsGg2FdCyi5gDmptNQBRzNhDs+UDilGsf0y6MeN3SWpS3HXM3pYwcWl4IiAHTN588VCBJtFqsiRljdYM9Jgd8c9ll9o3wR/MXhgrHo8G/FtjvqywNhM3HRRkaExkQ1LGLlR96iD14+2O4DHdW1zktj+PwvuzQs0/nl1XNrbCfl3ZkcF\"],\"pscMgtUser\":\"management\",\"clusterMorValue\":\"domain-c7\",\"pscMgtPassword\":\"Testvxrail123!\",\"pscIp\":\"10.62.91.137\",\"clusterName\":null,\"ssoTenant\":\"vsphere.local\",\"vcenterIp\":\"10.62.91.135\"}";

        String json_date = "{\"vcenterPassword\":\"Testvxrail123!\",\"hostAccounts\":[{\"hostName\":\"app25-01.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:15df1500dc0a1afdd247ef2062b8c8f1\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:386d88388d3336b1f7273a30a01a353c\",\"passwordConfirm\":null}},{\"hostName\":\"app25-02.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:15df1500dc0a1afdd247ef2062b8c8f1\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:386d88388d3336b1f7273a30a01a353c\",\"passwordConfirm\":null}},{\"hostName\":\"app25-03.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:15df1500dc0a1afdd247ef2062b8c8f1\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:386d88388d3336b1f7273a30a01a353c\",\"passwordConfirm\":null}}],\"rsaPublicKeys\":[\"AAAAB3NzaC1yc2EAAAADAQABAAABAQD3ThJxi8pJVhlqlfwn8hNVxI/Voc0NfiN1OX5123BlVpCezar3zGvZyXXxwRahHW1/nQtoMFTojwFm9/d2087t3DhC0jLqtiX3KhShz7ph3F0U6S74T6tuJWIt7KwBIPjLsHNzNTBneryDm/9NscWMb8i81C4trTzEikA7JTWJfE6XvYACpeoLa8yeN8vGIJMALqRQHBWXDdUgb1yZYMXLv1wtO56y+dAQCBkHeu50jST/LhXhMQseAraFe5G/0GOk3F8uwFebhVcGt/uyk4kDUlVwsKHfvVsCE2QXj5iaN1Tr8f4nPxlCEiK0FfTBi2Ut2AQXMHrqJQj6CDni9mKb\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQC8LSKxD75USjZTQdrgzaJOxH0Zzl9rHEwnovBm/9qmLupVS9nKACbMyRCoFWEsu7FipDGq963ljh32pRNYX4DXqTwu0w5Qtxz9wRFfPQ9X4cHarP5OMA/CY9XsZdJMxATjzfpACnOILeyQSy4DlxzQJeC8vjZ2vxVyuMiquNmdy+jASh/PGwOkCFo8QOVltL+R0/VmCOCL9zeUjntO5/mAtv3Alna0qesJFxY2LwLMfZcDnAhXR8MVY2g0daCJpjmBP5sWFu2gVa81JfieEiqXbUAvzHqpE/T67milqUzYUex1FUyxJVaFX0AZHr/HRhQiepPwHaZ7VHqqYUD5IhNn\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCmBrRjy12o7r07r27XEI+KJkSGfZGlp71xuL26Mc6Y5gRbY4SHsnrzOq1QCG0nqRksRnijKMAR4apsFYPLuhLE8YJBgY0Mj1oaiO0av5AbIU5cWLAaC1TFV/PWl/1MGABGXdxNpdb+w0obsyGiaosvQz8RNFtgBD+0K2V/Ojbj6Xcwsq+yjTdOLPik4pQz1lo1WJ7zR5dAUlLp7UU7HW/aq/w8NE4UU2iGLBa30i/kFTvzd1RBYnhEVV8ZHvYgeOI/OEByWcxPbbHtnzF8OKAvMToZPrsuCBTJsjOJaOxjmsaRz0u4w6EtUGn7inPRsmjqr8YNXJjLKKr6hzaK7iXj\"],\"pscMgtUser\":\"management\",\"clusterMorValue\":\"domain-c7\",\"pscMgtPassword\":\"Testvxrail123!\",\"pscIp\":\"10.62.92.79\",\"clusterName\":null,\"ssoTenant\":\"vsphere.local\",\"vcenterIp\":\"10.62.92.78\"}";
        String path = "https://10.62.92.73/extension/mystic/settings/simple";
        try {
            sendPostRequest(path, json_date, "initialize");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }
}

