package edu.hw8;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.Executors;

public class Task3MultiThreadDecryptor extends Task3AbstractDecryptor {

    public Task3MultiThreadDecryptor(Map<String, String> encryptedPasswords) {
        super(encryptedPasswords);
    }

    private class RunnableTask implements Runnable {
        private long value;
        private final int threads;

        public RunnableTask(long value, int threads) {
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
                executor.submit(new RunnableTask(i, threads));
            }
        }
        return decryptedPasswords;
    }
}
