package com.ldy.password;

/**
 * BlowFish Util
 */

import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;

public class BlowfishUtil {

    //// Cipher / decipher logic ////
    // default path /etc/vmware-marvin/password.key
    static private final String DEFAULT_KEY_FIlENAME = "/Users/yanz3/Studay/password.key";

    final static private SecretKey blowfishKey;

    static {
        try {
            blowfishKey = initSecretKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static SecretKey initSecretKey() throws Exception {
        final String keyFilename = System.getProperty("marvin.password.keyfile");
        final File keyFile;
        if (keyFilename != null) {
            keyFile = new File(keyFilename);
        } else {
            keyFile = new File(DEFAULT_KEY_FIlENAME);
        }

        if (keyFile.exists()) {
            final byte keyBytes[] = FileUtils.readFileToByteArray(keyFile);
            return new SecretKeySpec(keyBytes, "Blowfish");
        } else {
            final SecureRandom secureRandom = new SecureRandom();
            final byte[] keyBytes = secureRandom.generateSeed(16);
            FileUtils.writeByteArrayToFile(keyFile, keyBytes);
            return new SecretKeySpec(keyBytes, "Blowfish");
        }
    }

    public static String cipher(String text) {
        try {
            // Encrypt the message
            final Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, blowfishKey);
            final byte[] cipherText = cipher.doFinal(text.getBytes());

            // Return with prefix as hex
            return Hex.encodeHexString(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decipher(String text) {
        try {
            // Extract cipher text from hex
            final byte[] cipherText = Hex.decodeHex(text.toCharArray());

            // Decrypt the message
            final Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, blowfishKey);
            return new String(cipher.doFinal(cipherText));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(cipher("Testvxrail123!"));
        System.out.println(decipher("ef4a211bf57e6a0a7b69647d4ce75f6c"));

    }


}

