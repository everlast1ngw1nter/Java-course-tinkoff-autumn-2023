package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    void throwException() throws Exception {
        throw new Exception();
    }

    @Test
    void callingInfoTest1() {
        String expectedClass = this.getClass().getName();
        Task4.CallingInfo callingInfo = Task4.callingInfo(new Exception());
        assertThat(callingInfo.className())
                .isEqualTo(expectedClass);
        assertThat(callingInfo.methodName())
                .isEqualTo("callingInfoTest1");
    }

    @Test
    void callingInfoTest2() {
        Task4.CallingInfo callingInfo = null;
        try{
            throwException();
        } catch (Exception e) {
            callingInfo = Task4.callingInfo(e);
        }
        String expectedClass = this.getClass().getName();
        assertThat(callingInfo.className())
                .isEqualTo(expectedClass);
        assertThat(callingInfo.methodName())
                .isEqualTo("throwException");
    }
}
