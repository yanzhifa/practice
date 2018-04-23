package com.ldy.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by yanz3 on 12/21/16.
 */
public class RWFile {

    public static void rwFile() {
        String fpath = "/Users/yanz3/disk_led_2";
        File f = new File(fpath);

        try {
            List<String> lines = FileUtils.readLines(f);
            System.out.println(lines);
            lines.set(0,"45");
            System.out.println(lines);
            FileUtils.writeLines(f,lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        rwFile();
    }
}
