package com.ldy.jackson.write;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by yanz3 on 6/20/17.
 */
public class WriteJson {

    public static void main(String[] args) throws IOException, JSONException {
        ConfigurationJournal configurationJournal = new ConfigurationJournal();
        ObjectMapper mapper = new ObjectMapper();

        try {

            // Writing to a file
            //mapper.writeValue(new File("/Users/yanz3/test/jounal.json"), configurationJournal);

            String str = mapper.writeValueAsString(configurationJournal);
            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //initConfigJson();

    }

    private static void initConfigJson() throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", "4.6");
        jsonObject.put("network", JSONObject.NULL);
        jsonObject.put("hostnames", JSONObject.NULL);
        jsonObject.put("accounts", JSONObject.NULL);
        jsonObject.put("global", JSONObject.NULL);
        jsonObject.put("externalVC", JSONObject.NULL);
        jsonObject.put("vendor", JSONObject.NULL);
        jsonObject.put("vmwSolution", JSONObject.NULL);
        jsonObject.put("expectedMarvinIds", JSONObject.NULL);


        try (FileWriter file = new FileWriter("/Users/yanz3/test/file1.txt")) {
            file.write(jsonObject.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonObject);
        }
    }

}
