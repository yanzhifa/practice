package com.ldy.file;

import java.io.File;
import java.io.IOException;

/**
 * Created by yanz3 on 3/15/18.
 */
public class FilePermissionTest {

    public static void main(String[] args) {
        String filePath = "/Users/yanz3/test/lock.txt";

        File file = new File(filePath);
        if(file.exists()) {
            try {
                file.createNewFile();
                file.setWritable(true, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
