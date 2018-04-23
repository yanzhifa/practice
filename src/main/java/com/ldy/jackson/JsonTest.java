package com.ldy.jackson;

import org.json.JSONObject;

/**
 * Created by yanz3 on 10/17/17.
 */
public class JsonTest {

    private boolean istrue;

    public boolean isIstrue() {
        return istrue;
    }

    public void setIstrue(boolean istrue) {
        this.istrue = istrue;
    }

    public static void main(String[] args) {
        String output = "{\"data\": \"pages\", \"message\": \"just for test\"}";
        JSONObject jObject  = new JSONObject(output); // json
        String data = jObject.getString("data"); // get data object
        System.out.println(data);
//        String projectname = data.getString("name"); // get the name from data.
    }
}
