package com.ldy.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanz3 on 6/18/17.
 */
public class LoadFile {


    public static void main(String[] args) {

        File file = new File("/Users/yanz3/model.json");
        final ObjectMapper jsonObjectMapper = new ObjectMapper();
        final ObjectReader jsonObjectReader = jsonObjectMapper.readerFor(Model.class);
        Model model = null;

        try {
            model = jsonObjectReader.readValue(file);
            System.out.println(model);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*List<String> list = Arrays.asList(new String[]{"aa","bb"});
        System.out.println(list);

        if (!file.exists()) {
            System.out.println("Not Exist...");
        } else {
            List<String> lines = null;
            try {
                lines = FileUtils.readLines(file);
                System.out.println(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }
}
