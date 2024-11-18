package crypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

public enum ActionMode {

    ENCRYPT(CipherMode.ENCRYPT){
        private byte[] ivBytes = new byte[0];
        @Override
        public byte[] getIvBytes(byte[] input){
            if (ivBytes.length == 0){
                ivBytes = new byte[16];
                new SecureRandom().nextBytes(ivBytes);
            }
            return ivBytes;
        }
        @Override
        public byte[] getResult(byte[] input, Cipher cipher) throws IOException, IllegalBlockSizeException, BadPaddingException {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write(this.getIvBytes(input));
            output.write(cipher.doFinal(input));
            return output.toByteArray();
        }
    }, DECRYPT(CipherMode.DECRYPT){
        @Override
        public byte[] getIvBytes(byte[] input){
            byte[] result = new byte[16];
            System.arraycopy(input, 0, result, 0, 16);
            return result;
        }
        @Override
        public byte[] getResult(byte[] input, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
            byte[] toDecrypt = new byte[input.length - 16];
            System.arraycopy(input, 16, toDecrypt, 0, toDecrypt.length);
            return cipher.doFinal(toDecrypt);
        }
    };

    private final int cipherMode;

    ActionMode(int cipherMode){
        this.cipherMode = cipherMode;
    }

    public int getCipherMode() {
        return cipherMode;
    }

    public abstract byte[] getIvBytes(byte[] input);

    public abstract byte[] getResult(byte[] input, Cipher cipher) throws IOException, IllegalBlockSizeException, BadPaddingException;

    private static class CipherMode {
        public static int ENCRYPT = Cipher.ENCRYPT_MODE;

        public static int DECRYPT = Cipher.DECRYPT_MODE;
    }
}