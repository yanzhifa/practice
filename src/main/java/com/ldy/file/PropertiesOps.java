package com.ldy.file;

import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by yanz3 on 6/20/17.
 */
public class PropertiesOps {

    public static void main(String[] args) {
        System.out.println(StringUtils.substringBefore("abc.we.com","."));
        //write();
    }

    private static void write() {

        Properties prop = new Properties();
        OutputStream output = null;
        try {

            output = new FileOutputStream("/Users/yanz3/test/config.properties");

            // set the properties value
            prop.setProperty("database", "localhost");
            prop.setProperty("dbuser", "mkyong");
            prop.setProperty("dbpassword", "password");

            // save properties to project root folder
            prop.store(output, "Marvin properties, do not modify.");

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
