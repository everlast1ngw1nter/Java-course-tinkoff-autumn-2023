package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionInfoTest {
    static class FakeDict implements Dictionary {

        @Override
        public String getWord() {
            return "abcd";
        }
    }

    private SessionInfo sessionInfo;

    @BeforeEach
    void setUp() {
        WordInfo wordInfo = new WordInfo(new FakeDict());
        sessionInfo = new SessionInfo(wordInfo);
    }

    @Test
    void checkGuessedLetterTrueTest() {
        assertThat(sessionInfo.checkGuessedLetter('a'))
                .isTrue();
        assertThat(sessionInfo.checkGuessedLetter('c'))
                .isTrue();
    }

    @Test
    void checkGuessedLetterFalseTest() {
        assertThat(sessionInfo.checkGuessedLetter('e'))
                .isFalse();
        assertThat(sessionInfo.checkGuessedLetter('1'))
                .isFalse();
    }

    @Test
    void getMaskTrueTest1() {
        assertThat(sessionInfo.getMask())
                .isEqualTo("****");
    }

    @Test
    void getMaskTrueTest2() {
        sessionInfo.checkGuessedLetter('a');
        assertThat(sessionInfo.getMask())
                .isEqualTo("a***");
    }

    @Test
    void getMaskTrueTest3() {
        sessionInfo.checkGuessedLetter('c');
        sessionInfo.checkGuessedLetter('d');
        sessionInfo.checkGuessedLetter('f');
        assertThat(sessionInfo.getMask())
                .isEqualTo("**cd");
    }

    @Test
    void getMaskFalseTest1() {
        assertThat(sessionInfo.getMask())
                .isNotEqualTo("abcd");
    }

    @Test
    void getMaskFalseTest2() {
        assertThat(sessionInfo.getMask())
                .isNotEqualTo("**");
    }

    @Test
    void getMistakesAndCharsTest1() {
        var hs = new HashSet<Character>();
        hs.add('b');
        hs.add('e');
        hs.add('f');
        sessionInfo.checkGuessedLetter('b');
        sessionInfo.checkGuessedLetter('e');
        sessionInfo.checkGuessedLetter('f');
        assertThat(sessionInfo.getMistakes())
                .isEqualTo(2);
        assertThat(sessionInfo.getChars())
                .isEqualTo(hs);
    }

    @Test
    void getMistakesAndCharsTest2() {
        var hs = new HashSet<Character>();
        hs.add('b');
        hs.add('f');
        sessionInfo.checkGuessedLetter('b');
        sessionInfo.checkGuessedLetter('b');
        sessionInfo.checkGuessedLetter('f');
        sessionInfo.checkGuessedLetter('f');
        assertThat(sessionInfo.getMistakes())
                .isEqualTo(2);
        assertThat(sessionInfo.getChars())
                .isEqualTo(hs);
    }
}
