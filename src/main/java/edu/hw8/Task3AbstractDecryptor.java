package edu.hw8;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Task3AbstractDecryptor {

    protected final Map<String, String> encryptedPasswords;

    protected final Map<String, String> decryptedPasswords;

    protected static final String POSSIBLE_SYMBOLS = "123456789qwertyuiopasdfghjklzxcvbnm";


    public Task3AbstractDecryptor(Map<String, String> encryptedPasswords) {
        this.encryptedPasswords = encryptedPasswords;
        this.decryptedPasswords = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("ParameterAssignment")
    protected String nextPassword(long stringNumber) {
        var builder = new StringBuilder();
        do {
            var nextCharNumber = stringNumber % POSSIBLE_SYMBOLS.length();
            builder.append(POSSIBLE_SYMBOLS.charAt((int) nextCharNumber));
            stringNumber = stringNumber / POSSIBLE_SYMBOLS.length();
        }
        while (stringNumber != 0);
        return builder.toString();
    }

    @SuppressWarnings("MagicNumber")
    protected void tryDecryptPassword(String password)
            throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = password.getBytes(StandardCharsets.UTF_8);
        var md = MessageDigest.getInstance("MD5");
        var hexList = new ArrayList<String>();
        for (var elem : md.digest(bytesOfMessage)) {
            var currHex = Integer.toHexString(0xff & elem);
            var currValue = switch (currHex.length()) {
                case 1 -> "0" + currHex;
                case 2 -> currHex;
                default -> throw new IllegalArgumentException();
            };
            hexList.add(currValue);
        }
        var encryptedPossiblePassword = String.join("", hexList);
        if (encryptedPasswords.containsKey(encryptedPossiblePassword)) {
            decryptedPasswords.put(encryptedPasswords.get(encryptedPossiblePassword), password);
        }
    }
}
