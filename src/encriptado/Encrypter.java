package encriptado;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

public class Encrypter {
    private final static String algoritmo = "AES";
    private final static String tipoCifrado = "AES/CBC/PKCS5Padding";

    public static byte[] encrypt(String key, byte[] iv, byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance(tipoCifrado);
        SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(key.toCharArray(), "SALT".getBytes(), 65536, 256);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretFactory.generateSecret(keySpec).getEncoded(), algoritmo);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(input);
    }

}