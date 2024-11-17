package encriptado;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class Encrypter {
    private final static String algoritmo = "AES";
    private final static String tipoCifrado = "AES/CBC/PKCS5Padding";

    public static byte[] encrypt(String key, byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance(tipoCifrado);
        SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        byte[] salt = new byte[32];
        random.nextBytes(iv);
        random.nextBytes(salt);
        KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretFactory.generateSecret(keySpec).getEncoded(), algoritmo);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(input);
    }

}