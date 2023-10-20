package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    void atbashCorrectTest1() {
        String inputStr = "Hello world!";
        String outputStr = "Svool dliow!";
            assertThat(Task1.atbash(inputStr))
                .isEqualTo(outputStr);
    }

    @Test
    void atbashCorrectTest2() {
        String inputStr = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String outputStr = "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        assertThat(Task1.atbash(inputStr))
                .isEqualTo(outputStr);
    }
}
