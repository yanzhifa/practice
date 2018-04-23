package com.ldy.jackson;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonObjectTest {

    public static void getJsonObj() {

        Map<String, String> modelServiceMap = new HashMap<String, String>();
        Map<String, Map<String, String>> slotMap = new HashMap<String, Map<String,String>>();
        String value = "{\"S2600JF\":\"PhoenixAppliance\",\"QuantaPlex T41S-2U\":\"QuantaAppliance\",\"VMware Virtual Platform\":\"VMwareVirtualPlatformAppliance\"}";
        String slot = "{\"S2600JF\":{\"Cache\":\"0\",\"Capacity\":\"1,2,3,4\"},\"QuantaPlex T41S-2U\":{\"Cache\":\"0\",\"Capacity\":\"1,2,3,4\"},\"Dell\":{\"Cache\":\"0,5\",\"Capacity\":\"1,2,3,4,6,7,8,9\"}}";
        System.out.println(slot);
        JSONObject rootObj = new JSONObject(slot);
        JSONArray arr = rootObj.names();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject solt = rootObj.getJSONObject(arr.getString(i));
            JSONArray soltArr = solt.names();
            Map<String, String> soltValue = new HashMap<String, String>();
            for(int j = 0; j < soltArr.length(); j++) {
                soltValue.put(soltArr.getString(j), solt.getString(soltArr.getString(j)));
            }
            slotMap.put(arr.getString(i), soltValue);
        }
        System.out.println(slotMap);
    }

    public static void main(String[] args) {
        getJsonObj();
    }
}
