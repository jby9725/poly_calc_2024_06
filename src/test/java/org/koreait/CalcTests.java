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
    // 삼항 더하기
    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void test4() {
        assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }
    // 삼항 빼기
    @Test
    @DisplayName("50 - 10 - 20 == 20")
    void test5() {
        assertThat(Calc.run("50 - 10 - 20")).isEqualTo(20);
    }
    // 삼항 빼기 음수 포함
    @Test
    @DisplayName("-50 - 10 - 20 == -80")
    void test6() {
        assertThat(Calc.run("-50 - 10 - 20")).isEqualTo(-80);
    }
    // 삼항 혼합 연산
    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void test7() {
        assertThat(Calc.run("10 - 20 + 30")).isEqualTo(20);
    }
    // 삼항 혼합 연산2
    @Test
    @DisplayName("-50 + 100 - 20 == 30")
    void test8() {
        assertThat(Calc.run("-50 + 100 - 20")).isEqualTo(30);
    }
    // 다항 혼합 연산
    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void test9() {
        assertThat(Calc.run("10 - 10 - 10 - 10 + 10 + 10 - 10")).isEqualTo(-10);
    }
    // 삼항 곱셈 연산
    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void test10() {
        assertThat(Calc.run("10 * 10 * 10")).isEqualTo(1000);
    }
    // 삼항 곱셈 연산
    @Test
    @DisplayName("10 * -15 * 10 == -1500")
    void test11() {
        assertThat(Calc.run("10 * -15 * 10")).isEqualTo(-1500);
    }
    // 삼항 곱셈 연산
    @Test
    @DisplayName("10 * 2 * -3 == -60")
    void test12() {
        assertThat(Calc.run("10 * 2 * -3")).isEqualTo(-60);
    }
    // 곱셈 더하기 혼합연산
    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void test13() {
        assertThat(Calc.run("10 + 5 * 2")).isEqualTo(20);
    }
    // 곱셈 더하기 혼합연산 2
    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void test14() {
        assertThat(Calc.run("20 + 10 + 5 * 2")).isEqualTo(40);
    }
    // 곱셈 더하기 혼합연산 3
    @Test
    @DisplayName("20 - 10 + 5 * 2 == 20")
    void test15() {
        assertThat(Calc.run("20 - 10 + 5 * 2")).isEqualTo(20);
    }
}
