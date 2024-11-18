package crypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class AESCipher {

    public static byte[] encrypt(String key, byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] salt = new byte[32];
        new SecureRandom().nextBytes(salt);
        KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretFactory.generateSecret(keySpec).getEncoded(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(input);
    }

}