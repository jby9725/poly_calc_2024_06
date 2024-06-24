package org.koreait;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTests {
    @Test
    @DisplayName("1 + 1 == 2")
    void test1() {
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("1000 + 200 == 1200")
    void test2() {
        assertThat(Calc.run("1000 + 200")).isEqualTo(1200);
    }

    @Test
    @DisplayName("50 - 30 == 20")
    void test3() {
        assertThat(Calc.run("50 - 30")).isEqualTo(20);
    }


}
