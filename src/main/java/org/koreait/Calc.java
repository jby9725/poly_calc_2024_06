package org.koreait;

// 추가 조건
// 연산자 사이는 공백으로 구분한다.

import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static int run(String exp) {
        int answer = 0;

        // 빼기 연산을 음수를 더하는 것으로 변경
        exp = exp.replaceAll("- ", "+ -");

        boolean needToPlus = exp.contains("+");
//        boolean needToMinus = exp.contains("-"); // 이제 필요가 없어졌다.
        boolean needToMultiply = exp.contains("*");

        String[] bits = null;

        if (needToPlus) {
            bits = exp.split(" \\+ "); // 더하기는 역슬래시 2개 넣은 다음 써야한다. 모르면? 검색 : java split plus
        } else if (needToMultiply) {
            answer = 1;
            bits = exp.split(" \\* ");
        }

        // 파싱되고 남은 숫자들을 저장할 리스트 numbers
        List<Integer> numbers = new ArrayList<>();

        for (String bit : bits)
            numbers.add(Integer.parseInt(bit));

        for (int number : numbers) {
            if (needToPlus) {
                answer = answer + number;
            } else if (needToMultiply) {
                answer = answer * number;
            }
        }

        System.out.println("answer: " + answer);
        return answer;
//        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다.");

    }

}
