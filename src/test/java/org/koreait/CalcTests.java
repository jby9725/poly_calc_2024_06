package org.koreait;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTests {

    // 더하기
    @Test
    @DisplayName("1 + 1 == 2")
    void test1() {
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }
    // 더하기2
    @Test
    @DisplayName("1000 + 200 == 1200")
    void test2() {
        assertThat(Calc.run("1000 + 200")).isEqualTo(1200);
    }
    // 빼기 간단
    @Test
    @DisplayName("50 - 30 == 20")
    void test3() {
        assertThat(Calc.run("50 - 30")).isEqualTo(20);
    }
    // 다항 더하기
    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void test4() {
        assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }
    // 다항 빼기
    @Test
    @DisplayName("50 - 10 - 20 == 20")
    void test5() {
        assertThat(Calc.run("50 - 10 - 20")).isEqualTo(20);
    }
    // 다항 빼기 음수 포함
    @Test
    @DisplayName("-50 - 10 - 20 == -80")
    void test6() {
        assertThat(Calc.run("-50 - 10 - 20")).isEqualTo(-80);
    }
    // 다항 혼합 연산
    @Test
    @DisplayName("-50 + 100 - 20 == 30")
    void test7() {
        assertThat(Calc.run("-50 + 100 - 20")).isEqualTo(30);
    }



}
