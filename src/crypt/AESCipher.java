package crypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

public class AESCipher {

    public static byte[] doAction(String password, String salt, int modeIndex, byte[] input) throws Exception {
        //Esto es una mala practica pero para poder tener bidireccion para encriptar y desencriptar es que se utiliza
        //el mismo vector de inicializacion
        byte[] ivByte = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivParameter = new IvParameterSpec(ivByte);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(),
                salt.getBytes(StandardCharsets.UTF_8), 65536, 256);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretFactory.generateSecret(keySpec).getEncoded(),
                "AES");
        cipher.init(modeIndex, secretKeySpec, ivParameter);
        return cipher.doFinal(input);
    }

}