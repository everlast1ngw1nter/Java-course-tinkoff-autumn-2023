package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.assertj.core.api.Assertions.assertThat;

public class WordInfoTest {
    static class FakeDict implements Dictionary {

        @Override
        public String getWord() {
            return "abcd";
        }
    }
    private WordInformation wordInfo;

    @BeforeEach
    void setUp() {
        wordInfo = new WordInfo(new FakeDict());
    }

    @Test
    void maskTest() {
        String mask = wordInfo.getMask(new HashSet<>());
        assertThat(mask)
                .isEqualTo("****");
    }

    @Test
    void isInWordTrueTest() {
        boolean isInWord = wordInfo.isInWord('b');
        assertThat(isInWord)
                .isTrue();
    }

    @Test
    void isInWordFalseTest() {
        boolean isInWord = wordInfo.isInWord('e');
        assertThat(isInWord)
                .isFalse();
    }
}
