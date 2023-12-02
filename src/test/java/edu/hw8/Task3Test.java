package edu.hw8;

import org.junit.jupiter.api.Test;
import java.util.Map;

public class Task3Test {
    @Test
    void decryptorTest() {
        var map = Map.of(
                "e10adc3949ba59abbe56e057f20f883e", "a.v.petrov",
                "d8578edf8458ce06fbc5bb76a58c5ca4", "v.v.belov");
        var decryptor = new Task3Decryptor(map);
        var s = decryptor.decryptPasswords(8);
        for (var elem : s.entrySet()) {
            System.out.println(elem);
        }
    }
}
