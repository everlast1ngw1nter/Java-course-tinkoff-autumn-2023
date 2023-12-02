package edu.hw8;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class Task3Decryptor {

    private final Map<String, String> encryptedPasswords;

    private final Map<String, String> decryptedPasswords;

    private static final String POSSIBLE_SYMBOLS = "123456789qwertyuiopasdfghjklzxcvbnm";


    public Task3Decryptor(Map<String, String> encryptedPasswords) {
        this.encryptedPasswords = encryptedPasswords;
        this.decryptedPasswords = new ConcurrentHashMap<>();;
    }

    private class MyRunnable implements Runnable {
        private long value;

        private final int threads;

        public MyRunnable(long value, int threads) {
            this.value = value;
            this.threads = threads;
        }

        public void run() {
            while (decryptedPasswords.size() != encryptedPasswords.size()){
                var possiblePassword = nextPassword(value);
                try {
                    tryDecryptPassword(possiblePassword);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                value += threads;
            }
        }
    }

    public Map<String, String> decryptPasswords(int threads) {
        try (var executor = Executors.newFixedThreadPool(threads)) {
            for (var i = 0; i < threads; i++) {
                executor.submit(new MyRunnable(i, threads));
            }
        }
        return decryptedPasswords;
    }


    private String nextPassword(long stringNumber) {
        var builder = new StringBuilder();
        do {
            var nextCharNumber = stringNumber % POSSIBLE_SYMBOLS.length();
            builder.append(POSSIBLE_SYMBOLS.charAt((int) nextCharNumber));
            stringNumber = stringNumber / POSSIBLE_SYMBOLS.length();
        }
        while (stringNumber != 0);
        return builder.toString();
    }

    private void tryDecryptPassword(String password)
            throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = password.getBytes(StandardCharsets.UTF_8);
        var md = MessageDigest.getInstance("MD5");
        var res = new ArrayList<String>();
        for (var elem : md.digest(bytesOfMessage)) {
            var n = Integer.toHexString(0xff & elem);
            res.add(switch (n.length()) {
                case 1 -> "0" + n;
                case 2 -> n;
                default -> throw new IllegalArgumentException();
            });
        }
        var encryptedPossiblePassword = String.join("", res);
        if (encryptedPasswords.containsKey(encryptedPossiblePassword)) {
            decryptedPasswords.put(encryptedPasswords.get(encryptedPossiblePassword), password);
        }
    }
}
