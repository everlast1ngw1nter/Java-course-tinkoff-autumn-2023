package edu.project1;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

public class WordDictTest {
    private final static String[] WORDS = {"hangman", "java", "backend", "tinkoff"};

    @Test
    void getWordPredictableTest() {
        boolean isPredictable = true;
        var words = Arrays.asList(WORDS);
        for (int i = 0; i < 10; i++) {
            var dict = new WordDict();
            String word = dict.getWord();
            if (!words.contains(word)) {
                isPredictable = false;
            }
        }
        assertThat(isPredictable).isTrue();
    }
}