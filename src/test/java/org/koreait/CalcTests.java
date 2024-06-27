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
    @DisplayName("20 + 15 + 5 * 2 == 45")
    void test14() {
        assertThat(Calc.run("20 + 15 + 5 * 2")).isEqualTo(45);
    }
    // 곱셈 더하기 혼합연산 3
    @Test
    @DisplayName("20 - 10 + 5 * 2 == 20")
    void test15() {
        assertThat(Calc.run("20 - 10 + 5 * 2")).isEqualTo(20);
    }
    // 곱셈 더하기 혼합연산 4
    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void test16() {
        assertThat(Calc.run("10 * 20 + 10 + 5 * 2")).isEqualTo(220);
    }
    // 괄호연산
    @Test
    @DisplayName("(10 + 20) == 30")
    void test17() {
        assertThat(Calc.run("(10 + 20)")).isEqualTo(30);
    }
    // 괄호연산
    @Test
    @DisplayName("((10 + 20)) == 30")
    void test18() {
        assertThat(Calc.run("((10 + 20))")).isEqualTo(30);
    }
    // 괄호연산2
    @Test
    @DisplayName("(20 + 20) + 20 == 60")
    void test20() {
        assertThat(Calc.run("(20 + 20) + 20")).isEqualTo(60);
    }
    // 괄호연산2
    @Test
    @DisplayName("((20 + 20)) + 20 == 60")
    void test21() {
        assertThat(Calc.run("((20 + 20)) + 20")).isEqualTo(60);
    }
    // 괄호연산3
    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    void test22() {
        assertThat(Calc.run("(10 + 20) * 3")).isEqualTo(90);
    }

    @Test
    @DisplayName("10 + (10 + 5) == 25")
    void test23() {
        assertThat(Calc.run("10 + (10 + 5)")).isEqualTo(25);
    }

    @Test
    @DisplayName("10 * (10 + 5) == 150")
    void test24() {
        assertThat(Calc.run("10 * (10 + 5)")).isEqualTo(150);
    }

    @Test
    @DisplayName("-(10 + 5) == -15")
    void test25() {
        assertThat(Calc.run("-(10 + 5)")).isEqualTo(-15);
    }

    @Test
    @DisplayName("-(8 + 2) * -(7 + 3) + 5 == 105")
    void test26() {
        assertThat(Calc.run("-(8 + 2) * -(7 + 3) + 5")).isEqualTo(105);
    }

    @Test
    @DisplayName("5 - (1 + 5) == -1")
    void test27() {
        assertThat(Calc.run("5 - (1 + 5)")).isEqualTo(-1);
    }

    @Test
    @DisplayName("3 * 1 + (1 - (4 * 1 - (1 - 1))) == 0")
    void test28() {
        assertThat(Calc.run("3 * 1 + (1 - (4 * 1 - (1 - 1)))")).isEqualTo(0);
    }
}
