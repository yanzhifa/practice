package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class WriteFile {

    private static void write(Properties newProperties) throws IOException {
        final File tempFile = File.createTempFile("marvin", "properties", new File("/Users/yanz3/Practices/java/file"));
        final FileWriter fileWriter = new FileWriter(tempFile);
        newProperties.store(fileWriter, "Marvin properties, do not modify.");
        fileWriter.flush();
        fileWriter.close();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
