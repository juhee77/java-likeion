package com.lahee.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {
    // 1. 테스트를 작성
    @Test
    public void additionTest() {
        // 2. 테스트를 실행하고 실패한다.
        Calculator calculator = new Calculator();

        assertEquals(5, calculator.add(2, 3));
        assertNotEquals(5, calculator.add(3, 3));
    }

    @Test
    public void subtractionTest() {
        Calculator calculator = new Calculator();

        assertEquals(3, calculator.sub(5, 2));
    }

    // 곱셈 기능을 만들어 봅시다.
    @Test
    public void multipleTest() {
        Calculator calculator = new Calculator();

        assertEquals(9, calculator.multiple(3, 3));
    }

    // 나누기를 만들건데,
    // 기본은 반환값이 정수
    // 만약 0으로 나누려고 한다면
    // IllegalArgumentException 발생
    @Test
    public void divisionTest() {
        Calculator calculator = new Calculator();

        assertEquals(3, calculator.divide(6, 2));
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(6, 0));
    }

    // 3. 테스트를 통과하는 코드를 작성
    private class Calculator {
        public int add(int a, int b) {
            return a + b;
        }

        public int sub(int a, int b) {
            return a - b;
        }

        public int multiple(int a, int b) {
            return a * b;
        }

        public int divide(int a, int b) {
            return a / b;
        }
    }
}
