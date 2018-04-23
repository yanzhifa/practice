package com.ldy.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * Created by yanz3 on 7/26/17.
 */
public class FileTest {

    public static void whenReadUsingFiles_thenRead() throws IOException {
        String expectedValue = "server 127.127.8.0";
        File file = new File("/etc/ntp.conf");
        String result = Files.toString(file, Charsets.UTF_8);

        System.out.println(result);

        System.out.println(result.contains(expectedValue));

        File file1 = new File("/build");
        System.out.println(file1.mkdir());
    }

    public static void main(String[] args) throws IOException {
        whenReadUsingFiles_thenRead();
    }
}
