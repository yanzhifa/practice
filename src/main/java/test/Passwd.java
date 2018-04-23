package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

public class Passwd {
    // % ( ) & $ ^ - + * 
    //private static final String specialCharacters =  "!@$%^&*()-+=";
    private static final String specialCharacters =  "#";
    
    
    
    public static String getrandomPassword() {
        ArrayList<String> passwordContainer = new ArrayList<String>();
        passwordContainer.add(RandomStringUtils.randomNumeric(1));   //atleast one number
        passwordContainer.add(RandomStringUtils.randomAlphabetic(1).toUpperCase()); //atleast one uppercase character
        passwordContainer.add(RandomStringUtils.randomAlphabetic(1).toLowerCase());   //atleast one lowercase character
        passwordContainer.add(RandomStringUtils.random(1, specialCharacters)); //atleast one other character (not alphanumeric)
        // minlen = 8. Make it difficult for brute force attack.
        BigInteger bi = new BigInteger(130, new SecureRandom());
        String secure = bi.toString(32);
        String secureString = secure.substring(0, Integer.parseInt("20") - 4);
        passwordContainer.add(secureString);
        Collections.shuffle(passwordContainer);
        final StringBuilder sb = new StringBuilder();
        for(String s: passwordContainer) {
            sb.append(s);
        }
        return sb.toString();
    }
    
    private static void write(Properties newProperties) throws IOException {
        final File tempFile = File.createTempFile("marvin", "properties", new File("/Users/yanz3/Practices/java/file"));
        final FileWriter fileWriter = new FileWriter(tempFile);
        newProperties.store(fileWriter, "Marvin properties, do not modify.");
        fileWriter.flush();
        fileWriter.close();
    }
    
    public static void main(String[] args) {
        BigInteger bi = new BigInteger(150, new SecureRandom());
        int b = 2 ^ 32;
        
        System.out.println(bi.toString());
        System.out.println(bi.toString(32));
        
        for(int i=0;i<10;i++) {
            String password = getrandomPassword();
            System.out.println(password);
            Properties properties = new Properties();
            properties.setProperty("password", password);
            try {
                write(properties);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("------------");
        }
    }
}
