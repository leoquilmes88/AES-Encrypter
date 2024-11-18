package crypt;

import javax.crypto.Cipher;

public enum ActionMode {

    ENCRYPT(CipherMode.ENCRYPT), DECRYPT(CipherMode.DECRYPT);

    private final int cipherMode;

    ActionMode(int cipherMode){
        this.cipherMode = cipherMode;
    }

    public int getCipherMode() {
        return cipherMode;
    }

    private static class CipherMode {
        public static int ENCRYPT = Cipher.ENCRYPT_MODE;

        public static int DECRYPT = Cipher.DECRYPT_MODE;
    }
}