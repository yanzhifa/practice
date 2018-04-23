package com.ldy.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSanitizer {


    public static String sanitizeJsonString(String jsonString, List<String> fieldNames) {
        try {
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };

            HashMap<String, Object> jsonMap = mapper.readValue(jsonString, typeRef);
            for (String fieldName : fieldNames) {
                if (jsonMap.containsKey(fieldName)) {
                    jsonMap.put(fieldName, "********");
                }
            }

            String result = mapper.writeValueAsString(jsonMap);
            return result;
        } catch (IOException ex) {
            System.out.println(ex);
            return "";
        }

    }
    
    public static void main(String[] args) {
        String value = "{\"vcenterPassword\":\"Testvxrail123!\",\"hostAccounts\":[{\"hostName\":\"app32-01.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}},{\"hostName\":\"app32-02.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}},{\"hostName\":\"app32-03.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}}],\"rsaPublicKeys\":[\"AAAAB3NzaC1yc2EAAAADAQABAAABAQDb/DCfBo75bv7gpAGRq7aQdy3Aw5FTC2A5WR8Lu1upiaPtJSsUiY/S4KFgR/iLSrVCP1fH9WJN8mEKF1icyXkUsA6p6blxfPKHeorztXUuU12GNPkqnjpJu2tV26nwk+8pC9NIm3PL0ZiY0o4pa2yhXPmjA6Py6RXp1ZPN3sVYVFrRBaBdd2rMzbyQOi5Wn3qJR8q25CW6yNUMeQkEDqcEez/j+zKnWn5ZpREUSZ+D5g3uTsu6aK8MRXdiP1DZnoc2QWOZo+7LrSX7datdLIGO6K49QAWIlmsTRpdbYWu9yT88JUlup8LcpGuYMGuHbQLUr2J9NS+ss5K3bMIKlQ/7\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuXcfIJ97r1BDBc4ai0MeYyknGYE2XjvePHISkbSm1nXo6/RA0eMggXq9NnAzgUj+PYsKe/gKDakWgLlY9ies+nJ4K/QZaal88asemi+a871xbx61FS73KP6jl/Vx3rURuFTsOvVf/wEP03e97u6GM5cnpLby9rx2G0wv0sxd8oVN1FFYlKuhp7xDqFiTAry3ioTiqN5ra8lJ54sIqAVG6rvc1c8flwlfSg5dbb69Bsik6MGzXJ4/MhIgrVS7hqKhX6wj7V51JI5ED9JOtJl+LqHN9e7vbVrQX77zKW/XR5cqZIVxa36x6WBOAty56Guq9FA2jhM4IJ1TZ76/hrNxf\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuGRDYa79sjBgvyI3LSlospJUs9DzhoxJTCwEGNBmPHCKbIol9OWYr7bPP7Cd3MqSnVK40oW7c5E+nV5Nmt8f4kYJXbDML7xhtjFMU6PmiEECdGpZ2j/mKjDpPd/NjddzSrc2pmhG0UTs5Ov4cACkDCvUPZCBTqBZV6brY8jsGg2FdCyi5gDmptNQBRzNhDs+UDilGsf0y6MeN3SWpS3HXM3pYwcWl4IiAHTN588VCBJtFqsiRljdYM9Jgd8c9ll9o3wR/MXhgrHo8G/FtjvqywNhM3HRRkaExkQ1LGLlR96iD14+2O4DHdW1zktj+PwvuzQs0/nl1XNrbCfl3ZkcF\"],\"pscMgtUser\":\"management\",\"clusterMorValue\":\"domain-c7\",\"pscMgtPassword\":\"Testvxrail123!\",\"pscIp\":\"10.62.91.137\",\"clusterName\":null,\"ssoTenant\":\"vsphere.local\",\"vcenterIp\":\"10.62.91.135\"}";
        String value1 = "{\"vcenterPassword\":\"Testvxrail123!\",\"hostAccounts\":[{\"hostName\":\"app32-01.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}},{\"hostName\":\"app32-02.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}},{\"hostName\":\"app32-03.localdomain.local\",\"hostRootAccount\":{\"username\":\"root\",\"password\":\"blowfish:6c19a4cf14b7c91670481848b58ddfa5\",\"passwordConfirm\":null},\"hostManagementAccount\":{\"username\":\"management\",\"password\":\"blowfish:10001ed4f0d2680a89dd4fe6d5f0d269\",\"passwordConfirm\":null}}],\"rsaPublicKeys\":[\"AAAAB3NzaC1yc2EAAAADAQABAAABAQDb/DCfBo75bv7gpAGRq7aQdy3Aw5FTC2A5WR8Lu1upiaPtJSsUiY/S4KFgR/iLSrVCP1fH9WJN8mEKF1icyXkUsA6p6blxfPKHeorztXUuU12GNPkqnjpJu2tV26nwk+8pC9NIm3PL0ZiY0o4pa2yhXPmjA6Py6RXp1ZPN3sVYVFrRBaBdd2rMzbyQOi5Wn3qJR8q25CW6yNUMeQkEDqcEez/j+zKnWn5ZpREUSZ+D5g3uTsu6aK8MRXdiP1DZnoc2QWOZo+7LrSX7datdLIGO6K49QAWIlmsTRpdbYWu9yT88JUlup8LcpGuYMGuHbQLUr2J9NS+ss5K3bMIKlQ/7\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuXcfIJ97r1BDBc4ai0MeYyknGYE2XjvePHISkbSm1nXo6/RA0eMggXq9NnAzgUj+PYsKe/gKDakWgLlY9ies+nJ4K/QZaal88asemi+a871xbx61FS73KP6jl/Vx3rURuFTsOvVf/wEP03e97u6GM5cnpLby9rx2G0wv0sxd8oVN1FFYlKuhp7xDqFiTAry3ioTiqN5ra8lJ54sIqAVG6rvc1c8flwlfSg5dbb69Bsik6MGzXJ4/MhIgrVS7hqKhX6wj7V51JI5ED9JOtJl+LqHN9e7vbVrQX77zKW/XR5cqZIVxa36x6WBOAty56Guq9FA2jhM4IJ1TZ76/hrNxf\",\"AAAAB3NzaC1yc2EAAAADAQABAAABAQCuGRDYa79sjBgvyI3LSlospJUs9DzhoxJTCwEGNBmPHCKbIol9OWYr7bPP7Cd3MqSnVK40oW7c5E+nV5Nmt8f4kYJXbDML7xhtjFMU6PmiEECdGpZ2j/mKjDpPd/NjddzSrc2pmhG0UTs5Ov4cACkDCvUPZCBTqBZV6brY8jsGg2FdCyi5gDmptNQBRzNhDs+UDilGsf0y6MeN3SWpS3HXM3pYwcWl4IiAHTN588VCBJtFqsiRljdYM9Jgd8c9ll9o3wR/MXhgrHo8G/FtjvqywNhM3HRRkaExkQ1LGLlR96iD14+2O4DHdW1zktj+PwvuzQs0/nl1XNrbCfl3ZkcF\"],\"pscMgtUser\":\"management\",\"clusterMorValue\":\"domain-c7\",\"pscMgtPassword\":\"Testvxrail123!\",\"pscIp\":\"10.62.91.137\",\"clusterName\":null,\"ssoTenant\":\"vsphere.local\",\"vcenterIp\":\"10.62.91.135\"}";
        System.out.println(value);
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("vcenterPassword");
        fieldNames.add("pscMgtPassword");
        
        String result = sanitizeJsonString(value, fieldNames);
        System.out.println(result);
    }
}
