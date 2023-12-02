package edu.hw8;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Task3SingleThreadDecryptor extends Task3AbstractDecryptor {

    public Task3SingleThreadDecryptor(Map<String, String> encryptedPasswords) {
        super(encryptedPasswords);
    }

    public Map<String, String> decryptPasswords() {
        long currPasswordIndex = 0;
        while (encryptedPasswords.size() != decryptedPasswords.size()) {
            var possiblePassword = nextPassword(currPasswordIndex);
            try {
                tryDecryptPassword(possiblePassword);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            currPasswordIndex++;
        }
        return decryptedPasswords;
    }
}
