package edu.hw8;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    void multiThreadDecryptorTest() {
        var map = Map.of(
                "202cb962ac59075b964b07152d234b70", "a.v.petrov",
                "76d80224611fc919a5d54f0ff9fba446", "v.v.belov");
        var decryptor = new Task3MultiThreadDecryptor(map);
        var decryptedPasswords = decryptor.decryptPasswords(10);
        assertTrue(decryptedPasswords.containsValue("123"));
        assertTrue(decryptedPasswords.containsValue("qwe"));
    }

    @Test
    void singleThreadDecryptorTest() {
        var map = Map.of(
                "202cb962ac59075b964b07152d234b70", "a.v.petrov",
                "76d80224611fc919a5d54f0ff9fba446", "v.v.belov");
        var decryptor = new Task3SingleThreadDecryptor(map);
        var decryptedPasswords = decryptor.decryptPasswords();
        assertTrue(decryptedPasswords.containsValue("123"));
        assertTrue(decryptedPasswords.containsValue("qwe"));
    }
}
