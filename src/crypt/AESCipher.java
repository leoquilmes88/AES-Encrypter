package crypt;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

public class AESCipher {

    public static byte[] doAction(String password, String salt, Boolean randomIv, ActionMode actionMode, byte[] input) throws Exception {
        byte[] ivByte = actionMode.getIvBytes(input, randomIv);
        IvParameterSpec ivParameter = new IvParameterSpec(ivByte);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(),
                Hex.decodeHex(salt), 65536, 256);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretFactory.generateSecret(keySpec).getEncoded(),
                "AES");
        cipher.init(actionMode.getCipherMode(), secretKeySpec, ivParameter);
        return actionMode.getResult(input, randomIv, cipher);
    }

}